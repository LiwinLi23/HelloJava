package com.liwinli.utils.office;

import com.liwinli.app.xiaolingent.customer.analysis.model.AliBrushOrderModel;
import com.liwinli.utils.log.Logable;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class BrushOrderHandler extends Logable {
//    private List<String> columnNameList = new ArrayList<String>();
//    public static List<AliBrushOrderModel> aliBrushOrderModels = new ArrayList<AliBrushOrderModel>();
//
//    public void parse(File excelFile) throws IOException {
//        info("Parse brush_order file: {}", excelFile.getName());
//        FileInputStream inputStream = new FileInputStream(excelFile);
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        int sheetNum = workbook.getNumberOfSheets();
//        info("WorkBook sheet number: {}", sheetNum);
//        for (int i = 0; i < sheetNum; ++i) {
//            EnumECommerceOrder.BrushShopping brushShopping = EnumECommerceOrder.BrushShopping.QIJIAN;
//            String sheetName = workbook.getSheetName(i);
//            info("Sheet[{}] name: {}", i, sheetName);
//            if (sheetName.trim().contains(EnumECommerceOrder.BrushShopping.QIJIAN.getName())) {
//                brushShopping = EnumECommerceOrder.BrushShopping.QIJIAN;
//            } else if (sheetName.trim().contains(EnumECommerceOrder.BrushShopping.TAOBAO.getName())) {
//                brushShopping = EnumECommerceOrder.BrushShopping.TAOBAO;
//            } else if (sheetName.trim().contains(EnumECommerceOrder.BrushShopping.ZHUANYING.getName())) {
//                brushShopping = EnumECommerceOrder.BrushShopping.ZHUANYING;
//            } else {
//                info("Sheet[{}] name: {} is invalid", i, sheetName);
//                continue;
//            }
//
//            columnNameList = new ArrayList<String>();
//            Sheet curSheet = workbook.getSheetAt(i);
//            int rowIndex = 0;
//            Iterator<Row> rowIterator = curSheet.iterator();
//            while (rowIterator.hasNext()) {
//                Map<String, String> rowMap = new HashMap<String, String>();
//                Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
//                int cellIndex = 0;
//                while (cellIterator.hasNext()) {
//                    Cell cell = cellIterator.next();
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            String cellValue = cell.getStringCellValue();
//                            if (null == cellValue) {
//                                info("[{}, {}] 内容是空", rowIndex, cellIndex);
//                                ++cellIndex;
//                                continue;
//                            }
//                            if (cellIndex >= Constants.ALITableColumns.length) {
//                                warn("Cell index: {} >= 阿里系列数量");
//                                break;
//                            }
//
//                            if (0 == rowIndex) {
//                                if (Arrays.asList(Constants.ALI_BRUSH_ORDER_COLUMNS).contains(cellValue)) {
//                                    columnNameList.add(cellValue);
//                                } else {
//                                    warn("列[{}] 名字: {} 不在指定的列名中", cellIndex, cellValue);
//                                }
//                            } else {
//                                if (cellIndex < columnNameList.size()) {
//                                    String columnName = columnNameList.get(cellIndex);
//                                    if (columnName.trim().equals(Constants.ALI_BRUSH_ORDER_COLUMNS[1])) {
////                                        info("接单时间： {}", cellValue);
//                                    } else if (columnName.trim().equals("淘宝订单编号")) {
////                                        info("订单: {}", cellValue);
//                                    }
//
//                                    rowMap.put(columnName, cellValue);
//                                }
//                            }
//                            ++cellIndex;
//                            break;
//
//                        case BOOLEAN:
//                            ++cellIndex;
//                            break;
//
//                        case NUMERIC:
//                            ++cellIndex;
//                            break;
//
//                        default:
//                            ++cellIndex;
//                            break;
//                    }
//
//                    if (cellIndex >= Constants.ALI_BRUSH_ORDER_COLUMNS.length) {
//                        break;
//                    }
//                }
//
//                if (0 == rowIndex) {
//                    info("该Excel 列为： {}", columnNameList);
//                } else {
//                    String rowTime = rowMap.get(Constants.ALI_BRUSH_ORDER_COLUMNS[1]);
//                    if (StringUtils.isBlank(rowTime)) {
//                        continue;
//                    }
//
//                    int year = 0;
//                    int month = 0;
//                    try {
//                        Date date = Constants.sDateFormat.parse(rowTime);
//                        year = date.getYear() + 1900;
//                        month = date.getMonth() + 1;
//                    } catch (Exception e) {
//                        info("{}", e);
//                    }
//
//                    if (year > 0 && month > 0) {
//                        AliBrushOrderModel aliBrushOrderModel = null;
//                        for (AliBrushOrderModel item : aliBrushOrderModels) {
//                            if (item.shopping.equals(brushShopping) && item.year == year && item.month == month) {
//                                aliBrushOrderModel = item;
//                            }
//                        }
//
//                        if (null == aliBrushOrderModel) {
//                            aliBrushOrderModel = new AliBrushOrderModel();
//                            aliBrushOrderModel.shopping = brushShopping;
//                            aliBrushOrderModel.year = year;
//                            aliBrushOrderModel.month = month;
//                            aliBrushOrderModels.add(aliBrushOrderModel);
//                        }
//
//                        aliBrushOrderModel.orderList.add(rowMap.get(Constants.ALI_BRUSH_ORDER_COLUMNS[6]));
//
//                    }
//                }
//
//                ++rowIndex;
//            }
//        }
//
//        workbook.close();
//        inputStream.close();
//    }
}
