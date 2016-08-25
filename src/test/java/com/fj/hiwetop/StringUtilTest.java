package com.fj.hiwetop;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.junit.Test;

import com.fj.hiwetoptools.StringUtil;
import com.fj.hiwetoptools.web.HttpUtil;

public class StringUtilTest {
	
	@Test
	public void format() throws UnsupportedEncodingException{
		System.out.println(StringUtil.format("{} test {}", "aaa","bbb"));
		//HttpUtil.setDefaultCharset("gbk");
		String result = HttpUtil.get("http://192.168.0.114:8080/");
		System.out.println(result);
	}
}
