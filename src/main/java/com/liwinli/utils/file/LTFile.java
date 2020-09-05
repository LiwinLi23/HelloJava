package com.liwinli.utils.file;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LTFile {

    public static void listPath(String curPath) {
        if (null == curPath || curPath.length() <= 1) {
            System.out.println("");
        }

        String path = "/Volumes/Macintosh HD 1/统计需求/data";
        File file = new File(path);
        File[] fs = file.listFiles();
        for(File f : fs) {
            if(!f.isDirectory()) {
                System.out.println(f);
            } else {
                System.out.println(f);
            }
        }
    }

    public static List<File> getFilesIn(String curPath) {
        List<File> fileList = new ArrayList();
        if (null == curPath || curPath.length() <= 1) { return fileList; }

        File file = new File(curPath);
        File[] fs = file.listFiles();
        for(File f : fs) {
            if(!f.isDirectory()) {
                fileList.add(f);
            }
        }

        return fileList;
    }
}
