package com.fj.hiwetoptools.push;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fj.hiwetoptools.system.PropKit;
import com.jfinal.kit.LogKit;

/**
 * <p>Description: </p>
 *
 * @auther linyu
 * @create 2020/1/15 15:33
 */
public class TeleKit {
    public static final String url = PropKit.use("push.properties").get("tele.push.url");

    public static void sendTele(String text) {
        try {
            JSONObject json = new JSONObject();
            json.put("text", text);
            HttpUtil.post(url, json.toJSONString());
        } catch (Exception e) {
            LogKit.error(e.getMessage(),e);
        }
    }
}
