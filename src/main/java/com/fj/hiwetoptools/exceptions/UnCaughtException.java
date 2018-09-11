package com.fj.hiwetoptools.exceptions;

import com.fj.hiwetoptools.util.StrUtil;
import cn.hutool.core.exceptions.ExceptionUtil;

/**
 * 不被捕获的异常,将抛至最顶层.
 *
 */
@SuppressWarnings("serial")
public class UnCaughtException extends RuntimeException {

	public UnCaughtException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public UnCaughtException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	public UnCaughtException(String message)
	{
		super(message);
	}
	
	/**
	 * 可变参数异常调用 throw new UnCaughtException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public UnCaughtException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public UnCaughtException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}

}
