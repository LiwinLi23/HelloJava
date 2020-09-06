package com.liwinli.app.xiaolingent.customer.analysis;

import com.liwinli.app.xiaolingent.customer.analysis.constant.Constants;
import com.liwinli.app.xiaolingent.customer.analysis.enums.EnumDataChannel;
import com.liwinli.utils.file.LTFile;
import com.liwinli.utils.log.Logable;
import com.liwinli.utils.office.BrushOrderHandler;
import com.liwinli.utils.office.CityLevelHandler;
import com.liwinli.utils.office.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

import static java.awt.SystemColor.info;

@Slf4j
public class XLCommerceParser extends Logable {

    private static XLCommerceParser self = new XLCommerceParser();
    public static void main(String[] args) {
//        System.out.println("+++++++++++++++++ This is the XLCommerceParser ++++++++++++++++++++++");
        log.info("+++++++++++++++++ This is the XLCommerceParser ++++++++++++++++++++++");
        String parserPath = "/Volumes/KINGSTON/用户画像需求";
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

            try {
                ExcelUtils excelUtils = new ExcelUtils();
                excelUtils.parse(f);
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    }
}
