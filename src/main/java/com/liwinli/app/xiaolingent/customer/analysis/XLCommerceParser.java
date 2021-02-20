package com.liwinli.app.xiaolingent.customer.analysis;

import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
import com.liwinli.app.xiaolingent.customer.analysis.model.PurchasedCustomerModel;
import com.liwinli.utils.file.LTFile;
import com.liwinli.utils.log.Logable;
import com.liwinli.utils.office.BrushOrderHandler;
import com.liwinli.utils.office.CityLevelHandler;
import com.liwinli.utils.office.ExcelUtils;
import com.liwinli.utils.office.WSCHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.info;

@Slf4j
public class XLCommerceParser extends Logable {

    private static XLCommerceParser self = new XLCommerceParser();
    public static void main(String[] args) {
        self.info("+++++++++++++++++ This is the XLCommerceParser ++++++++++++++++++++++");
        List<PurchasedCustomerModel> totoalPurchasedCustomerModelList = new ArrayList<>();
//        String parserPath = "/Volumes/KINGSTON/用户画像需求";
        String parserPath = "/Volumes/Macintosh HD 1/统计需求/data";
        List<File> fileList = LTFile.getFilesIn(parserPath);

        for (File f : fileList) {
            String fileName = f.getName();
            if (fileName.trim().contains(Constants.CITY_LEVEL_FILE_KEYWORD)) {
                self.info("城市等级的Excel文件必须包含关键字: {}", Constants.CITY_LEVEL_FILE_KEYWORD);
                try {
                    CityLevelHandler cityLevelHandler = new CityLevelHandler();
                    cityLevelHandler.parse(f);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        for (File f : fileList) {
            String fileName = f.getName();
            if (fileName.trim().contains(Constants.BRUSH_FILE_KEYWORD)) {
                self.info("阿里系刷单文件必须包含: {}", Constants.BRUSH_FILE_KEYWORD);
                try {
                    BrushOrderHandler brushOrderHandler = new BrushOrderHandler();
                    brushOrderHandler.parse(f);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }

        for (File f : fileList) {
            String fileName = f.getName();
            boolean isAlyFile = false;  boolean isWSCFile = false;
            if (StringUtils.isEmpty(fileName)) { continue; }

            for (EnumDataChannel.FileKeywordAli aliKW : EnumDataChannel.FileKeywordAli.values()) {
                if (fileName.contains(aliKW.getFileKeyword())) {
                    isAlyFile = true;
                    break;
                }
            }

            for (EnumDataChannel.FileKeywordWSC wscKW : EnumDataChannel.FileKeywordWSC.values()) {
                if (fileName.contains(wscKW.getFileKeyword())) {
                    isWSCFile = true;
                    break;
                }
            }

            if (isAlyFile == isWSCFile) {
                self.warn("文件名：{} 无法确定是阿里系还是微商城数据源, 暂不分析该文件！！！", fileName);
                continue;
            }

            if (isAlyFile) {
                try {
                    ExcelUtils excelUtils = new ExcelUtils();
                    excelUtils.parse(f);
                    totoalPurchasedCustomerModelList.addAll(excelUtils.getPurchasedCustomerModelList());
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                try {
                    WSCHandler wscHandler = new WSCHandler();
                    wscHandler.parse(f);
                    totoalPurchasedCustomerModelList.addAll(wscHandler.getPurchasedCustomerModelList());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }

        try {
            //路径需要存在
//            Workbook wb = new HSSFWorkbook();         // Excel2003， 有65535行限制
            Workbook wb = new XSSFWorkbook();           // Excel2007， 暂无行号限制

            //创建 Sheet页
            Sheet sheetA = wb.createSheet("output");
            for(int i = 0; i < totoalPurchasedCustomerModelList.size(); ++i) {
                PurchasedCustomerModel model = totoalPurchasedCustomerModelList.get(i);
                Row row = sheetA.createRow(i);
                for(int j = 0; j < 18; ++j){
                    Cell cell = row.createCell(j);
                    switch (j) {
                        case 0:
                            cell.setCellValue(model.platform);
                            break;
                        case 1:
                            cell.setCellValue(model.shoppingName.toString());
                            break;
                        case 2:
                            cell.setCellValue(model.orderNumer);
                            break;
                        case 3:
                            cell.setCellValue(model.orderType.toString());
                            break;
                        case 4:
                            cell.setCellValue(model.orderStatus.toString());
                            break;
                        case 5:
                            cell.setCellValue(model.orderCreateTime.toString());
                            break;
                        case 6:
                            cell.setCellValue(model.receiverName);
                            break;
                        case 7:
                            cell.setCellValue(model.mobilePhone);
                            break;
                        case 8:
                            cell.setCellValue(model.province);
                            break;
                        case 9:
                            cell.setCellValue(model.city);
                            break;
                        case 10:
                            cell.setCellValue(model.area);
                            break;
                        case 11:
                            cell.setCellValue(model.cityLevel);
                            break;
                        case 12:
                            cell.setCellValue(model.detailAddr);
                            break;
                        case 13:
                            cell.setCellValue(model.totalPrice);
                            break;
                        case 14:
                            cell.setCellValue(model.refundStatus);
                            break;
                        case 15:
                            cell.setCellValue(model.refundAmount);
                            break;
                        case 16:
                            cell.setCellValue(model.goodsTitle);
                            break;
                        case 17:
                            cell.setCellValue(model.categoryId);
                            break;
                        default:
                    }
                    // cell.setCellValue((j+1)+" * "+(i+1)+" = " + (j+1)*(i+1));
                }
            }
            FileOutputStream fos = new FileOutputStream(parserPath + "/target/省月用户数据.xlsx");
            wb.write(fos);
            fos.close();
            wb.close();
            System.out.println("写数据结束！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
////        System.out.println("+++++++++++++++++ This is the XLCommerceParser ++++++++++++++++++++++");
//        log.info("+++++++++++++++++ This is the XLCommerceParser ++++++++++++++++++++++");
//        String parserPath = "/Volumes/KINGSTON/用户画像需求";
//        List<File> fileList = LTFile.getFilesIn(parserPath);
//
//        for (File f : fileList) {
//            String fileName = f.getName();
//            if (fileName.trim().contains(Constants.CITY_LEVEL_FILE_KEYWORD)) {
//                self.info("城市等级的Excel文件必须包含关键字: {}", Constants.CITY_LEVEL_FILE_KEYWORD);
//                try {
//                    CityLevelHandler cityLevelHandler = new CityLevelHandler();
//                    cityLevelHandler.parse(f);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }
//
//        for (File f : fileList) {
//            String fileName = f.getName();
//            if (fileName.trim().contains(Constants.BRUSH_FILE_KEYWORD)) {
//                self.info("阿里系刷单文件必须包含: {}", Constants.BRUSH_FILE_KEYWORD);
//                try {
//                    BrushOrderHandler brushOrderHandler = new BrushOrderHandler();
//                    brushOrderHandler.parse(f);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//            }
//        }
//
//        for (File f : fileList) {
//            String fileName = f.getName();
//            boolean isAlyFile = false;  boolean isWSCFile = false;
//            if (StringUtils.isEmpty(fileName)) { continue; }
//
//            for (EnumDataChannel.FileKeywordAli aliKW : EnumDataChannel.FileKeywordAli.values()) {
//                if (fileName.contains(aliKW.getFileKeyword())) {
//                    isAlyFile = true;
//                    break;
//                }
//            }
//
//            for (EnumDataChannel.FileKeywordWSC wscKW : EnumDataChannel.FileKeywordWSC.values()) {
//                if (fileName.contains(wscKW.getFileKeyword())) {
//                    isWSCFile = true;
//                    break;
//                }
//            }
//
//            if (isAlyFile == isWSCFile) {
//                self.warn("文件名：{} 无法确定是阿里系还是微商城数据源, 暂不分析该文件！！！", fileName);
//                continue;
//            }
//
//            try {
//                ExcelUtils excelUtils = new ExcelUtils();
//                excelUtils.parse(f);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//
//
//    }

}
