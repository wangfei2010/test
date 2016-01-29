package com.cn.wf.service;


/**
 * 消息处理接口
 * @author WangFei
 */
public interface IMessageService {

	/**
	 * 发送简单邮件
	 * @param messageContent 发送的邮件内容（简单的文本内容）
	 * @param title 发送邮件标题
	 * @param mailAddress 发送的邮件地址
	 * @return 返回是否发送成功
	 */
	public boolean sendSimpleMailMessage(String messageContent, String title, String... emailAddress);
	
	/**
	 * 发送带附件的邮件
	 * @param messageContent 邮件正文内容
	 * @param title 邮件标题
	 * @param attachFilePath 附件路径
	 * @param emailAddress 收件人地址
	 * @return 
	 */
	public boolean sendAttachMailMessage(String messageContent, String title , String attachFilePath, String fileName, String... emailAddress);
}
