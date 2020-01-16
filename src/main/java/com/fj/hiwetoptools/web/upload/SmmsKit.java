package com.fj.hiwetoptools.web.upload;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <p>Description: 上传到sm.ms</p>
 *
 * @auther linyu
 * @create 2019/3/27 15:06
 */
public class SmmsKit {
    private static Logger log = LoggerFactory.getLogger(SmmsKit.class);
    private static String url = "https://sm.ms/api/upload";

    /**
     * 上传图片到sm.ms
     * @param file
     * @return
     *
     *
    名称	类型	示例值	描述
    code	String	success	上传文件状态。正常情况为 success。出现错误时为 error
    filename	String	smms.jpg	上传文件时所用的文件名
    storename	String	561cc4e3631b1.png	上传后的文件名
    size	Int	187851	文件大小
    width	Int	1157	图片的宽度
    height	Int	680	图片的高度
    hash	String	nLbCw63NheaiJp1	随机字符串，用于删除文件
    delete	String	https://sm.ms/api/delete/nLbCw63NheaiJp1	删除上传的图片文件专有链接
    url	String	https://ooo.0o0.ooo/2015/10/13/561cfc3282a13.png	图片服务器地址
    path	String	/2015/10/13/561cfc3282a13.png	图片的相对地址
    msg	String	No files were uploaded.	上传图片出错时将会出现
     */
    public static JSONObject upload(File file) {
        String result = HttpRequest.post(url).form("smfile", file).timeout(30*1000).execute().body();
        log.info("upload sm.ms result:{}",result);
        return JSONObject.parseObject(result);
    }

    /**
     * 获取地址
     * @param jsonObject
     * @return
     * {"code":"success","data":{"width":959,"height":959,"filename":"201808301608441.jpg","storename":"5c9b23d4c4051.jpg","size":50636,"path":"\/2019\/03\/27\/5c9b23d4c4051.jpg","hash":"8w4zYcbP3G1Nl5r","timestamp":1553671124,"ip":"121.204.18.245","url":"https:\/\/i.loli.net\/2019\/03\/27\/5c9b23d4c4051.jpg","delete":"https:\/\/sm.ms\/delete\/8w4zYcbP3G1Nl5r"}}
     */
    public static String getUrl(JSONObject jsonObject) {
        if (jsonObject.getString("code").equalsIgnoreCase("success")) {
            return jsonObject.getJSONObject("data").getString("url");
        }
        return "";
    }
}
