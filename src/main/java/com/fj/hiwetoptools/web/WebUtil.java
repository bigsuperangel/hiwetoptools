package com.fj.hiwetoptools.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.CharsetUtil;
import com.fj.hiwetoptools.util.StrUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fj.hiwetoptools.util.GsonUtil;

public class WebUtil {
	public static final Logger logger = LoggerFactory.getLogger(WebUtil.class);
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	public static boolean isAjaxRequest(HttpServletRequest request){

		String accept = request.getHeader("accept");
		String xRequestedWith = request.getHeader("X-Requested-With");

		// 如果是异步请求或是手机端，则直接返回信息
		return ((accept != null && accept.indexOf("application/json") != -1
				|| (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
		));
	}

	public static void printSuccess(HttpServletRequest request,
			HttpServletResponse response) {
		printSuccess(request, response, "操作成功", null);
	}

	public static void printSuccess(HttpServletRequest request,
			HttpServletResponse response, String msg) {
		printSuccess(request, response, msg, null);
	}

	public static void printSuccess(HttpServletRequest request,
			HttpServletResponse response, Object obj) {
		printSuccess(request, response, "操作成功", obj);
	}

	public static void printSuccess(HttpServletRequest request,
			HttpServletResponse response, String msg, Object obj) {
		doPrint(request, response, buildRs(true, msg, obj));
	}

	public static void printFail(HttpServletRequest request,
			HttpServletResponse response, String msg) {
		printFail(request, response, msg, null);
	}

	public static void printFail(HttpServletRequest request,
			HttpServletResponse response, Object obj) {
		printFail(request, response, "操作失败", obj);
	}

	public static void printFail(HttpServletRequest request,
			HttpServletResponse response, String msg, Object obj) {
		doPrint(request, response, buildRs(false, msg, obj));
	}

	private static String buildRs(boolean success, String msg, Object datas) {
		return new Result(success, msg, datas).toString();
	}

	public static void doPrint(HttpServletRequest request,
			HttpServletResponse response, String str) {
		setDisableCacheHeader(response);
		response.setHeader("Content-type", "application/json");
        response.setCharacterEncoding(CharsetUtil.UTF_8);

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(str);
			out.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	public static String getIp(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
		String ip = request.getHeader("X-Forwarder-For");
		if ((StrUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-Ip");
			if ((StrUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip)))
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
		if ((StrUtil.isBlank(ip)) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		if (("0:0:0:0:0:0:0:1".equals(ip)) || ("localhost".equals(ip))
				|| ("0.0.0.1".equals(ip)))
			ip = "127.0.0.1";
		return ip;
	}

	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000L);

		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	public static void setDisableCacheHeader(HttpServletResponse response) {
        response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");//HTTP 1.1
	}

	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}
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
     * 获取上下文URL全路径
     *
     * @param request
     * @return
     */
    public static String getContextPath(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme()).append("://").append(request.getServerName());
        int port = request.getServerPort();
        if(port != 80 ){
            sb.append(":").append(port);
        }
        sb.append(request.getContextPath());
        String path = sb.toString();
        sb = null;
        return path;
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

	public static void downLoad(HttpServletRequest request,
			HttpServletResponse response, String fileName, InputStream is) {
		OutputStream os = null;
		try {
			int length = is.available();
			byte[] b = new byte[length];
			is.read(b);

			response.reset();
			response.setContentType("application/octet-stream");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(fileName, "utf-8"));
			response.addIntHeader("Content-Length", length);
			os = new BufferedOutputStream(response.getOutputStream());
			os.write(b);
			os.flush();
		} catch (Exception localException) {
			logger.error(localException.getMessage(),localException);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	public static class Result {
		private boolean rs = true;
		private String msg;
		private Object datas;

		public Result() {
		}

		public Result(boolean rs, String msg) {
			this.rs = rs;
			this.msg = msg;
		}

		public Result(boolean rs, String msg, Object datas) {
			this.rs = rs;
			this.msg = msg;
			this.datas = datas;
		}

		public boolean isRs() {
			return this.rs;
		}

		public void setRs(boolean rs) {
			this.rs = rs;
		}

		public String getMsg() {
			return this.msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getDatas() {
			return this.datas;
		}

		public void setDatas(Object datas) {
			this.datas = datas;
		}

		@Override
		public String toString() {
			StringBuilder strb = new StringBuilder("{\"rs\":");
			strb.append(this.rs ? "true" : "false")
			.append(",\"msg\":\"")
			.append(this.msg == null ? "" : this.msg)
			.append("\",\"datas\":")
			.append(this.datas == null ? "{}" : JSON
					.toJSONString(this.datas)).append("}");
			return strb.toString();
		}
	}
}
