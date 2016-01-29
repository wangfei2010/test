package com.zp.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map.Entry;

public  class RequestService {
	
	private RequestService(){
		
	}
	/*
	 * ����ӿ�
	 */
	public static String Request(String url,TreeMap<String,String> params) throws Exception{
		String key = PropertiesUtil.getProperty("appkey", "wuse.properties");
		String password = PropertiesUtil.getProperty("password", "wuse.properties");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		
		params.put("timestamp", date);
		params.put("appkey", key);
		
		String content = StringBuildContent(params, password);
		
		StringBuilder response = new StringBuilder();
		try{
			
			URL postUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);                 
			connection.setRequestMethod("POST"); 
			connection.setUseCaches(false); 
			connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded"); 
			
			DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
			out.writeBytes(content);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			
			String line;
			while((line = reader.readLine())!=null){
			
				response.append(line);
			}

		}
		catch(Exception e){
			throw e;
		}
		System.out.println("*************response:"+response);
		return response.toString();
	}
	
	public static String StringBuildContent(TreeMap<String, String> params,String password) throws UnsupportedEncodingException{
		StringBuilder content = new StringBuilder();
		
		Iterator<Entry<String,String>> it = params.entrySet().iterator();
		
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			
			content.append(entry.getKey()+"="+ URLEncoder.encode(entry.getValue(),"utf-8") + "&");
		}
		content.append("sign="+ SignData(params, password));
		
		return content.toString();
	}
	/*
	 * ���ǩ��
	 */
	public static String SignData(TreeMap<String,String> params,String password){
		
		StringBuilder sb = new StringBuilder();

		sb.append(password);
		Iterator<Entry<String,String>> it = params.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> entry = it.next();
			sb.append(""+entry.getKey()+""+entry.getValue()+"");
		}
		sb.append(password);
		System.out.println(sb.toString());
		
		String md5Text = Md5(sb.toString());
		
		return md5Text.toUpperCase();
	}
	private static String Md5(String str){
		MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
            md5.update(str.getBytes("utf-8"));
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        byte[] bytes = md5.digest();
        StringBuilder stringBuilder  = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {   
            int v = bytes[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
	}
}
