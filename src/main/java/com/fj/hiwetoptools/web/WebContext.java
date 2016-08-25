package com.fj.hiwetoptools.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebContext {
	/**
     * 使每个线程拥有各自的 webContext 实例
     */
    private static final ThreadLocal<WebContext> webContextContainer = new ThreadLocal<WebContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    /**
     * 初始化
     */
    public static void init(HttpServletRequest request, HttpServletResponse response) {
    	WebContext webContext = new WebContext();
        webContext.request = request;
        webContext.response = response;
        webContextContainer.set(webContext);
    }

    /**
     * 销毁
     */
    public static void destroy() {
        webContextContainer.remove();
    }

    /**
     * 获取 webContext 实例
     */
    public static WebContext getInstance() {
        return webContextContainer.get();
    }

    /**
     * 获取 Request 对象
     */
    public static HttpServletRequest getRequest() {
        return getInstance().request;
    }

    /**
     * 获取 Response 对象
     */
    public static HttpServletResponse getResponse() {
        return getInstance().response;
    }

    /**
     * 获取 Session 对象
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

}
