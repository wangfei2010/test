package com.zp.commons.error;

public class MailCode extends ErrorCode{

	/**
	 * 001 收件人地址为空
	 */
	public static final MailCode mail_001 = new MailCode(001,"收件人地址为空");
	
	/**
	 * 002 邮件附件不存在
	 */
	public static final MailCode mail_002 = new MailCode(002,"附件不存在");
	
	protected MailCode(int code, String codeText) {
		super(code, codeText);
	}

}
