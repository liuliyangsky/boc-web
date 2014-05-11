package com.cherrypicks.boc.common.exception;

import com.cherrypicks.boc.common.util.CodeStatus;

public class InvalidArgumentException extends BOCAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidArgumentException() {
		super();
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	@Override
	public int getErrorCode() {
		return CodeStatus.ILLEGAL_ARGUMENT;
	}

}
