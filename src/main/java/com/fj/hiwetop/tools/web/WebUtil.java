package com.fj.hiwetop.tools.web;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fj.hiwetop.tools.exception.SysException;
import com.fj.hiwetop.tools.json.GsonUtil;
import com.fj.hiwetop.tools.string.StringUtil;

public class WebUtil {
	public static final Logger logger = LoggerFactory.getLogger(WebUtil.class);
	public static final String TEXT_TYPE = "text/plain";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	private static String WEB_PATH;

	public static Integer getInt(HttpServletRequest request, String key,
			Integer defVal) {
		String obj = request.getParameter(key);
		if ((obj == null) || ("".equals(obj.trim())))
			return defVal;
		try {
			return Integer.valueOf(Integer.parseInt(obj));
		} catch (NumberFormatException e) {
		}
		return defVal;
	}

	public static Long getLong(HttpServletRequest request, String key,
			Long defVal) {
		String obj = request.getParameter(key);
		if ((obj == null) || ("".equals(obj.trim())))
			return defVal;
		try {
			return Long.valueOf(Long.parseLong(obj));
		} catch (NumberFormatException e) {
		}
		return defVal;
	}

	public static String getWebPath() {
		return WEB_PATH;
	}

	public static void setWebPath(String path) {
		WEB_PATH = path;
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return request.getHeader("X-Requested-With") == null;
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

	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000L);

		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	public static void setDisableCacheHeader(HttpServletResponse response) {
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");

		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
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
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
	}

	public static String simpleHttpGet(String url, Map<String, Object> header) {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			logger.info("抓取开始 url=" + url + " \n header = " + header);
			URL urlObj = new URL(url);

			connection = (HttpURLConnection) urlObj.openConnection();

			connection.connect();

			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			StringBuilder strb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				strb.append(line);
			}

			logger.info("抓取完成  返回内容[" + strb.toString() + "] url=" + url);
			return strb.toString();
		} catch (Exception e) {
			logger.error("抓取页面url=" + url + "出现异常", e);
			throw new SysException("抓取页面url=" + url + "出现异常：" + e.getMessage(),
					e);
		} finally {
			IOUtils.closeQuietly(reader);
			if (connection != null)
				try {
					connection.disconnect();
				} catch (Exception e) {
					logger.error("关闭抓取流出现异常", e);
				}
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
