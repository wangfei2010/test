package javacommon.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cn.wf.model.mail.Mail;
import com.zp.commons.error.ApiException;
import com.zp.commons.error.MailCode;

/**
 * 邮件工具类
 * @author WangFei
 */
public class EmailUtil {
	public static final String FROM = PropertiesUtil.getProperty("mail.user.name", "emailconfig.properties");// 发件人的email
	public static final String PWD = PropertiesUtil.getProperty("mail.user.pass", "emailconfig.properties");// 发件人密码

	/**
	 * 创建Session
	 * @return
	 */
	private static Session getSession() {

		// 获取配置信息
		Properties prop = PropertiesUtil.getPropertys("emailconfig.properties");

		// 创建Session
		Authenticator authenticator = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, PWD);
			}

		};
		return Session.getDefaultInstance(prop, authenticator);
	}

	/**
	 * 发送文本
	 * @param title
	 * @param content
	 * @param toEmailAddress
	 * @throws MessagingException
	 */
	public static void sendTextMail(Mail mail) throws MessagingException {
		if (mail.getToAddress() == null || mail.getToAddress().size() == 0) throw new ApiException(MailCode.mail_001);

		Session session = getSession();

		Transport ts = session.getTransport();

		// 连接邮箱
		ts.connect();

		// 创建邮件
		Message message = createMessage(session, mail.getMailTitle(), mail.getToAddress());
		// 邮件的文本内容
		message.setContent(mail.getMailContent(), "text/html;charset=UTF-8");
		message.saveChanges();

		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());

		ts.close();
	}

	/**
	 * 发送带附件的邮件
	 * @param mail
	 * @throws MessagingException
	 */
	public static void sendAttachMail(Mail mail) throws MessagingException {

		if (mail.getToAddress() == null || mail.getToAddress().size() == 0) throw new ApiException(MailCode.mail_001);

		Session session = getSession();

		Transport ts = session.getTransport();

		// 连接邮箱
		ts.connect();

		// 创建邮件
		Message message = createMessage(session, mail.getMailTitle(), mail.getToAddress());

		// 创建邮件正文
		MimeBodyPart text = new MimeBodyPart();
		text.setContent(mail.getMailContent(), "text/html;charset=UTF-8");

		// 创建邮件附件
		MimeBodyPart attach = new MimeBodyPart();
		attach.setDataHandler(new DataHandler(new FileDataSource(mail.getAbsoluteFilePath())));
		attach.setFileName(mail.getFileName()); //

		// 创建爱你容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");

		message.setContent(mp);
		message.saveChanges();

		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());

		ts.close();
	}

	/**
	 * 创建消息
	 * @param session
	 * @param title
	 * @param toEmailAddress
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private static Message createMessage(Session session, String title, List<String> toEmailAddress) throws AddressException, MessagingException {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(FROM));
		// 指明邮件的收件人
		InternetAddress[] address = new InternetAddress[toEmailAddress.size()];

		for (int index = 0; index < toEmailAddress.size(); index++) {
			address[index] = new InternetAddress(toEmailAddress.get(index));
		}

		message.setRecipients(Message.RecipientType.TO, address);

		// 邮件的标题
		message.setSubject(title);

		return message;
	}
}
