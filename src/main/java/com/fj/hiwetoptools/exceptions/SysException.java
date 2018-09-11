package com.fj.hiwetoptools.exceptions;

import com.fj.hiwetoptools.util.StrUtil;
import cn.hutool.core.exceptions.ExceptionUtil;


public class SysException extends RuntimeException
{
	/**
	 *
	 */
	private static final long serialVersionUID = 4304920561531709959L;

	public SysException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	/**
	 * 可变参数异常
	 * @param throwable
	 * @param messageTemplate
	 * @param params
	 */
	public SysException(Throwable throwable, String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params), throwable);
	}

	public SysException(String message)
	{
		super(message);
	}
	
	/**
	 * 可变参数异常调用 throw new SysException("test{}","test")
	 * @param messageTemplate
	 * @param params
	 */
	public SysException(String messageTemplate, Object... params) {
		super(StrUtil.format(messageTemplate, params));
	}

	public SysException(Throwable cause)
	{
		super(ExceptionUtil.getMessage(cause),cause);
	}
}
