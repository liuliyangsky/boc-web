package com.cherrypicks.boc.common.util;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author carlye
 */
public class BeanFactory {
	private static String[] filenames = { "applicationContext.xml" };

	private static AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(filenames);

	public static AbstractApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> className) {
		return applicationContext.getBean(className);
	}

	public static void shutDown() {
		applicationContext.registerShutdownHook();
	}

	public static void autowireBeanPropertiesByName(Object existingBean) {
		applicationContext.getAutowireCapableBeanFactory().autowireBeanProperties(existingBean, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
	}
}
