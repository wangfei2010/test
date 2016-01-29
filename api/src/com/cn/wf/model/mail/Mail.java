package com.cn.wf.model.mail;

import java.util.List;

/**
 * 邮件实体类模型
 * @author WangFei
 */
public class Mail {

	/** 邮件标题 */
	private String mailTitle = "";

	/** 邮件正文 */
	private String mailContent = "";

	/** 收件人列表 */
	private List<String> toAddress = null;

	/** 附件绝对路径 */
	private String absoluteFilePath = "";

	/** 附件名称 */
	private String fileName = "";

	/**
	 * 创建邮件对象
	 * @param mailTitle 邮件标题
	 * @param mailContent 邮件正文内容
	 * @param toAddress 邮件收件人地址
	 */
	public Mail(String mailTitle, String mailContent, List<String> toAddress) {
		this.mailTitle = mailTitle;
		this.mailContent = mailContent;
		this.toAddress = toAddress;
	}

	/**
	 * 创建邮件对象
	 * @param mailTitle 邮件标题
	 * @param mailContent 邮件正文内容
	 * @param toAddress 收件人地址
	 * @param attachment 邮件附件
	 */
	public Mail(String mailTitle, String mailContent, List<String> toAddress, String absoluteFilePath, String fileName) {
		this(mailTitle, mailContent, toAddress);

		this.absoluteFilePath = absoluteFilePath;
		this.fileName = fileName;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public List<String> getToAddress() {
		return toAddress;
	}

	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}

	public String getAbsoluteFilePath() {
		return absoluteFilePath;
	}

	public void setAbsoluteFilePath(String absoluteFilePath) {
		this.absoluteFilePath = absoluteFilePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
