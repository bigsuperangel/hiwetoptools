package com.fj.hiwetoptools.exception.bean;


public class ExcelException extends BaseException
{
	/**
	 * 导入导出 excel异常
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public ExcelException(String message, Exception cause)
	{
		super(message, cause);
	}

	public ExcelException(String message)
	{
		super(message);
	}

	public ExcelException(Exception cause) {
		super(cause);
	}
}
