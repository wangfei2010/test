package com.zp.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author liuyingbo
 * @CreateTime 2015年01月08日
 */
public class DateUtil {
	
	public final static String DATE_TIME="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 将给定的日期转换为制定格式的日期字符串
	 * @param date 长整形格式的日期
	 * @param pattern 指定格式如：yyyy-MM-dd HH:mm:ss
	 * @return 指定格式的日期字符串
	 */
	public static String format(Long date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	} 
	
	/**
	 * 将给定的日期转换为如yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date 长整型格式的日期
	 * @return yyyy-MM-dd HH:mm:ss格式的日期字符串
	 */
	public static String format(Long date){
		return format(date,DATE_TIME);
	}
	
	/**
	 * 获取时间差值（相差天数）
	 * @param fromDate 开始日期
	 * @param toDate 结束日期
	 * <p>Author : WangFei</p>
	 * <p>Date : 2015年10月8日</p>
	 * @return
	 */
	public static long getDiffDays(Date fromDate , Date toDate){
		
		if(toDate == null || fromDate == null) return 0;
		
		long diff = toDate.getTime() - fromDate.getTime();

    	long days = Math.abs(diff / (1000 * 60 * 60 * 24));
    	
    	return days;
	}
	
	/**
	 * 获取时间差值（相差天数）,默认从现在时刻开始
	 * @param toDate 结束日期
	 * <p>Author : WangFei</p>
	 * <p>Date : 2015年10月8日</p>
	 * @return
	 */
	public static long getDiffDays(Date toDate){
		long diff = toDate.getTime() - new Date().getTime();
		
		long days = Math.abs(diff / (1000 * 60 * 60 * 24));
    	
    	return days;
	}
	
	/**
	 * 获取时间差值（相差天数）,默认从现在时刻开始
	 * @param toDate 结束日期,日期格式为：yyyy-MM-dd HH:mm:ss
	 * <p>Author : WangFei</p>
	 * <p>Date : 2015年10月8日</p>
	 * @return
	 */
	public static long getDiffDays(String toDate){

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date to = df.parse(toDate);
			
			long diff = to.getTime() - new Date().getTime();
			
			long days = diff / (1000 * 60 * 60 * 24);
			
			return Math.abs(days);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return -1;
	
	}
	
	/**
	 * 获取时间差值（相差天数）
	 * @param fromDate 开始日期,日期格式为：yyyy-MM-dd HH:mm:ss
	 * @param toDate 结束日期,日期格式为：yyyy-MM-dd HH:mm:ss
	 * <p>Author : WangFei</p>
	 * <p>Date : 2015年10月8日</p>
	 * @return
	 */
	public static long getDiffDays(String fromDate , String toDate){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date from = df.parse(fromDate);
			Date to = df.parse(toDate);
			
			long diff = to.getTime() - from.getTime();
			
			long days = diff / (1000 * 60 * 60 * 24);
			
			return Math.abs(days);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	private DateUtil(){}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.format(System.currentTimeMillis()));
	}

}
