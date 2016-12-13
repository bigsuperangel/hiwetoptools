package com.fj.hiwetoptools.exception.bean;

import com.fj.hiwetoptools.StrUtil;
import com.fj.hiwetoptools.exception.ExceptionUtil;


public class ExcelException extends RuntimeException
{
	/**
	 * 导入导出 excel异常
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public ExcelException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public ExcelException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	public ExcelException(String message)
	{
		super(message);
	}
	
	/**
	 * 可变参数异常调用 throw new ExcelException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public ExcelException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public ExcelException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}
}
