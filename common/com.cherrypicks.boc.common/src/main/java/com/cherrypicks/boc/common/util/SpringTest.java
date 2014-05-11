package com.cherrypicks.boc.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = { "/applicationTestContext.xml" })
public abstract class SpringTest extends AbstractTestNGSpringContextTests {
	protected Log logger = LogFactory.getLog(this.getClass());
}