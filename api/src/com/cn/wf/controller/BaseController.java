package com.cn.wf.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	/**
	 * 
	 * @Title: getRealRequest
	 * @Description: 获取请求的真实客户端ip地址，nginx跳转后使用
	 * @param request
	 * @return
	 * @author WangFei
	 * @date 2014年10月31日 上午11:48:26
	 */
	public String getRealRemoteAddr(HttpServletRequest request) {
		return request.getHeader("X-Real-IP") != null ? request.getHeader("X-Real-IP") : request.getRemoteAddr();
	}
}
