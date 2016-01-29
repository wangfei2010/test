/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.web.scope.Flash;
import cn.org.rapid_framework.web.util.HttpUtils;

import com.zp.common.base.BaseSpringController;
import com.zp.common.util.QuartzManager;
import com.zp.zpquartz.model.QrtzJobDetails;
import com.zp.zpquartz.model.QrtzTriggers;
import com.zp.zpquartz.service.QrtzTriggersManager;
import com.zp.zpquartz.vo.query.QrtzTriggersQuery;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


@Controller
@RequestMapping("/quartzTriggers")
public class QrtzTriggersController extends BaseSpringController{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	private QrtzTriggersManager qrtzTriggersManager;
	
	private final String LIST_ACTION = "redirect:/pages/QrtzTriggers/list.do";
	
	public QrtzTriggersController() {
	}
	
	/** 
	 * 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写
	 **/
	public void setQrtzTriggersManager(QrtzTriggersManager manager) {
		this.qrtzTriggersManager = manager;
	}

	/**
	 * 增加了@ModelAttribute的方法可以在本controller的方法调用前执行,可以存放一些共享变量,如枚举值
	 */
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
	}
	
	/** 
	 * 执行搜索 
	 **/
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response,QrtzTriggersQuery query) {
		Page page = this.qrtzTriggersManager.findPage(query);
		
		ModelAndView result = new ModelAndView("/QrtzTriggers/list");
		result.addAllObjects(toModelMap(page, query));
		return result;
	}
	
	/** 
	 * 查看对象
	 **/
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("triggerId"));
		QrtzTriggers qrtzTriggers = (QrtzTriggers)qrtzTriggersManager.getById(id);
		return new ModelAndView("/QrtzTriggers/show","qrtzTriggers",qrtzTriggers);
	}
	
	/** 
	 * 进入新增页面
	 **/
	public ModelAndView create(HttpServletRequest request,HttpServletResponse response,QrtzTriggers qrtzTriggers) throws Exception {
		return new ModelAndView("/QrtzTriggers/create","qrtzTriggers",qrtzTriggers);
	}
	
	/** 
	 * 保存新增对象
	 **/
	public ModelAndView save(HttpServletRequest request,HttpServletResponse response,QrtzTriggers qrtzTriggers) throws Exception {
		qrtzTriggersManager.save(qrtzTriggers);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return new ModelAndView(LIST_ACTION);
	}
	
	/**
	 * 进入更新页面
	 **/
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("triggerId"));
		QrtzTriggers qrtzTriggers = (QrtzTriggers)qrtzTriggersManager.getById(id);
		return new ModelAndView("/QrtzTriggers/edit","qrtzTriggers",qrtzTriggers);
	}
	
	/**
	 * 保存更新对象
	 **/
	public ModelAndView update(HttpServletRequest request,HttpServletResponse response) throws Exception {
		java.lang.Integer id = new java.lang.Integer(request.getParameter("triggerId"));
		
		QrtzTriggers qrtzTriggers = (QrtzTriggers)qrtzTriggersManager.getById(id);
		bind(request,qrtzTriggers);
		qrtzTriggersManager.update(qrtzTriggers);
		Flash.current().success(UPDATE_SUCCESS);
		return new ModelAndView(LIST_ACTION);
	}
	
	/**
	 *删除对象
	 **/
	public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) {
		String[] items = request.getParameterValues("items");
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			
			java.lang.Integer id = new java.lang.Integer((String)params.get("triggerId"));
			
			qrtzTriggersManager.removeById(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return new ModelAndView(LIST_ACTION);
	}
	
	/**
	 * 主页跳转
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String indexJsp(HttpServletRequest request,HttpServletResponse response){
		return "/quartzTriggers/index";
	}

	/**
	 * 分页查询qrtz信息
	 * @param page 页码
	 * @param rows 每页显示条数
	 */
	@RequestMapping(value = "/queryJobsByPage.json")
	public @ResponseBody ModelAndView queryJobsByPage(Integer page, Integer rows) throws Exception{
		QrtzTriggersQuery query = new QrtzTriggersQuery();
		query.setPageNumber(page);
		query.setPageSize(rows);
		query.setSortColumns("trigger_id desc");
		Page jobPage = qrtzTriggersManager.findPage(query);
		ModelAndView result = new ModelAndView();
		result.addObject("total", jobPage.getTotalCount());
		result.addObject("rows", jobPage.getResult());
		return result;
	}
	/**
	 * 获取job类名称
	 */
	@RequestMapping(value = "/getJobName.json")
	public @ResponseBody List<Object> getJobName() throws Exception{
		String path = this.getClass().getClassLoader().getResource("").getPath() + "com/zp/zpquartz/job/";
		File file = new File(path);
		List<Object> list = new ArrayList<Object>();
		String jobList = qrtzTriggersManager.getJobNames();
		if(jobList == null || "".equals(jobList)) {
			return list;
		}
		if(file.exists()){  
            File files[] = file.listFiles();
            for (File aFile : files) {
            	String jobName = aFile.getName().substring(0, aFile.getName().lastIndexOf("."));
            	if(!jobList.contains(jobName)){
            		Map<String, String> fileMap = new HashMap<String, String>();
            		fileMap.put("jobName", jobName);
            		list.add(fileMap);
            	}
			}
        }
		return list;
	}
	
	/**
	 * 新增JOB
	 */
	@RequestMapping("/saveQuartzTriggers.json")
	public @ResponseBody Map<String, Object> saveQuartzTriggers(HttpServletRequest request) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String triggerName = request.getParameter("triggerName");
			String triggerState = request.getParameter("triggerState");
			String triggerCron = request.getParameter("triggerCron");
			if("".equals(triggerName) || "".equals(triggerState) || "".equals(triggerCron)){
				throw new Exception("参数错误，请参考API文档");
			}
			Integer jobId = null;
			String jobClass = "com.zp.zpquartz.job." + triggerName;
			String jobFullName = this.getClass().getClassLoader().getResource("").getPath() + "com/zp/zpquartz/job/" + triggerName + ".class";
			File file = new File(jobFullName);
			if(!file.exists()){
				throw new Exception("指定的类文件不存在");
			}
			
			QrtzJobDetails qrtzJobDetails = new QrtzJobDetails();
			qrtzJobDetails.setJobClass(jobClass);
			qrtzJobDetails.setJobName(triggerName);
			
			QrtzTriggers qrtzTriggers = new QrtzTriggers();
			qrtzTriggers.setTriggerGroup("");
			qrtzTriggers.setNextFireTime(((Long)(System.currentTimeMillis() / 1000)).intValue());
			qrtzTriggers.setTriggerCron(triggerCron);
			qrtzTriggers.setJobId(jobId);
			qrtzTriggers.setTriggerName(triggerName);
			qrtzTriggers.setTriggerState(triggerState);
			qrtzTriggersManager.saveJob(qrtzJobDetails,qrtzTriggers);
			result.put("success", true);
			result.put("message", "");
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除Job
	 */
	@RequestMapping(value = "/deleteQuartzTriggers.json")
	public @ResponseBody Map<String,Object> deleteQuartzTriggers(Integer triggerId) throws Exception{
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try {
			QrtzTriggers qrtzTriggers = qrtzTriggersManager.getById(triggerId);
			qrtzTriggersManager.deleteJob(qrtzTriggers.getJobId(),triggerId);
			resultMap.put("success", true);
			resultMap.put("message", "");
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * 更新JOB
	 */
	@RequestMapping(value = "/editQuartzTriggers.json")
	public @ResponseBody Map<String, Object> editQuartzTriggers(HttpServletRequest request) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String triggerId = request.getParameter("triggerId");
			String triggerState = request.getParameter("editTriggerState");
			String triggerCron = request.getParameter("triggerCron");
			QrtzTriggers qrtzTriggers = qrtzTriggersManager.getById(Integer.parseInt(triggerId));
			if(qrtzTriggers != null){
				qrtzTriggers.setTriggerState(triggerState);
				qrtzTriggers.setTriggerCron(triggerCron);
				qrtzTriggersManager.update(qrtzTriggers);
				resultMap.put("success", true);
				resultMap.put("message", "");
			}else{
				resultMap.put("success", false);
				resultMap.put("message", "该数据不存在！");
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/**
	 * 重新部署所有任务
	 */
	@RequestMapping(value = "/reflushJobs.json")
	public @ResponseBody Map<String, Object> reflushJobs() throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			String path = this.getClass().getClassLoader().getResource("").getPath() + "com/zp/zpquartz/job/";
			File file = new File(path);
			if(file.exists()){  
	            File files[] = file.listFiles();
	            for (File aFile : files) {
	            	String jobName = aFile.getName().substring(0, aFile.getName().lastIndexOf("."));
	            	QuartzManager.removeJob(jobName);
				}
	        }
			List<QrtzTriggers> jobList = qrtzTriggersManager.findAllData();
			for (QrtzTriggers qrtzTriggers : jobList) {
				if("1".equals(qrtzTriggers.getTriggerState())){
					String jobName = qrtzTriggers.getTriggerName();
					@SuppressWarnings("rawtypes")
					Class jobClass = Class.forName("com.zp.zpquartz.job." + jobName);
					QuartzManager.addJob(jobName, jobClass, qrtzTriggers.getTriggerCron());
				}
			}
			resultMap.put("success", true);
		}catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	}
	/**
	 * 重新部署选中任务
	 * @param triggerId 任务Id
	 */
	@RequestMapping(value = "reloadJob")
	public Map<String, Object> reloadJob(Integer triggerId) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			QrtzTriggers qrtzTriggers = qrtzTriggersManager.getById(triggerId);
			String jobName = qrtzTriggers.getTriggerName();
			QuartzManager.removeJob(jobName);
			if("1".equals(qrtzTriggers.getTriggerState())){
				@SuppressWarnings("rawtypes")
				Class jobClass = Class.forName("com.zp.zpquartz.job." + jobName);
				QuartzManager.addJob(jobName, jobClass, qrtzTriggers.getTriggerCron());
			}
			resultMap.put("success", true);
		}catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
			e.printStackTrace();
		}
		return resultMap;
	} 
	
}

