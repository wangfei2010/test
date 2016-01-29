package wf.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtils extends org.apache.commons.lang.StringUtils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static Logger logger = Logger.getLogger(StringUtils.class);

	/**
	 * @Title: toUnicode
	 * @Description: 字符串中的非ascii码转换成unicode形式
	 * @param inStr
	 * @return
	 * @author WangFei
	 * @date 2014年10月31日 上午11:49:53
	 */
	public static String toUnicode(String inStr) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {
			int ch = (int) inStr.charAt(i);
			if (ch > 255)
				str.append("\\u").append(Integer.toHexString(ch));
			else
				str.append((char) ch);
		}
		return str.toString();
	}

	/**
	 * @Title: toMap
	 * @Description: 把类似http get请求格式的字符串格式化成Map<String,String>
	 * @param inStr 要格式化的字符串
	 * @param regex1 外层切分表达式
	 * @param regex2 二次切分表达式
	 * @return
	 * @author WangFei
	 * @date 2015年3月10日 上午11:32:45
	 */
	public static Map<String, String> toMap(String inStr, String regex1, String regex2) {
		String[] ins = split(inStr, regex1);
		if (ins != null && ins.length > 0) {
			Map<String, String> re = new HashMap<String, String>(ins.length);
			for (int i = 0; i < ins.length; i++) {
				String[] ts = split(ins[i], regex2);
				if (ts != null && ts.length > 0) {
					if (ts.length == 1) {
						re.put(ts[0], null);
					}
					else if (ts.length == 2) {
						re.put(ts[0], ts[1]);
					}
					else {
						StringBuffer tempValue = new StringBuffer();
						for (int j = 1; j < ts.length; j++) {
							tempValue.append(ts[j]).append(regex2);
						}
						re.put(ts[0], tempValue.substring(0, tempValue.length() - regex2.length()));
					}
				}
			}
			return re;
		}
		return null;
	}

	/**
	 * @Title: fromMap
	 * @Description: 把Map<Object,Object>格式化成类似http get请求格式的字符串
	 * @param param
	 * @return
	 * @author WangFei
	 * @date 2015年3月10日 下午1:18:45
	 */
	public static <E extends Object> String fromMap(Map<E, E> param) {
		if (param == null || param.isEmpty()) {
			return null;
		}
		Set<E> keySet = param.keySet();
		Iterator<E> it = keySet.iterator();
		StringBuffer sb = new StringBuffer();
		Object key = null;
		while (it.hasNext()) {
			key = it.next();
			sb.append(key.toString()).append("=").append(String.valueOf(param.get(key))).append("&");
		}
		return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : null;
	}

	/**
	 * @Title: isEmpty
	 * @Description: 覆盖commons.lang里的isEmpty方法，过滤空字符串
	 * @param str
	 * @return
	 * @author WangFei
	 * @date 2014年11月24日 下午5:13:37
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.replaceAll(" ", "").length() == 0;
	}

	/**
	 * @Title: isEmpty
	 * @Description: 判断一组String是否为空，若有任一个为空，返回true，否则返回false
	 * @param str
	 * @return
	 * @author WangFei
	 * @date 2014年11月24日 下午5:19:31
	 */
	public static boolean isEmpty(String... str) {
		for (String s : str) {
			if (isEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: isNotEmpty
	 * @Description: 判断一组String是否不为空，若有任一个为空，返回false，否则返回true
	 * @param str
	 * @return
	 * @author WangFei
	 * @date 2014年11月24日 下午5:23:42
	 */
	public static boolean isNotEmpty(String... str) {
		for (String s : str) {
			if (isEmpty(s)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @Title: timeStampToDateTime
	 * @Description: 毫秒时间戳转换为 “yyyy-MM-dd HH:mm:ss”格式的字符串
	 * @param timeStamp
	 * @return
	 * @author WangFei
	 * @date 2015年4月17日 上午11:54:34
	 */
	public static String timeStampToDateTime(long timeStamp) {
		try {
			return sdf.format(new Date(timeStamp));
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return String.valueOf(timeStamp);
		}
	}

	/**
	 * @Title: unixTimeStampToDateTime
	 * @Description: unix(秒)时间戳转换为 “yyyy-MM-dd HH:mm:ss”格式的字符串
	 * @param timeStamp
	 * @return
	 * @author WangFei
	 * @date 2015年4月17日 下午1:10:53
	 */
	public static String unixTimeStampToDateTime(int timeStamp) {
		try {
			return timeStampToDateTime(timeStamp * 1000L);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return String.valueOf(timeStamp);
		}
	}

	public static String timeStampToDate(long timeStamp) {
		try {
			return date_sdf.format(new Date(timeStamp));
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return String.valueOf(timeStamp);
		}
	}

	public static Date dateStringToDate(String dateStr) throws ParseException {
		return date_sdf.parse(dateStr);
	}

	/***
	 * 过滤表情
	 * @param source
	 * @return
	 */
	public static boolean SpecialCharaterFilter(String str) {

		if (!str.replaceAll("\\s*", "").matches("[0-9A-Za-z_\\u4e00-\u9fa5~!@#$%^&*()~！+@#￥%……&*（）“;',./{}:\"<>?【】；‘’”；，。、{}：“《》？\\[\\]+-=]*")) {
			return false;
		}
		else {
			return true;
		}

	}

	/***
	 * 邮箱验证
	 */
	public static boolean isEmail(String email) {
		String check = "^[0-9a-z_][_.0-9a-z-]{0,32}@([0-9a-z][0-9a-z-]{0,32}.){1,4}[a-z]{2,4}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	/**
	 * 解析字符串
	 * @param obj
	 * @return
	 */
	public static String parseStr(Object obj) {
		if (obj == null) return "";

		if (obj.toString().equalsIgnoreCase("null")) return "";

		return obj.toString();
	}

	// 根据Unicode编码完美的判断中文汉字和符号
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	/**
	 * 完整的判断中文汉字和符号
	 * @param input
	 * @return
	 */
	public static boolean isChinese(String input) {
		char[] ch = input.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 创建唯一GUID
	 * @return
	 */
	public static String newGuid() {
		return UUID.randomUUID().toString();
	}
}
