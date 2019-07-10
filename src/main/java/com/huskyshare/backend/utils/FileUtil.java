package com.huskyshare.backend.utils;

import java.util.Date;

public class FileUtil {

    /*
     * Java文件操作 获取文件扩展名
     */
    public static String getFileExtension(String filename){
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /*
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public static String generateFilename(){
        return "" + Math.round(new Date().getTime()+ 10000*Math.random());
    }
}
