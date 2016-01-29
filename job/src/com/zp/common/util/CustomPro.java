package com.zp.common.util;

import java.util.Properties;

public class CustomPro {
	private static Properties cache = new Properties();
	static{
		try {
			cache.load(CustomPro.class.getClassLoader().getResourceAsStream("customs.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key){
		return cache.getProperty(key);
	}
}
