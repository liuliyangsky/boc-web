package com.cherrypicks.boc.common;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author carlye
 */
public abstract class Context {

	protected Log logger = LogFactory.getLog(this.getClass());

	@PostConstruct
	public void init() {
		logger.info("init");
	}

	@PreDestroy
	public void destroy() {
		logger.info("destroy");
	}
}
