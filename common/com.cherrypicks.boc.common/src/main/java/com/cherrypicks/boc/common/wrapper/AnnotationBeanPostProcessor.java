package com.cherrypicks.boc.common.wrapper;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ReflectionUtils;

public class AnnotationBeanPostProcessor extends PropertyPlaceholderConfigurer implements BeanPostProcessor, InitializingBean {
	private static transient Log logger = LogFactory.getLog(AnnotationBeanPostProcessor.class);

	private java.util.Properties pros;

	@SuppressWarnings("rawtypes")
	private Class[] enableClassList = { String.class, int.class };

	@SuppressWarnings("rawtypes")
	public void setEnableClassList(Class[] enableClassList) {
		this.enableClassList = enableClassList;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@SuppressWarnings("rawtypes")
	private boolean filterType(String type) {

		if (type != null) {
			for (Class c : enableClassList) {
				if (c.toString().equals(type)) {
					return true;
				}
			}

			return false;
		} else {
			return true;
		}
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		Field[] fields = bean.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (logger.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder();
				sb.append(" ========= ").append(field.getType()).append(" ============ ").append(field.getName()).append(" ============ ").append(field.isAnnotationPresent(Properties.class));

				logger.debug(sb.toString());
			}

			if (field.isAnnotationPresent(Properties.class)) {
				if (filterType(field.getType().toString())) {
					Properties p = field.getAnnotation(Properties.class);
					try {
						// StringBuilder sb = new StringBuilder();
						// sb.append("set").append(StringUtils.upperCase(field.getName().substring(0,
						// 1)))
						// .append(field.getName().substring(1,
						// field.getName().length()));
						//
						// Method method =
						// bean.getClass().getMethod(sb.toString(),
						// String.class);
						// method.invoke(bean, pros.getProperty(p.name()));

						ReflectionUtils.makeAccessible(field);

						if (int.class.toString().equals(field.getType().toString())) {
							field.set(bean, Integer.valueOf(pros.getProperty(p.name())));
						} else {
							field.set(bean, pros.getProperty(p.name()));
						}

					} catch (Exception e) {
						logger.error(" --- ", e);
					}
				}
			}
		}

		return bean;
	}

	public void afterPropertiesSet() throws Exception {
		pros = mergeProperties();
	}

	public void init() {
		logger.info("init");
	}

	public void destroy() {
		logger.info("destroy");
	}
}
