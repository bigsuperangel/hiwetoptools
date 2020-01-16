package com.fj.hiwetoptools.push;

import cn.hutool.http.HttpUtil;
import com.fj.hiwetoptools.system.PropKit;
import com.jfinal.kit.Kv;

/**
 * <p>Description: Server酱</p>
 *
 * @auther linyu
 * @create 2020/1/13 11:27
 */
public class ServerJKit {
    private static final String url =  PropKit.use("push.properties").get("serverj.push.url");

    public static void send(String text,String desp) {
        Kv kv = Kv.create();
        kv.set("text", text);
        kv.set("desp", desp);
        String post = HttpUtil.post(url, kv);
        System.out.println(post);
    }

}
