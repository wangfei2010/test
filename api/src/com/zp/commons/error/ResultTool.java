package com.zp.commons.error;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class ResultTool {
	private static Logger logger = Logger.getLogger(ResultTool.class);

	/**
	 * 
	 * @Title: create
	 * @Description: 包装正确结果集
	 * @param error
	 * @return
	 * @author yuzf
	 * @date 2014年9月29日 下午4:01:55
	 */
	public static ModelAndView createSuccess(Object result) {
		return createSuccess(result,null);
	}
	
	/**
	 * 
	 * @Title: create
	 * @Description: 包装正确结果集
	 * @param error
	 * @return
	 * @author zhupengren
	 * @date 2014年9月29日 下午4:01:55
	 */
	public static ModelAndView createSuccess(Object result, ModelAndView view) {
		view=view==null?new ModelAndView():view;
		create(ErrorCode.SUCCESS,view);
		view.addObject("result", result);
		return view;
	}

	/**
	 * 
	 * @Title: create
	 * @Description: 使用提供的ModelAndView，可以少创建一次对象，推荐使用
	 * @param error
	 * @param view
	 * @return
	 * @author zhupengren
	 * @date 2014年9月29日 下午4:02:47
	 */
	public static ModelAndView create(ErrorCode error, ModelAndView view) {
		logger.error(error.getCode()+":"+error.getCodeMsg());
		view.addObject("code", error.getCode());
		view.addObject("codeMsg", error.getCodeMsg());
		return view;
	}
	
	/**
	 * 
	 * @Title: create 
	 * @Description: 使用已有的ModelAndView，打印异常堆栈，返回500系统错误编码
	 * @param e
	 * @param view
	 * @return 
	 * @author yuzf
	 * @date 2014年9月29日 下午4:40:34
	 */
	public static ModelAndView create(Exception e, ModelAndView view) {
		view=view==null?new ModelAndView():view;
		logger.error(e.getMessage(), e);
		if(e instanceof ApiException){
			return create(((ApiException)e).getCode(),view);
		}else{
			return create(ErrorCode.ERROR,view);
		}
	}

}