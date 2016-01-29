package wf.com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取时间差值（相差天数）
	 * @param fromDate 开始日期
	 * @param toDate 结束日期
	 * @author WangFei
	 * @return
	 */
	public static long getDiffDays(Date fromDate, Date toDate) {

		if (toDate == null || fromDate == null) return 0;

		long diff = toDate.getTime() - fromDate.getTime();

		long days = Math.abs(diff / (1000 * 60 * 60 * 24));

		return days;
	}

	/**
	 * 获取时间差值（相差天数）,默认从现在时刻开始
	 * @param toDate 结束日期
	 * @author WangFei
	 * @return
	 */
	public static long getDiffDays(Date toDate) {
		long diff = toDate.getTime() - new Date().getTime();

		long days = Math.abs(diff / (1000 * 60 * 60 * 24));

		return days;
	}

	/**
	 * 获取时间差值（相差天数）,默认从现在时刻开始
	 * @param toDate 结束日期,日期格式为：yyyy-MM-dd HH:mm:ss
	 * @author WangFei
	 * @return
	 */
	public static long getDiffDays(String toDate) {

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
	 * @author WangFei
	 * @return
	 */
	public static long getDiffDays(String fromDate, String toDate) {

		try {
			Date from = sf.parse(fromDate);
			Date to = sf.parse(toDate);

			long diff = to.getTime() - from.getTime();

			long days = diff / (1000 * 60 * 60 * 24);

			return Math.abs(days);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return -1;
	}

	/**
	 * 将给定的日期转换为制定格式的日期字符串
	 * @param date 长整形格式的日期
	 * @param pattern 指定格式如：yyyy-MM-dd HH:mm:ss
	 * @return 指定格式的日期字符串
	 */
	public static String format(Long date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将给定的日期转换为如yyyy-MM-dd HH:mm:ss格式的字符串
	 * @param date 长整型格式的日期
	 * @return yyyy-MM-dd HH:mm:ss格式的日期字符串
	 */
	public static String format(Long date) {
		return format(date, DATE_TIME);
	}

	/**
	 * 将给定的日期转换为如yyyy-MM-dd格式的字符串
	 * @param date 长整型格式的日期
	 * @return yyyy-MM-dd HH:mm:ss格式的日期字符串
	 */
	public static String format() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String format = sf.format(date);
		return format;
	}

	/**
	 * 当前时间的时间戳
	 * @return
	 * @throws ParseException
	 */
	public static Integer nowTimeStamp() {
		Long nowTime = System.currentTimeMillis() / 1000;
		return Integer.valueOf(nowTime.toString());
	}

	public static String getMessageTime() {
		Calendar cal = Calendar.getInstance();
		int millis = cal.getTimeZone().getRawOffset();
		String sign;
		if (millis < 0) {
			sign = "-";
			millis = millis * (-1);
		}
		else {
			sign = "+";
		}
		int hr = (millis / 60000) / 60;
		int min = (millis / 60000) % 60;
		String hrs = "" + hr;
		String ms = "" + min;
		if (min < 10) {
			ms = "0" + ms;
		}
		if (hr < 10) {
			hrs = "0" + hrs;
		}
		String gmtOffset = sign + hrs + ":" + ms;
		String messageTime = sf.format(new java.util.Date()) + gmtOffset;
		return messageTime;
	}

	/**
	 * 返回相应格式的日期字符串
	 * DateUtil.getDateString()<BR>
	 * @author WangFei
	 * @param date
	 * @param formatPattern
	 * @return
	 */
	public static String getDateString(Date date, String formatPattern) {
		if (date == null) {
			return "";
		}
		if ((formatPattern == null) || formatPattern.equals("")) {
			formatPattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(formatPattern);
		return sdf.format(date);
	}

	/**
	 * 根据不同pattern解析日期字符串
	 * DateUtil.parseString2Date()<BR>
	 * @author WangFei
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseString2Date(String dateStr, String pattern) throws ParseException {
		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		date = sdf.parse(dateStr);
		return date;
	}

	public static long DateString2Timestamp(String datestr, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = new Date();
		try {
			date = dateFormat.parse(datestr);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		int timestamp = (int) (date.getTime() / 1000);
		return timestamp;
	}

	public static int getTimestamp(Date date) {
		if (date == null) {
			return 0;
		}
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(date);
		int cur = (int) (calendar.getTimeInMillis() / 1000);
		return cur;
	}

	/**
	 * 获取两个指定日期相差的天数.
	 * @param firstDate .
	 * @param secondDate .
	 * @return .
	 */
	public static int getBetweenDay(Date firstDate, Date secondDate) {
		// 时间格式相同，获取两时间差的秒数
		long betweendDateBySeconds = firstDate.getTime() - secondDate.getTime();
		// 得到天数
		return (int) (Math.abs(betweendDateBySeconds / (1000 * 3600 * 24)));
	}

	/**
	 * 增加日期
	 * @param date
	 * @return
	 */
	public static Date addDate(Date date, int dateField, int dateAmount) {
		if (date == null) {
			date = new Date();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(dateField, dateAmount);

		return calendar.getTime();
	}

	/**
	 * 判断 firstDate 是否在 secondDate 之前
	 * @param firstDate .
	 * @param secondDate .
	 * @return firstDate 在 secondDate 之前返回 true，否则返回 false.
	 */
	public static boolean isBefore(Date firstDate, Date secondDate) {
		if ((firstDate == null) || (secondDate == null)) {
			return false;
		}

		return firstDate.compareTo(secondDate) < 0;
	}

	public static boolean isAfter(Date firstDate, Date secondDate) {
		if ((firstDate == null) || (secondDate == null)) {
			return false;
		}

		return firstDate.compareTo(secondDate) > 0;
	}

	private DateUtil() {
	}
}
