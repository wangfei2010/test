package com.cn.wf.service.impl;

import java.util.Arrays;
import java.util.List;

import javacommon.util.EmailUtil;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cn.wf.model.mail.Mail;
import com.cn.wf.service.IMessageService;
import com.zp.commons.error.ApiException;
import com.zp.commons.error.BusinessException;
import com.zp.commons.error.MailCode;

@Service
public class MessageService implements IMessageService {
	private static Logger logger = Logger.getLogger(MessageService.class);
	
	@Override
	public boolean sendSimpleMailMessage(String messageContent, String title, String... emailAddress) {
		if (emailAddress == null || emailAddress.length == 0) {
			throw new ApiException(MailCode.sys_008);
		}

		try {
			List<String> toAddress = Arrays.asList(emailAddress);
			
			// 发送邮件
			EmailUtil.sendTextMail(new Mail(title, messageContent, toAddress));
			
			return true;
		}
		catch (ApiException | MessagingException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(MailCode.ERROR, e);
		}

	}
	
	@Override
	public boolean sendAttachMailMessage(String messageContent, String title, String attachFilePath, String fileName, String... emailAddress) {
		if (emailAddress == null || emailAddress.length == 0) {
			throw new ApiException(MailCode.sys_008);
		}
		
		try {
			List<String> toAddress = Arrays.asList(emailAddress);
			
			// 发送邮件
			EmailUtil.sendAttachMail(new Mail(title, messageContent, toAddress,attachFilePath,fileName));
			
			return true;
		}
		catch (ApiException | MessagingException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(MailCode.ERROR, e);
		}

	}

}
