package com.fj.hiwetop.http;


import com.fj.hiwetoptools.http.Header;
import com.fj.hiwetoptools.http.HttpRequest;
import com.fj.hiwetoptools.http.HttpUtil;
import com.fj.hiwetoptools.lang.Console;

import java.util.HashMap;

/**
 * Post请求样例
 * @author Looly
 *
 */
public class HttpPostDemo {
	public static void main(String[] args) {
		String url = "http://wthrcdn.etouch.cn/weather_mini";
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("city", "北京");
		String result1 = HttpUtil.post(url, paramMap);
		Console.log(result1);
		
		//链式构建请求
		String result2 = HttpRequest.post(url)
			.header(Header.USER_AGENT, "Hutool http")
			.form(paramMap)
			.execute().body();
		Console.log(result2);
	}
}
