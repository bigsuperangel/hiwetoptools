package com.fj.hiwetoptools.exception.bean;

public class BaseException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 3624511977806718032L;

	public BaseException(String message, Exception cause)
	{
		super(message, cause);
	}

	public BaseException(String message)
	{
		super(message);
	}

	public BaseException(Exception cause)
	{
		super(cause);
	}
}
