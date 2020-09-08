package com.liwinli.utils.office;

import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;
import com.liwinli.app.xiaolingent.customer.analysis.model.AliBrushOrderModel;
import com.liwinli.app.xiaolingent.customer.analysis.model.LTCityLevelModel;
import com.liwinli.app.xiaolingent.customer.analysis.model.PurchasedCustomerModel;
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
    private List<PurchasedCustomerModel> purchasedCustomerModelList = new ArrayList<>();
    public  void parse(File aliOrderFile) throws IOException {
//        String excelFilePath = "/Volumes/Macintosh HD 1/统计需求/data/旗舰店（6月1日-30日）.xlsx";
//        File aliOrderFile = new File(excelFilePath);
        EnumDataChannel.Platform platform = null;
        String fileName = aliOrderFile.getName();
        warn("正在分析阿里系数据文件： {}", fileName);
        EnumECommerceOrder.BrushShopping brushShopping = EnumECommerceOrder.BrushShopping.NULL_BS;
        if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.TAOBAO.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.TAOBAO;
            platform = EnumDataChannel.Platform.TAOBAO;
        } else if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.QIJIAN.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.QIJIAN;
            platform = EnumDataChannel.Platform.TIANMAO;
        } else if (fileName.trim().contains(EnumECommerceOrder.BrushShopping.ZHUANYING.getName().substring(0, 2))) {
            brushShopping = EnumECommerceOrder.BrushShopping.ZHUANYING;
            platform = EnumDataChannel.Platform.TIANMAO;
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
            PurchasedCustomerModel purchasedCustomerModel = new PurchasedCustomerModel();
            Map<String, String> rowMap = new HashMap<String, String>();
            Row nextRow = rowIterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int cellIndex = 0;
            while (cellIterator.hasNext()) {
//                warn("Column[{}] name: {}", cellIndex, Constants.ALITableColumns[cellIndex]);
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
                        cellValue = cellValue.trim();
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
                                    String[] addressArray = cellValue.split(" ");
                                    if (addressArray.length <= 2) {
                                        rowMap.put("xl_province", "未知"); rowMap.put("xl_city", "未知");
                                        rowMap.put("xl_area", "未知");
                                    } else {
                                        rowMap.put("xl_province", addressArray[0]); rowMap.put("xl_city", addressArray[1]);
                                        rowMap.put("xl_area", addressArray[2]);
                                    }
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
                        }
                    }
                }
            }

            if (0 != rowIndex && !isBrushOrder) {
                purchasedCustomerModel.platform = platform.getName();
                purchasedCustomerModel.shoppingName = rowMap.get("店铺名称");
                purchasedCustomerModel.orderNumer = rowMap.get("订单编号");
                purchasedCustomerModel.orderType = EnumECommerceOrder.Type.NORMAL.getType();
                purchasedCustomerModel.orderStatus = rowMap.get("订单状态").trim();
                purchasedCustomerModel.orderCreateTime = orderTime;
                purchasedCustomerModel.receiverName = rowMap.get("收货人姓名");
                purchasedCustomerModel.phone = rowMap.get("联系电话");
                purchasedCustomerModel.mobilePhone = rowMap.get("联系手机");
                purchasedCustomerModel.province = rowMap.get("xl_province");
                purchasedCustomerModel.city = rowMap.get("xl_city");
                purchasedCustomerModel.area = rowMap.get("xl_area");
                purchasedCustomerModel.detailAddr = rowMap.get("收货地址");
                purchasedCustomerModel.totalPrice = Float.parseFloat(rowMap.get("买家实际支付金额"));
                purchasedCustomerModel.refundStatus = rowMap.get("订单关闭原因");
                purchasedCustomerModel.refundAmount = Float.parseFloat(rowMap.get("退款金额"));
                purchasedCustomerModel.goodsTitle = rowMap.get("宝贝标题");
                purchasedCustomerModel.categoryId = Integer.parseInt(rowMap.get("宝贝种类"));

                double cityLevel = -1;
                for (LTCityLevelModel item : CityLevelHandler.ltCityLevelModels) {
                    if (item.province.contains(purchasedCustomerModel.province) && item.city.contains(purchasedCustomerModel.city) &&
                        item.district.contains(purchasedCustomerModel.area.equalsIgnoreCase("null") ? "" : purchasedCustomerModel.area)) {
                        cityLevel = item.cityLevel;
                        break;
                    }
                }

                if (cityLevel >= 1) {
                    purchasedCustomerModel.cityLevel = cityLevel;
                } else {
                    info("{}, {}, {} can't find in 区域地图", purchasedCustomerModel.province, purchasedCustomerModel.city, purchasedCustomerModel.area);
                    purchasedCustomerModel.cityLevel = cityLevel;
                }

                purchasedCustomerModelList.add(purchasedCustomerModel);
            }

            ++rowIndex;
        }

        workbook.close();
        inputStream.close();
    }

    public List<PurchasedCustomerModel> getPurchasedCustomerModelList() { return purchasedCustomerModelList; }
}
