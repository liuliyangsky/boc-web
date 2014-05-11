package com.cherrypicks.boc.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {

	private static Properties properties = new Properties();
	private static Log LOGGER = LogFactory.getLog(PropertiesUtil.class);

	private PropertiesUtil() {
	}

	static {
		loadFile("config.properties");
	}

	private static void loadFile(String filename) {
		try {
			properties.load(PropertiesUtil.class.getResourceAsStream("/" + filename));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getProperty("redis0.host"));
	}
}
