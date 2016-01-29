package com.zp.common.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class FormatUtil{
	public static String formatTimetoDate(Integer time){
		SimpleDateFormat fm2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); 
		long unixLong = 0; 
		unixLong = Long.valueOf(time) * 1000; 
		String date = fm2.format(unixLong);
		return date; 
	}
	public static int getRandom(){
		Random random = new Random(System.currentTimeMillis());
		int ret=random.nextInt(89999999)+10000000;//(10000000,99999999)
		return ret;
	}
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }

}