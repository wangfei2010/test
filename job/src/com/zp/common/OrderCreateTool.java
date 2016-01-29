package com.zp.common;

import java.util.Calendar;

public class OrderCreateTool{
	public static Long createOrderSn(){
		StringBuffer ordersn = new StringBuffer();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH)+1;
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR);
		String str = String.valueOf(year);
		str = str.substring(2);
		String months = String.valueOf(month);
		if(months.length()==1){
			months="0"+months;
		}
		
		String days = String.valueOf(day);
		if(days.length()==1){
			days="0"+days;
		}
		
		String hours = String.valueOf(hour);
		if(hours.length()==1){
			hours="0"+hours;
		}
		ordersn.append(str);
		ordersn.append(months);
		ordersn.append(days);
		ordersn.append(hours);
		String rand = UniqueStringGenerator.getUniqueString().substring(8,13);
		ordersn.append(rand);
		return Long.valueOf(ordersn.toString().substring(0,13));
	}
	
	public static Long createPaySn(){
		StringBuffer ordersn = new StringBuffer();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH)+1;
		int day = ca.get(Calendar.DATE);
		int hour = ca.get(Calendar.HOUR);
		String years = String.valueOf(year);
		years = years.substring(2);
		String months = String.valueOf(month);
		if(months.length()==1){
			months="0"+months;
		}
		
		String days = String.valueOf(day);
		if(days.length()==1){
			days="0"+days;
		}
		
		String hours = String.valueOf(hour);
		if(hours.length()==1){
			hours="0"+hours;
		}
		
		ordersn.append(years+months+days+hours);
		String rand = UniqueStringGenerator.getUniqueString().substring(11,13);
		ordersn.append(rand);
		return Long.valueOf(ordersn.toString());
	}
	public static Long createRefundOrderSn(){
		StringBuffer ordersn = new StringBuffer();
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH)+1;
		int day = ca.get(Calendar.DATE);
		
		String years = String.valueOf(year);
		years = years.substring(2);
		String months = String.valueOf(month);
		if(months.length()==1){
			months="0"+months;
		}
		
		String days = String.valueOf(day);
		if(days.length()==1){
			days="0"+days;
		}
		
		ordersn.append(years+months+days);
		String rand = UniqueStringGenerator.getUniqueString().substring(9,13);
		ordersn.append(rand);
		return Long.valueOf(ordersn.toString());
	}
}