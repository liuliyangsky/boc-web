package com.cherrypicks.boc.common.performance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author carlye
 */
public abstract class Performance extends PerformanceTemplete {

	private Log logger = LogFactory.getLog(this.getClass());

	public Performance(int runCounts, int runThreads) {
		super(runCounts, runThreads);
	}

	public Performance(int maxTime, int runThreads, int baseCount) {
		super(maxTime, runThreads, baseCount);
	}

	protected void start() {
		super.start();

		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		String name = stacks[2].toString();
		logger.info("stacks:" + name);
	}
}
