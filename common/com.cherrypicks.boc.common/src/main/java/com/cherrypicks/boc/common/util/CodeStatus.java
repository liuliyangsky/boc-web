package com.cherrypicks.boc.common.util;

/**
 * web service 系统错误状态码
 * 
 */
public class CodeStatus {
    public static final int SUCCESS_STATUS=1;
    
    public static final int FAILED_STATUS=0;
	// success
	public static final int SUCCESS = 0;
	// internal server error
	public static final int UNKNOWN = -1;
	// Input parameter error
	public static final int ILLEGAL_ARGUMENT = -2;
	
	public static final int JOSN_EXCEPTION=-3;
	
	public static final int FILTER_EXCEPTION=-4;
}
