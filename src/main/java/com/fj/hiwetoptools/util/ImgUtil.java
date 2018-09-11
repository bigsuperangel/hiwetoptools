package com.fj.hiwetoptools.util;

import com.fj.hiwetoptools.exceptions.FileException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @des 图片处理工具类
 * @auther linyu
 * @create 2016-09-13:9:48
 */
public class ImgUtil {
    private static final Logger log = LoggerFactory.getLogger(ImgUtil.class);

    /**
     * 图像尺寸不变，默认按0.25质量
     * @param origin
     * @param dest
     */
    public static void compress(File origin, File dest){
        compress(origin,dest,0.25f);
    }

    /**
     * 图像尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
     * @param origin
     * @param dest
     * @param quality 图片质量
     */
    public static void compress(File origin,File dest,float quality){
        try {
            Thumbnails.of(origin).scale(1f).outputQuality(quality).toFile(dest);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileException("图片压缩失败");
        }
    }

    /**
     * 不按比例，就按指定的大小进行缩放
     * @param origin
     * @param dest
     * @param width
     * @param height
     */
    public static void scaleByFixedSize(File origin,File dest,int width,int height){
        try {
            Thumbnails.of(origin).size(width, height).keepAspectRatio(true).toFile(dest);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileException("图片缩放失败");
        }
    }

    /**
     * 按指定大小把图片进行缩和放（会遵循原图高宽比例）
     * @param origin
     * @param dest
     * @param width
     * @param height
     * @param keepRatio 是否按原图宽高比例 false不按比例，true按比例
     */
    public static void scaleByFixedSize(File origin,File dest,int width,int height,boolean keepRatio){
        try {
            Thumbnails.of(origin).size(width, height).keepAspectRatio(keepRatio).toFile(dest);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileException("图片缩放失败");
        }
    }

    /**
     * 按照比例进行缩小和放大
     * @param origin
     * @param dest
     * @param ratio 比例 ，大于1为放大，小于1为缩小
     */
    public static void scaleByRatio(File origin,File dest, float ratio){
        try {
            Thumbnails.of(origin).scale(ratio).toFile(dest);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new FileException("图片缩放失败");
        }
    }

}
