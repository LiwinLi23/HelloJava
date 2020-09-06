package com.liwinli.utils.office;

import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;
import com.liwinli.app.xiaolingent.customer.analysis.model.AliBrushOrderModel;
import com.liwinli.utils.log.Logable;
import com.liwinli.utils.time.LTTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
public class ExcelUtils extends Logable {

    private List<String> columnNameList = new ArrayList<String>();

    private List<Map<String, String>> mExcelData = new ArrayList<Map<String, String>>();
    public  void parse(File aliOrderFile) throws IOException {
//        String excelFilePath = "/Volumes/Macintosh HD 1/统计需求/data/旗舰店（6月1日-30日）.xlsx";
//        File aliOrderFile = new File(excelFilePath);
        String fileName = aliOrderFile.getName();
        EnumECommerceOrder.BrushShopping brushShopping = EnumECommerceOrder.BrushShopping.NULL_BS;
        if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.TAOBAO.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.TAOBAO;
        } else if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.QIJIAN.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.QIJIAN;
        } else if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.ZHUANYING.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.ZHUANYING;
        }

        FileInputStream inputStream = new FileInputStream(aliOrderFile);

        Workbook workbook = new XSSFWorkbook(inputStream);
        int workBookNum = workbook.getNumberOfSheets();
        info("WorkBook sheet number: {}", workBookNum);
        for (int i = 0; i < workBookNum; ++i) {
            String sheetName = workbook.getSheetName(i);
            info("Sheet[{}] name: {}", i, sheetName);
        }
        Sheet firstSheet = workbook.getSheetAt(0);
        int rowIndex = 0;
        Iterator<Row> rowIterator = firstSheet.iterator();
        while (rowIterator.hasNext()) {
            Map<String, String> rowMap = new HashMap<String, String>();
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int cellIndex = 0;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case STRING:
//                        System.out.print("\"");
//                        System.out.print(cell.getStringCellValue());
//                        System.out.print("\"");
                        String cellValue = cell.getStringCellValue();
                        if (null == cellValue) {
                            info("[{}, {}] 内容是空", rowIndex, cellIndex);
                            ++cellIndex;
                            continue;
                        }
                        if (cellIndex >= Constants.ALITableColumns.length) {
                            warn("Cell index: {} >= 阿里系列数量");
                            break;
                        }

//                        String cellKey = Constants.ALITableColumns[cellIndex];
                        if (0 == rowIndex) {
                            if (Arrays.asList(Constants.ALITableColumns).contains(cellValue)) {
                                columnNameList.add(cellValue);
                            } else {
                                warn("列[{}] 名字: {} 不在指定的列名中", cellIndex, cellValue);
                            }
                        } else {
                            if (cellIndex < columnNameList.size()) {
                                String columnName = columnNameList.get(cellIndex);
                                if (columnName.trim().equals("收货地址")) {
//                                    info("收货地址： {}", cellValue);
                                    String[] addressArray = cellValue.split(" ");
                                    int arraySize = addressArray.length;
                                }

                                rowMap.put(columnName, cellValue);
                            }
                        }
                        ++cellIndex;
                        break;

                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue());
                        ++cellIndex;
                        break;

                    case NUMERIC:
                        ++cellIndex;
                        break;
//                        System.out.print(cell.getNumericCellValue());

                    default:
                        ++cellIndex;
                        break;
                }

//                System.out.print(",");
//                ++cellIndex;
            }

            String orderTime = rowMap.get("订单创建时间");
            boolean isBrushOrder = false;
            if (rowMap.size() > 0 && null != orderTime) {
                Map<String, Integer> dateMap = LTTimeUtil.getYMD(orderTime);
                Integer year = dateMap.get("year"); Integer month = dateMap.get("month");
                AliBrushOrderModel aliBrushOrderModel = null;
                for (AliBrushOrderModel item : BrushOrderHandler.aliBrushOrderModels) {
                    if (item.shopping.equals(brushShopping) && item.year == year.intValue() && item.month == month.intValue()) {
                        aliBrushOrderModel = item;
                        break;
                    }
                }

                List<String> brushList = (null == aliBrushOrderModel) ? null : aliBrushOrderModel.orderList;
                if (!CollectionUtils.isEmpty(brushList)) {
                    String rowOrderSN = rowMap.get(Constants.ALI_ORDER_SN_COLUMN);
                    for (String brushOrder : brushList) {
                        if (brushOrder.equalsIgnoreCase(rowOrderSN)) {
                            isBrushOrder = true;
                            info("RowIndex: {} SN:{} is brush", rowIndex, rowOrderSN);
                        }
                    }
                }
            }

            if (!isBrushOrder) { mExcelData.add(rowMap); }
            if (0 == rowIndex) { info("该Excel 列为： {}", columnNameList); }
            ++rowIndex;
        }

        workbook.close();
        inputStream.close();
    }
}
