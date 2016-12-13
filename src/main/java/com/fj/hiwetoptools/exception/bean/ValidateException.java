package com.fj.hiwetoptools.exception.bean;

import com.fj.hiwetoptools.StrUtil;
import com.fj.hiwetoptools.exception.ExceptionUtil;

public class ValidateException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public ValidateException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public ValidateException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	public ValidateException(String message)
	{
		super(message);
	}
	
	/**
	 * 可变参数异常调用 throw new ValidateException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public ValidateException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public ValidateException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}

}

