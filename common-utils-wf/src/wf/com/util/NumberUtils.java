package wf.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class NumberUtils {
	private static Logger logger = Logger.getLogger(NumberUtils.class);
	
	private static Pattern mathPattern = null;
	static {
		String mathReg = "^[-+]?[0-9]+(([Ee]?[0-9]+[.]?)|([.]?[0-9]+[Ee]?))[0-9]+$";
		mathPattern = Pattern.compile(mathReg);
	}

	/**
	 * 判断是否为数字
	 * @param obj
	 * @return
	 */
	public static boolean isNumber(String obj) {

		if (StringUtils.isEmpty(obj)) return false;

		Matcher m = mathPattern.matcher(obj);

		return m.find();
	}
	
	/**
	 * 解析整型
	 * @param obj
	 * @return
	 */
	public static int parseInt(String obj) {
		int result = 0;
		if (StringUtils.isEmpty(obj)) {
			return result;
		}

		try {
			result = Integer.parseInt(obj);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return result;
		}

		return result;
	}
	
	/**
	 * 解析布尔型
	 * @param obj
	 * @return
	 */
	public static boolean parseBoolean(Object obj) {

		boolean result = false;

		if (obj == null) return result;

		if (isNumber(obj.toString())) {
			return parseInt(obj.toString()) > 0;
		}

		return Boolean.valueOf(obj.toString());

	}
	
	/**
	 * 解析浮点型数据
	 * @param str
	 * @return
	 */
	public static double parseDbl(String str) {
		if (StringUtils.isEmpty(str)) return 0;

		double result = 0.0;
		try {
			result = Double.parseDouble(str);
		}
		catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	/**
	 * 解析浮点型
	 * @param str
	 * @return
	 */
	public static float parseFloat(String str) {
		if (StringUtils.isEmpty(str)) return 0;

		float result = 0;

		try {
			result = Float.parseFloat(str);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
