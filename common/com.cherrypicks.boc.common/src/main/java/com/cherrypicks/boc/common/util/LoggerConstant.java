package com.cherrypicks.boc.common.util;

import org.apache.log4j.Logger;

/**
 * 
 * @author carlye
 */
public class LoggerConstant {

	public static Logger getTimeInterceptorLog(Class<?> c) {
		String name = "TIMEINTERCEPTOR." + c.getName();
		Logger log = Logger.getLogger(name);
		return log;
	}

}
