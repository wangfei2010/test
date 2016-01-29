package com.zp.zpquartz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(value = "/main.do")
	public String mainJsp(HttpServletRequest request,HttpServletResponse response){
		return "main";
	}
	
	@RequestMapping(value = "/login.do")
	public @ResponseBody Map<String, Object> login(HttpServletRequest request, HttpSession session){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if("admin".equals(account) && "123.com".equals(password)){
			session.setAttribute("jobUser", account);
			resultMap.put("success", true);
			resultMap.put("message", "");
		}else{
			resultMap.put("success", false);
			resultMap.put("message", "用户名或密码错误！");
		}
		return resultMap;
	}

}
