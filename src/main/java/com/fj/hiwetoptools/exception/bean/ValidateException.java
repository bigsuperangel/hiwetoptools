package com.fj.hiwetoptools.exception.bean;

public class ValidateException extends BaseException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public ValidateException(String message, Exception cause)
	{
		super(message, cause);
	}

	public ValidateException(String message)
	{
		super(message);
	}

	public ValidateException(Exception cause) {
		super(cause);
	}
}

