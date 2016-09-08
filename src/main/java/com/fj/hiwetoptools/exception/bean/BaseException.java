package com.fj.hiwetoptools.exception.bean;

import com.fj.hiwetoptools.StringUtil;
import com.fj.hiwetoptools.exception.ExceptionUtil;

/**
 * 通用异常基类
 * @author linyu
 *
 */
public class BaseException extends RuntimeException
{
	private static final long serialVersionUID = 3624511977806718032L;

	public BaseException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public BaseException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}

	public BaseException(String message)
	{
		super(message);
	}
	
	/**
	 * 可变参数异常调用 throw new BaseException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public BaseException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public BaseException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}
}
