package com.zp.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.zp.zpquartz.model.QrtzLogError;
import com.zp.zpquartz.service.QrtzLogErrorManager;
/**
 * 
 * @author liu
 *
 */
public class ErrorUtil {
	/**
	 * 
	 * @param e 
	 * @param qrtzLogErrorManager
	 * @param sendEmail
	 */
	public static void createError(Exception e,QrtzLogErrorManager qrtzLogErrorManager,String className, boolean sendEmail){
		QrtzLogError qrtzLogError = new QrtzLogError();
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
		String error = pw.toString();
		qrtzLogError.setError(error.length() > 500 ? error.substring(0,500) : error);
		qrtzLogError.setJobname(className);
		qrtzLogErrorManager.save(qrtzLogError);
		// 发送邮件
		if(sendEmail == true){
			EmailUtil.sendEmail(error.length() > 500 ? error.substring(0,500) : error, className+"error", "T.Calvin@zhenpin.com,liutaoryutou@zhenpin.com");
		}
		
		
	}

}
