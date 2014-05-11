package com.cherrypicks.boc.common.jms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ErrorHandler;

public class RequestErrorHandler implements ErrorHandler {

	Log log = LogFactory.getLog(RequestErrorHandler.class);
	
	public void handleError(Throwable t) {
		//t.printStackTrace();
		log.error(t);
	}

}
