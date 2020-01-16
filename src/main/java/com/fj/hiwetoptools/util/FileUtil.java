package com.fj.hiwetoptools.util;

import cn.hutool.core.date.DateUtil;
import com.fj.hiwetoptools.exceptions.FileException;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Description: 文件工具类</p>
 *
 * @auther linyu
 * @create 2018/11/6 10:55
 */
public class FileUtil extends cn.hutool.core.io.FileUtil {

    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

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

    /**
     * 自动生成日期的文件名
     * @param paramString
     * @return
     */
    public static String genRandomName(String paramString) {
        String extendName = getExtendNameAddDot(paramString);
        if (StrUtil.isBlank(extendName)) {
            return "";
        }
        return StrUtil.format("{}{}", DateUtil.format(new Date(), "yyyyMMddHHmmss"), extendName);
    }

    /**
     * 自动生成日期的文件名
     * @param file
     * @return
     */
    public static String genRandomName(File file) {
        if(!file.exists()){
            throw new FileException("文件不存在");
        }
        return genRandomName(file.getName());
    }

    /**
     * 压缩文件或目录
     * @param srcDirName 压缩的根目录
     * @param fileName 根目录下的待压缩的文件名或文件夹名，其中*或""表示跟目录下的全部文件
     * @param descFileName 目标zip文件
     */
    public static void zipFiles(String srcDirName, String fileName,
                                String descFileName) {
        // 判断目录是否存在
        if (srcDirName == null) {
            log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        File fileDir = new File(srcDirName);
        if (!fileDir.exists() || !fileDir.isDirectory()) {
            log.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
            return;
        }
        String dirPath = fileDir.getAbsolutePath();
        File descFile = new File(descFileName);
        try {
            ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(
                    descFile));
            if ("*".equals(fileName) || "".equals(fileName)) {
                FileUtil.zipDirectoryToZipFile(dirPath, fileDir, zouts);
            } else {
                File file = new File(fileDir, fileName);
                if (file.isFile()) {
                    FileUtil.zipFilesToZipFile(dirPath, file, zouts);
                } else {
                    FileUtil
                            .zipDirectoryToZipFile(dirPath, file, zouts);
                }
            }
            zouts.close();
            log.debug(descFileName + " 文件压缩成功!");
        } catch (Exception e) {
            log.debug("文件压缩失败：" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 解压缩ZIP文件，将ZIP文件里的内容解压到descFileName目录下
     * @param zipFileName 需要解压的ZIP文件
     * @param descFileName 目标文件
     */
    public static boolean unZipFiles(String zipFileName, String descFileName) {
        String descFileNames = descFileName;
        if (!descFileNames.endsWith(File.separator)) {
            descFileNames = descFileNames + File.separator;
        }
        try {
            // 根据ZIP文件创建ZipFile对象
            ZipFile zipFile = new ZipFile(zipFileName);
            ZipEntry entry = null;
            String entryName = null;
            String descFileDir = null;
            byte[] buf = new byte[4096];
            int readByte = 0;
            // 获取ZIP文件里所有的entry
            @SuppressWarnings("rawtypes")
            Enumeration enums = zipFile.getEntries();
            // 遍历所有entry
            while (enums.hasMoreElements()) {
                entry = (ZipEntry) enums.nextElement();
                // 获得entry的名字
                entryName = entry.getName();
                descFileDir = descFileNames + entryName;
                if (entry.isDirectory()) {
                    // 如果entry是一个目录，则创建目录
                    new File(descFileDir).mkdirs();
                    continue;
                } else {
                    // 如果entry是一个文件，则创建父目录
                    new File(descFileDir).getParentFile().mkdirs();
                }
                File file = new File(descFileDir);
                // 打开文件输出流
                OutputStream os = new FileOutputStream(file);
                // 从ZipFile对象中打开entry的输入流
                InputStream is = zipFile.getInputStream(entry);
                while ((readByte = is.read(buf)) != -1) {
                    os.write(buf, 0, readByte);
                }
                os.close();
                is.close();
            }
            zipFile.close();
            log.debug("文件解压成功!");
            return true;
        } catch (Exception e) {
            log.debug("文件解压失败：" + e.getMessage());
            return false;
        }
    }

    /**
     * 将目录压缩到ZIP输出流
     * @param dirPath 目录路径
     * @param fileDir 文件信息
     * @param zouts 输出流
     */
    public static void zipDirectoryToZipFile(String dirPath, File fileDir,
                                             ZipOutputStream zouts) {
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            // 空的文件夹
            if (files.length == 0) {
                // 目录信息
                ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
                try {
                    zouts.putNextEntry(entry);
                    zouts.closeEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    // 如果是文件，则调用文件压缩方法
                    FileUtil
                            .zipFilesToZipFile(dirPath, files[i], zouts);
                } else {
                    // 如果是目录，则递归调用
                    FileUtil.zipDirectoryToZipFile(dirPath, files[i],
                            zouts);
                }
            }

        }

    }

    /**
     * 将文件压缩到ZIP输出流
     * @param dirPath 目录路径
     * @param file 文件
     * @param zouts 输出流
     */
    public static void zipFilesToZipFile(String dirPath, File file,
                                         ZipOutputStream zouts) {
        FileInputStream fin = null;
        ZipEntry entry = null;
        // 创建复制缓冲区
        byte[] buf = new byte[4096];
        int readByte = 0;
        if (file.isFile()) {
            try {
                // 创建一个文件输入流
                fin = new FileInputStream(file);
                // 创建一个ZipEntry
                entry = new ZipEntry(getEntryName(dirPath, file));
                // 存储信息到压缩文件
                zouts.putNextEntry(entry);
                // 复制字节到压缩文件
                while ((readByte = fin.read(buf)) != -1) {
                    zouts.write(buf, 0, readByte);
                }
                zouts.closeEntry();
                fin.close();
                System.out
                        .println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
     * @param dirPat 目录名
     * @param file entry文件名
     * @return
     */
    private static String getEntryName(String dirPath, File file) {
        String dirPaths = dirPath;
        if (!dirPaths.endsWith(File.separator)) {
            dirPaths = dirPaths + File.separator;
        }
        String filePath = file.getAbsolutePath();
        // 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
        if (file.isDirectory()) {
            filePath += "/";
        }
        int index = filePath.indexOf(dirPaths);

        return filePath.substring(index + dirPaths.length());
    }


    //使用递归遍历文件夹及子文件夹中文件

    /**
     * 遍历文件夹下所有文件
     * @param filenames
     * @param file
     */
    public static void filesDirs(List<String> filenames , File file){

        //File对象是文件或文件夹的路径，第一层判断路径是否为空
        if(file!=null){
            //第二层路径不为空，判断是文件夹还是文件
            if(file.isDirectory()){
                //进入这里说明为文件夹，此时需要获得当前文件夹下所有文件，包括目录
                File[] files=file.listFiles();//注意:这里只能用listFiles()，不能使用list()
                //files下的所有内容，可能是文件夹，也可能是文件，那么需要一个个去判断是文件还是文件夹，这个判断过程就是这里封装的方法
                //因此可以调用自己来判断，实现递归
                for (File flies2:files) {
                    filesDirs(filenames,flies2);
                }
            }else{
                if (file.getName().endsWith("jsp") || file.getName().endsWith("html")) {
                    log.info("文件名字:{}",file.toPath().toString());
                    filenames.add(file.toPath().toString());
                }
            }
        }else{
            log.warn("文件不存在");
        }
    }
}
