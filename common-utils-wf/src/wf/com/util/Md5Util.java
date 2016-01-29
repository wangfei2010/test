package wf.com.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Md5Util {
	private static Logger logger = Logger.getLogger(Md5Util.class);

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
				if (i < 0) i += 256;
				if (i < 16) buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return new String(buf.toString());
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String getPasswordMd5(String password, String salt) {
		String ret = Md5Util.MD5(Md5Util.MD5(password).concat(salt));
		return ret;

	}

	public static String getPasswordMd5(String password) {
		String ret = Md5Util.MD5(password);
		return ret;

	}

}