package com.cherrypicks.boc.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 
 * @author carlye
 */
public class BeanUtil {
	public static <T> T instantiateClass(Class<T> clazz, Object... params) {
		Class<?>[] parameterTypes = new Class<?>[params.length];
		for (int i = 0; i < params.length; i++) {
			parameterTypes[i] = params[i].getClass();
		}
		try {
			Constructor<T> ctor = clazz.getConstructor(parameterTypes);
			T bean = ctor.newInstance(params);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static boolean isNull(Object bean) {
		return bean == null;
	}

	public static boolean isNotNull(Object bean) {
		return bean != null;
	}

	// @SuppressWarnings("unchecked")
	// public static <T> T getFieldValue(Object bean, String fieldName) {
	// try {
	// return (T) BeanUtils.getProperty(bean, fieldName);
	// }
	// catch (Exception e) {
	// throw new RuntimeException(e.getMessage(), e);
	// }
	// }

	public static Object getPropertyValue(Object model, String fieldName) {
		if (model == null) {
			return null;
		}
		try {
			Field field = getField(model, fieldName);
			field.setAccessible(true);
			return field.get(model);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(Object bean, String fieldName) {
		try {
			Field field = getField(bean, fieldName);
			field.setAccessible(true);
			return field.get(bean);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static Field getField(Object bean, String fieldName) {
		AssertUtil.assertNotNull(bean, "参数bean不能为空。");
		Field field = null;
		Class<?> clazz = bean.getClass();
		while (true) {
			try {
				field = clazz.getDeclaredField(fieldName);
				return field;
			} catch (NoSuchFieldException e) {
				if (clazz.getSuperclass() == null) {
					String className = bean.getClass().getSimpleName();
					throw new RuntimeException("clazz:" + className + " " + e.getMessage(), e);
				}
				clazz = clazz.getSuperclass();
				// field = getFieldByClass(clazz, fieldName);
			}

		}

	}

	public static void setFieldValue(Object bean, String fieldName, Object value) {
		try {
			Field field = bean.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(bean, value);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

	}
}
