package com.cherrypicks.boc.common.util;

import org.apache.commons.lang.StringUtils;

import com.cherrypicks.boc.common.exception.InvalidArgumentException;

/**
 * 参数验证
 * 
 * @author carlye
 */
public class AssertUtil {

	public static void assertNotNull(Object obj, String message) {
		assertTrue(obj != null, message);
	}

	public static void assertNotEmpty(String str, String message) {
		assertTrue(StringUtils.isNotEmpty(str), message);
	}

	public static void assertNotBlank(String str, String message) {
		assertTrue(StringUtils.isNotBlank(str), message);
	}

	public static void assertTrue(boolean flag, String message) {
		if (!flag) {
			throw new InvalidArgumentException(message);
		}
	}

}
