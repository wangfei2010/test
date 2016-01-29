package com.zp.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author Administrator
 * @desc   得到数据签名
 */
public class SignUtil {

	public static String getSign(TreeMap<String,Object> params){
		StringBuffer sb = new StringBuffer();
		long currentTimeMillis = System.currentTimeMillis();
		String date = DateUtil.format(currentTimeMillis);
		String appkey = PropertiesUtil.getProperty("appkey", "wuse.properties");
		String timestamp = date;
		params.put("appkey", appkey);
		params.put("timestamp", timestamp);
		Iterator<Map.Entry<String, Object>> entries = params.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<String, Object> entry = entries.next();
		    sb.append(entry.getKey());
		    sb.append(entry.getValue());
		}
		String str = sb.toString();
		System.out.println("pass:"+PropertiesUtil.getProperty("password", "wuse.properties")+str+PropertiesUtil.getProperty("password", "wuse.properties")); 
		String result = Md5Util.md5(PropertiesUtil.getProperty("password", "wuse.properties")+str+PropertiesUtil.getProperty("password", "wuse.properties")).toUpperCase();
		System.out.println("===============str"+str);
		System.out.println("===============md5"+result);
		params.remove("appkey");
		params.remove("timestamp");
		return result;
	}
	public static void main(String[] args) {
//		Md5Util.md5Second(str)
	}
}
