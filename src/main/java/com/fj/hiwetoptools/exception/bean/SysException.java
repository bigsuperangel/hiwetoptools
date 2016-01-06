package com.fj.hiwetoptools.exception.bean;


public class SysException extends BaseException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public SysException(String message, Exception cause)
	{
		super(message, cause);
	}

	public SysException(String message)
	{
		super(message);
	}

	public SysException(Exception cause) {
		super(cause);
	}
}
