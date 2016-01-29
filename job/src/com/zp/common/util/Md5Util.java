package com.zp.common.util;

import java.security.MessageDigest;
import java.util.Arrays;


public class Md5Util {
	
	  public static String md5(String data){
		    try{
		      byte[] md5 = md5(data.getBytes("utf-8"));
		      return toHexString(md5);
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    return "";
	  }
	  
	  public static String md5Second(String str){
		    try{
		      String md5String= md5(str);
		      byte[] md5Second = md5(md5String.getBytes("utf-8"));
		      return toHexString(md5Second);
		    }catch(Exception e){
		      e.printStackTrace();
		    }
		    return "";
	}
		  
	  
	  public static byte[] md5(byte[] data){
	    try{
	    MessageDigest md = 
	      MessageDigest.getInstance("md5");
	    md.update(data);
	    return md.digest();
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	    return new byte[1];
	  }
	  public static String toHexString(byte[] md5) {
	    StringBuilder buf = new StringBuilder();
	    for (byte b : md5) {
	      buf.append(leftPad(
	          Integer.toHexString(b&0xff), '0', 2));
	    }
	    return buf.toString();
	  }
	  public static String leftPad(
	      String hex, char c, int size) {
	    char[] cs = new char[size];
	    Arrays.fill( cs, c);
	    System.arraycopy(hex.toCharArray(), 0, 
	        cs, cs.length-hex.length(), hex.length());
	    return new String(cs);
	  }
	
    private final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            int i;
            StringBuffer buf = new StringBuffer("");     
            for (int offset = 0; offset < md.length; offset++) {
             i = md[offset];
             if(i<0) i+= 256;
             if(i<16)
              buf.append("0");
             buf.append(Integer.toHexString(i));
            }
            return new String(buf.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public String getPasswordMd5(String password){
		String ret = Md5Util.MD5(password);
		return ret;
    	
    }
    

}