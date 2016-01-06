package com.fj.hiwetoptools.exception;



/**
 * 不被捕获的异常,将抛至最顶层.
 *
 */
@SuppressWarnings("serial")
public class UnCaughtException extends BaseException {

	public UnCaughtException(String message, Exception cause) {
		super(message, cause);
	}

	public UnCaughtException(String message) {
		super(message);
	}

	public UnCaughtException(Exception cause) {
		super(cause);
	}

}
