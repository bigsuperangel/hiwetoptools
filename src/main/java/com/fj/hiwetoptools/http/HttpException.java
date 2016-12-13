package com.fj.hiwetoptools.http;


import com.fj.hiwetoptools.StrUtil;

/**
 *HTTP异常
 * @author xiaoleilu
 */
public class HttpException extends RuntimeException{

	public HttpException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public HttpException(String message) {
		super(message);
	}
	
	public HttpException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}
	
	public HttpException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public HttpException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}
}
