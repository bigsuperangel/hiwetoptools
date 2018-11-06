package com.fj.hiwetoptools.util;

import com.fj.hiwetoptools.exceptions.FileException;

import java.io.File;

/**
 * <p>Description: 文件工具类</p>
 *
 * @auther linyu
 * @create 2018/11/6 10:55
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {
    /**
     * 获取文件后缀 jpg
     * @param paramString
     * @return
     */
    public static String getExtendName(String paramString) {
        int i = paramString.lastIndexOf(".");
        if (i == -1) {
            return "";
        }
        return paramString.substring(i + 1);
    }

    /**
     * 获取文件后缀 jpg
     * @param file
     * @return
     */
    public static String getExtendName(File file) {
        if(!file.exists()){
            throw new FileException("文件不存在");
        }
        return getExtendName(file.getName());
    }

    /**
     * 获取文件后缀包括.jpg
     * @param paramString
     * @return
     */
    public static String getExtendNameAddDot(String paramString){
        int i = paramString.lastIndexOf(".");
        if (i == -1) {
            return "";
        }
        return paramString.substring(i);
    }

    /**
     * 获取文件后缀包括.jpg
     * @param file
     * @return
     */
    public static String getExtendNameAddDot(File file){
        if(!file.exists()){
            throw new FileException("文件不存在");
        }
        return getExtendNameAddDot(file.getName());
    }
}
