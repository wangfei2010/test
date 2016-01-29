package com.zp.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private static Map<String, Properties> propsMap = new HashMap<String, Properties>();

	/**
	 * 
	 * @Title: getProperty
	 * @Description: 基于Spring读取Properties的文件，返回属性值
	 * @param @param
	 *            propertyKey 要获取属性值对应的键名
	 * @param @param
	 *            propertyFileName 属性文件名
	 * @param @return
	 * @return String 属性值
	 * @throws @author
	 *             zhupengren
	 * @date 2014年4月17日 下午5:55:13
	 */
	public static String getProperty(String propertyKey, String propertyFileName) {
		if (propsMap.containsKey(propertyFileName)) {
			return propsMap.get(propertyFileName).getProperty(propertyKey);
		}
		String value = null;
		try {
			Properties props = getProperties(propertyFileName);
			value = props.getProperty(propertyKey);
			propsMap.put(propertyFileName, props);
		} catch (IOException e) {
			System.out.println(propertyFileName + "文件未找到!=========================");
			e.printStackTrace();
		}
		return value;
	}

	public static String setProperty(String propertyKey, String propertyValue, String propertyFileName) {
		if (propsMap.containsKey(propertyFileName)) {
			return propsMap.get(propertyFileName).getProperty(propertyKey);
		}
		String value = null;
		try {
			Properties props = getProperties(propertyFileName);
			props.setProperty(propertyKey, propertyValue);
			props.store(new FileOutputStream(propertyFileName), "");

			propsMap.put(propertyFileName, props);
		} catch (IOException e) {
			System.out.println(propertyFileName + "文件未找到!=========================");
			e.printStackTrace();
		}
		return value;
	}

	public static Properties getProperties(String fileName) throws IOException {
		Properties p = null;
		try {
			p = new Properties();
			p.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
