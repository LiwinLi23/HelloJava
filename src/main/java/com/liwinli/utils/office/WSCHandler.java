package com.liwinli.utils.office;

//import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
//import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
//import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumECommerceOrder;
//import com.liwinli.app.xiaolingent.customer.analysis.model.AliBrushOrderModel;
//import com.liwinli.app.xiaolingent.customer.analysis.model.LTCityLevelModel;
//import com.liwinli.app.xiaolingent.customer.analysis.model.PurchasedCustomerModel;
import com.liwinli.utils.log.Logable;
import com.liwinli.utils.time.LTTimeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WSCHandler extends Logable {
//    private List<String> columnNameList = new ArrayList<String>();
//    private List<PurchasedCustomerModel> purchasedCustomerModelList = new ArrayList<>();
//
//    public void parse(File aliOrderFile) throws IOException {
//        EnumDataChannel.Platform platform = EnumDataChannel.Platform.WEISHANGCHENG;
//        String fileName = aliOrderFile.getName();
//        warn("正在分析微商城数据文件： {}", fileName);
//        FileInputStream inputStream = new FileInputStream(aliOrderFile);
//
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        int workBookNum = workbook.getNumberOfSheets();
//        info("WorkBook sheet number: {}", workBookNum);
//        for (int i = 0; i < workBookNum; ++i) {
//            String sheetName = workbook.getSheetName(i);
//            info("Sheet[{}] name: {}", i, sheetName);
//            Sheet firstSheet = workbook.getSheetAt(0);
//            int rowIndex = 0;
//            Iterator<Row> rowIterator = firstSheet.iterator();
//            while (rowIterator.hasNext()) {
//                PurchasedCustomerModel purchasedCustomerModel = new PurchasedCustomerModel();
//                Map<String, String> rowMap = new HashMap<String, String>();
//                Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
//                int cellIndex = 0;
//                while (cellIterator.hasNext()) {
////                warn("Column[{}] name: {}", cellIndex, Constants.ALITableColumns[cellIndex]);
//                    Cell cell = cellIterator.next();
//                    if (cellIndex < columnNameList.size() && columnNameList.get(cellIndex).equals("订单创建时间")) {
//                        Date createDate = cell.getDateCellValue();
//                        if (null != createDate) {
//                            rowMap.put("订单创建时间", Constants.sDateFormat.format(createDate));
//                        }
//
//                        ++cellIndex;
//                        continue;
//                    }
//
//                    switch (cell.getCellType()) {
//                        case STRING:
////                        System.out.print("\"");
////                        System.out.print(cell.getStringCellValue());
////                        System.out.print("\"");
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
////                        String cellKey = Constants.ALITableColumns[cellIndex];
//                            cellValue = cellValue.trim();
//                            if (0 == rowIndex) {
//                                if (Arrays.asList(Constants.WSCTableColumns).contains(cellValue)) {
//                                    columnNameList.add(cellValue);
//                                } else {
//                                    warn("列[{}] 名字: {} 不在指定的微商城列名中", cellIndex, cellValue);
//                                }
//                            } else {
//                                if (cellIndex < columnNameList.size()) {
//                                    String columnName = columnNameList.get(cellIndex);
//                                    rowMap.put(columnName, cellValue);
//                                }
//                            }
//                            ++cellIndex;
//                            break;
//
//                        case BOOLEAN:
////                        System.out.print(cell.getBooleanCellValue());
//                            ++cellIndex;
//                            break;
//
//                        case NUMERIC:
//                            double numericValue = cell.getNumericCellValue();
//                            if (cellIndex < columnNameList.size()) {
//                                String columnName = columnNameList.get(cellIndex);
//                                rowMap.put(columnName, "" + numericValue);
//                            }
//                            ++cellIndex;
//                            break;
//
//
//                        default:
//                            ++cellIndex;
//                            break;
//                    }
//
////                System.out.print(",");
////                ++cellIndex;
//                }
//
//                if (0 != rowIndex) {
//                    purchasedCustomerModel.platform = platform.getName();
//                    purchasedCustomerModel.shoppingName = EnumDataChannel.WeiShangChengShopping.XLTOYS_QIJIAN.getName();
//                    purchasedCustomerModel.orderNumer = rowMap.get("订单号");
//                    purchasedCustomerModel.orderType = rowMap.get("订单类型").equals("普通订单") ? EnumECommerceOrder.Type.NORMAL.getType() : EnumECommerceOrder.Type.GROUP_BOOKING.getType();
//                    purchasedCustomerModel.orderStatus = rowMap.get("订单状态").trim();
//                    purchasedCustomerModel.orderCreateTime = rowMap.get("订单创建时间");
//                    purchasedCustomerModel.receiverName = rowMap.get("收货人/提货人");
//                    purchasedCustomerModel.phone = rowMap.get("收货人手机号/提货人手机号");
//                    purchasedCustomerModel.mobilePhone = rowMap.get("收货人手机号/提货人手机号");
//                    purchasedCustomerModel.province = rowMap.get("收货人省份");
//                    purchasedCustomerModel.city = rowMap.get("收货人城市");
//                    purchasedCustomerModel.area = rowMap.get("收货人地区");
//                    purchasedCustomerModel.detailAddr = rowMap.get("详细收货地址/提货地址");
//                    purchasedCustomerModel.totalPrice = Float.parseFloat(rowMap.get("商品金额合计"));
//                    purchasedCustomerModel.refundStatus = rowMap.get("订单退款状态");
//                    purchasedCustomerModel.refundAmount = Float.parseFloat(rowMap.get("订单已退款金额"));
//                    purchasedCustomerModel.goodsTitle = rowMap.get("全部商品名称");
//                    purchasedCustomerModel.categoryId = new Double(Double.parseDouble(rowMap.get("商品种类数"))).intValue();
//
//                    double cityLevel = -1;
//                    for (LTCityLevelModel item : CityLevelHandler.ltCityLevelModels) {
//                        if (item.province.contains(purchasedCustomerModel.province) && item.city.contains(purchasedCustomerModel.city) &&
//                                item.district.contains(purchasedCustomerModel.area.equalsIgnoreCase("null") ? "" : purchasedCustomerModel.area)) {
//                            cityLevel = item.cityLevel;
//                            break;
//                        }
//                    }
//
//                    if (cityLevel >= 1) {
//                        purchasedCustomerModel.cityLevel = cityLevel;
//                    } else {
//                        info("{}, {}, {} can't find in 区域地图", purchasedCustomerModel.province, purchasedCustomerModel.city, purchasedCustomerModel.area);
//                        purchasedCustomerModel.cityLevel = cityLevel;
//                    }
//
//                    purchasedCustomerModelList.add(purchasedCustomerModel);
//                } else {
//                    info("微商城表单Columns： {}", columnNameList);
//                }
//
//                ++rowIndex;
//            }
//        }
//
//
//        workbook.close();
//        inputStream.close();
//    }
//
//    public List<PurchasedCustomerModel> getPurchasedCustomerModelList() {
//        return purchasedCustomerModelList;
//    }
}
