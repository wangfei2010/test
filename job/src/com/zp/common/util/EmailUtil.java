package com.zp.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class EmailUtil {
	
	public static void sendEmail(String mailContent,String title,String mail){
		
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			map.put("title", title);
			map.put("mail", mail);
			map.put("mailContent", mailContent);
			String executePost = HttpClientUtil.getInstance().executePost(CustomPro.getValue("send_email_url")+"zpapi/msg/msgEmail.json", map);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
