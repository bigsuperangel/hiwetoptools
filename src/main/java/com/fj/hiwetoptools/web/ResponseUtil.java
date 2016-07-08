package com.fj.hiwetoptools.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fj.hiwetoptools.GsonUtil;
import com.fj.hiwetoptools.web.bean.BaseResultEnums;
import com.fj.hiwetoptools.web.bean.ResultModel;

/**
 * 通用响应类
 * @author linyu
 *
 */
public class ResponseUtil {
	public static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
	
	/**
	 * 操作成功响应
	 * @param request
	 * @param response
	 * @param brResult 
	 * @param obj 额外数据
	 */
	public static void responseSuccess(HttpServletRequest request,
			HttpServletResponse response, BaseResultEnums brResult, Object obj){
		doPrint(request, response, getJson(new ResultModel(true, brResult,obj)));
	}
	
	/**
	 * 操作成功响应
	 * @param request
	 * @param response
	 * @param brResult
	 */
	public static void responseSuccess(HttpServletRequest request,
			HttpServletResponse response, BaseResultEnums brResult){
		doPrint(request, response, getJson(new ResultModel(true, brResult)));
	}
	
	/**
	 * 失败响应
	 * @param request
	 * @param response
	 * @param brResult
	 */
	public static void responseFailure(HttpServletRequest request,
			HttpServletResponse response, BaseResultEnums brResult){
		doPrint(request, response, getJson(new ResultModel(false, brResult)));
	}
	
	/**
	 * 失败响应
	 * @param request
	 * @param response
	 * @param brResult
	 * @param obj
	 */
	public static void responseFailure(HttpServletRequest request,
			HttpServletResponse response, BaseResultEnums brResult, Object obj){
		doPrint(request, response, getJson(new ResultModel(false, brResult,obj)));
	}
	
	private static String getJson(ResultModel result){
		return GsonUtil.toJson(result);
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
	
	public static void setDisableCacheHeader(HttpServletResponse response) {
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}
}
