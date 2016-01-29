package com.cn.wf.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wf.com.util.StringUtils;

import com.cn.wf.service.IMessageService;
import com.zp.commons.error.ApiException;
import com.zp.commons.error.BusinessException;
import com.zp.commons.error.MailCode;
import com.zp.commons.error.ResultTool;

@Controller
@RequestMapping("/mail")
public class MailController extends BaseController {
	private Logger logger = Logger.getLogger(MailController.class);

	@Resource
	private IMessageService messageService;

	@RequestMapping("/sendMail.json")
	public @ResponseBody
	ModelAndView sendMail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView result = new ModelAndView();

		// 邮件发送内容
		String mailContent = StringUtils.parseStr(request.getParameter("mailContent"));
		// 邮件发送标题
		String title = StringUtils.parseStr(request.getParameter("title"));
		String mailAddress = StringUtils.parseStr(request.getParameter("mail"));

		String filePath = StringUtils.parseStr(request.getParameter("filepath"));

		// 检测参数是否正确
		if (StringUtils.isEmpty(mailContent, title, mailAddress)) {
			return ResultTool.create(MailCode.sys_008, result);
		}

		try {
			boolean sendFlag = false;

			// 不包含附件
			if (StringUtils.isEmpty(filePath)) {
				sendFlag = messageService.sendSimpleMailMessage(mailContent, title, mailAddress.split(","));
			}
			// 包含附件
			else {
				File file = new File(filePath);
				if (!file.exists()) {
					return ResultTool.create(MailCode.mail_002, result);
				}

				// 发送邮件
				sendFlag = messageService.sendAttachMailMessage(mailContent, title, filePath, file.getName(), mailAddress.split(","));
			}

			if (sendFlag) {
				result.addObject("result", "发送成功");
				return ResultTool.create(MailCode.SUCCESS, result);
			}
			else {
				return ResultTool.create(MailCode.ERROR, result);
			}
		}
		catch (BusinessException | ApiException e) {
			logger.error(e.getMessage(), e);
			return ResultTool.create(MailCode.ERROR, result);
		}

	}
}
