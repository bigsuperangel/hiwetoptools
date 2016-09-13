package com.fj.hiwetoptools.exception.bean;

import com.fj.hiwetoptools.StringUtil;
import com.fj.hiwetoptools.exception.ExceptionUtil;

/**
 * 文件处理异常
 */
public class FileException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public FileException(String message, Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public FileException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}

	public FileException(String message)
	{
		super(message);
	}

	/**
	 * 可变参数异常调用 throw new SysException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public FileException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public FileException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}
}
