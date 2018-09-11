package com.fj.hiwetop;

import com.fj.hiwetoptools.web.URIUtil;

public class BasicTypeTest {

    public static void main(String[] args) {
        String url = "http://www.name.lrj/?id=1234";
        String id = URIUtil.getParameter(url, "id");
        System.out.println(id);    // 1234

        String query = "name=zhangsan&age=20";
        String age = URIUtil.getQueryParameter(query, "age");
        System.out.println(age);
    }
}
