package com.fj.hiwetoptools.web;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fj.hiwetoptools.GsonUtil;
import com.fj.hiwetoptools.StringUtil;

/**
 * 请求工具类
 * @author linyu
 *
 */
public class RequestUtil {
	/**
	 * 判断是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		return request.getHeader("X-Requested-With") == null;
	}
	
	/**
	 * 获取相对准确的ip地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		String ip = request.getHeader("X-Forwarder-For");
		if ((StringUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-Ip");
			if ((StringUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip)))
				ip = request.getHeader("WL-Proxy-Client-Ip");
		} else {
			String[] ipArr = ip.split(",");
			for (String IP : ipArr) {
				if (!"unknown".equalsIgnoreCase(IP)) {
					ip = IP;
					break;
				}
			}
		}
		if ((StringUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		if (("0:0:0:0:0:0:0:1".equals(ip)) || ("localhost".equals(ip))
				|| ("0.0.0.1".equals(ip)))
			ip = "127.0.0.1";
		return ip;
	}
	
	/**
	 * 获得request所有请求参数.
	 *
	 * @param request
	 * @return
	 */
	public static Map<String, String> getRequestParams(HttpServletRequest request) {
		Map<String, String> reqMap = new HashMap<String, String>();
		Enumeration<?> enum_term = request.getParameterNames();
		while (enum_term.hasMoreElements()) {
			String paramName = (String) enum_term.nextElement();
			String paramValue = request.getParameter(paramName);
			reqMap.put(paramName, paramValue);

		}
		return reqMap;
	}

	/**
	 * 获得请求路径
	 *
	 * @param request
	 * @return 请求路径
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		if(request.getQueryString()!=null)
			requestPath = requestPath + "?"	+ request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

	/**
	 * 获得请求的全路径
	 *
	 * @param request
	 * @return
	 */
	public static String getReuqestAllPath(HttpServletRequest request){
		Map<?, ?> properties = request.getParameterMap();
		return request.getRequestURI()+GsonUtil.toJson(properties);
	}
}
