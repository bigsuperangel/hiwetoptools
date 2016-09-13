package com.fj.hiwetop;

import com.fj.hiwetoptools.FileUtil;
import com.fj.hiwetoptools.ImgUtil;
import org.junit.Test;

import java.io.File;

/**
 * @des
 * @auther linyu
 * @create 2016-09-13:10:27
 */
public class ImgUtilTest {

    //@Test
    public void test(){
        File origin = new File("F:\\Jellyfish.jpg");
        String suff = FileUtil.getExtendNameAddDot(origin);
        File dest = new File("F:\\dest"+suff);
        ImgUtil.compress(origin,dest);
    }
}
