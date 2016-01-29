/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.rapid_framework.page.Page;

import com.zp.common.base.BaseManager;
import com.zp.common.base.EntityDao;
import com.zp.zpquartz.dao.QrtzJobDetailsDao;
import com.zp.zpquartz.dao.QrtzTriggersDao;
import com.zp.zpquartz.model.QrtzJobDetails;
import com.zp.zpquartz.model.QrtzTriggers;
import com.zp.zpquartz.vo.query.QrtzTriggersQuery;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
public class QrtzTriggersManager extends BaseManager<QrtzTriggers,java.lang.Integer>{
	@Resource
	private QrtzTriggersDao qrtzTriggersDao;
	@Resource
	private QrtzJobDetailsDao qrtzJobDetailsDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setQrtzTriggersDao(QrtzTriggersDao dao) {
		this.qrtzTriggersDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.qrtzTriggersDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(QrtzTriggersQuery query) {
		return qrtzTriggersDao.findPage(query);
	}
	
	@Transactional(readOnly=true)
	public List<QrtzTriggers> findAllData() {
		return qrtzTriggersDao.findAllData();
	}
	
	public Integer getFireTimeByJobName(String jobName) {
		return qrtzTriggersDao.getFireTimeByJobName(jobName);
	}
	public Integer updateFireTimeByJobName(String jobName) {
		return qrtzTriggersDao.updateFireTimeByJobName(jobName);
	}
	
	/**
	 * 该方法用于跟新当前时间戳作为开始时间使用
	 * @param pMap 需要包含下次执行时间戳以及job名称{"jobName":"jobName","timestamp":timestamp}，时间戳单位为秒
	 * @return 
	 */
	public Integer updateFireTimeByJobName(Map<String, Object> pMap) {
		return qrtzTriggersDao.updateFireTimeByJobName(pMap);
	}
	
	public List<QrtzTriggers> queryList() {
		return qrtzTriggersDao.queryList();
	}
	/**
	 * 获取已经存在的jobName
	 * @return aJob,bJob……
	 */
	public String getJobNames() {
		return qrtzTriggersDao.getJobNames();
	}
	/**
	 * 保存Job,需要同时保存qrtzJobDetails和qrtzTriggers
	 * @param qrtzJobDetails
	 * @param qrtzTriggers
	 */
	@SuppressWarnings("unchecked")
	public void saveJob(QrtzJobDetails qrtzJobDetails, QrtzTriggers qrtzTriggers) {
		qrtzJobDetailsDao.save(qrtzJobDetails);
		qrtzTriggers.setJobId(qrtzJobDetails.getJobId());
		qrtzTriggersDao.save(qrtzTriggers);
	}
	/**
	 * 删除Job，级联删除qrtzJobDetails和qrtzTriggers
	 * @param jobId
	 * @param triggerId
	 */
	@SuppressWarnings("unchecked")
	public void deleteJob(Integer jobId, Integer triggerId) {
		qrtzJobDetailsDao.deleteById(jobId);
		qrtzTriggersDao.deleteById(triggerId);
	}
	
}
