package wf.com.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class TransUtil {
	private static Logger logger = Logger.getLogger(TransUtil.class);

	public static String translation(String translate) {

		String obj = "";
		try {
			translate = new String((translate).getBytes("UTF-8"), "UTF-8");
			String url = "http://fanyi.youdao.com/openapi.do";
			Map<String, String> paramaters = new HashMap<String, String>();
			paramaters.put("keyfrom", "zpwyxgs");
			paramaters.put("key", "73613364");
			paramaters.put("type", "data");
			paramaters.put("doctype", "json");
			paramaters.put("version", "1.1");
			paramaters.put("q", translate);
			try {
				String executeGet = HttpClientUtil.getInstance().executeGet(url, paramaters);
				Map<String, Object> translation = JSONUtils.json2map(executeGet);
				Object object = translation.get("translation");
				obj = object.toString();
				if (obj.indexOf("[") != -1) {
					obj = obj.substring(1, obj.length() - 1);
				}
			}
			catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return obj;

	}
	
	public static void main(String[] args) {
		System.out.println(translation(""));
	}

}
