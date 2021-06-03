package com.liwinli.utils.file;


import com.apple.eio.FileManager;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class LTFile {

    public static void listPath(String curPath) {
        if (null == curPath || curPath.length() <= 1) {
            System.out.println("");
        }

//        String path = "/Volumes/Macintosh HD 1/统计需求/data";
        File file = new File(curPath);
        File[] fs = file.listFiles();
        if (null == fs) {
            return;
        }

        for (File f : fs) {
            if (f.isDirectory()) {
                listPath(f.getAbsolutePath());
            } else {
                String fileName = f.getName();
                String lcFileName = fileName.toLowerCase();
                if ((lcFileName.endsWith("jpg") || lcFileName.endsWith("gif")) && f.length() > 50 * 1000) {
//                    log.info("File name: {}, size: {}", fileName, f.length());
//                    System.out.println("File name:" + fileName + ", size:" + f.length());
//                    System.out.println("New file Name: " + "/Volumes/KINGSTON/images/S/" + lcFileName);
                    f.renameTo(new File("/Volumes/KINGSTON/images/S/" + lcFileName));
                }
            }
        }
    }

    public static List<File> getFilesIn(String curPath) {
        List<File> fileList = new ArrayList();
        if (null == curPath || curPath.length() <= 1) {
            return fileList;
        }

        File file = new File(curPath);
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (!f.isDirectory()) {
                fileList.add(f);
            }
        }

        return fileList;
    }
}
