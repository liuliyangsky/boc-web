package com.cherrypicks.boc.common.exception;

import com.cherrypicks.boc.common.util.CodeStatus;

public  class BOCAPIException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7065579710316348767L;

	private int errorCode = CodeStatus.UNKNOWN;
	public int getErrorCode() {
		return errorCode;
	}

	public BOCAPIException() {
		super();
	}

	public BOCAPIException(String message, Throwable cause) {
		super(message, cause);
	}

	public BOCAPIException(String message) {
		super(message);
	}

	public BOCAPIException(Throwable cause) {
		super(cause);
	}

}
