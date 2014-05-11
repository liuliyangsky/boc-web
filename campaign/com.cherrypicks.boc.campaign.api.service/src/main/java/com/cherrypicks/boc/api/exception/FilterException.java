package com.cherrypicks.boc.api.exception;

import com.cherrypicks.boc.common.exception.BOCAPIException;


/**
 * 过滤层异常
 * 
 * @author edwin.zhou
 *
 */
public class FilterException extends BOCAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3796340342119449661L;
	
	private int errorCode = ExceptionCodeStatusEnum.FILTER_EXCEPTION.toInt();

	public int getErrorCode() {
		return errorCode;
	}
	
	public FilterException(String message) {
		super(message);
	}
	
	public FilterException(String message , int errorCode) {
		super(message);
	}
	
}
