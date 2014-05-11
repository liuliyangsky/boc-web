package com.cherrypicks.boc.utils;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String[] split(String str1, String str2) {
		return org.apache.commons.lang.StringUtils.split(str1, str2);
	}

	public static String replace(String src, String target, String rWith) {
		return org.apache.commons.lang.StringUtils.replace(src, target, rWith);
	}

	public static String replace(String src, String target, String rWith, int maxCount) {
		return org.apache.commons.lang.StringUtils.replace(src, target, rWith, maxCount);
	}

	public static boolean equals(String str1, String str2) {
		return org.apache.commons.lang.StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str) {
		return org.apache.commons.lang.StringUtils.isAlphanumeric(str);
	}

	public static String[] stripAll(String[] strs) {
		return org.apache.commons.lang.StringUtils.stripAll(strs);
	}

	public static String left(String str, int length) {
		return org.apache.commons.lang.StringUtils.left(str, length);
	}

	public static Map<String, Integer> split(String content, String... split) {
		if (StringUtil.isEmpty(content)) {
			return null;
		}

		Map<String, Integer> contentMap = new HashMap<String, Integer>();
		final String[] strArr = content.split(split[0]);
		for (final String str : strArr) {
			final String[] tmp = str.split(split[1]);
			contentMap.put(tmp[0], Integer.valueOf(tmp[1]));
		}
		return contentMap;
	}
}
