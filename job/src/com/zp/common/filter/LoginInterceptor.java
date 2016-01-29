package com.zp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("jobUser") != null && "admin".equals(session.getAttribute("jobUser"))){
			return true;
		}
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return false;
	}
	
	

}
