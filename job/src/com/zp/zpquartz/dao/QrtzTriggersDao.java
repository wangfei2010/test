/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;

import com.zp.common.base.BaseIbatis3QtrzDao;
import com.zp.zpquartz.model.QrtzTriggers;
import com.zp.zpquartz.vo.query.QrtzTriggersQuery;


@Repository
public class QrtzTriggersDao extends BaseIbatis3QtrzDao<QrtzTriggers,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "QrtzTriggers";
	}
	
	public void saveOrUpdate(QrtzTriggers entity) {
		if(entity.getTriggerId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(QrtzTriggersQuery query) {
		return pageQuery("QrtzTriggers.findPage",query);
	}

	public List<QrtzTriggers> findAllData() {
		return getSqlSessionTemplate().selectList("QrtzTriggers.findAllData", null);
	}
	
	public Integer getFireTimeByJobName(String jobName) {
		return (Integer) getSqlSessionTemplate().selectOne("QrtzTriggers.getFireTimeByJobName", jobName);
	}
	public Integer updateFireTimeByJobName(String jobName) {
		return getSqlSessionTemplate().update("QrtzTriggers.updateFireTimeByJobName", jobName);
	}
	/**
	 * 该方法用于跟新当前时间戳作为开始时间使用
	 * @param pMap 需要包含下次执行时间戳以及job名称{"jobName":"jobName","time":timestamp}，时间戳单位为秒
	 * @return 
	 */
	public Integer updateFireTimeByJobName(Map<String, Object> pMap) {
		return getSqlSessionTemplate().update("QrtzTriggers.updateFireTimeByJobNameAndTimestamp", pMap);
	}
	
	public List<QrtzTriggers> queryList() {
		return getSqlSessionTemplate().selectList("QrtzTriggers.queryList",null);
	}

	@Override
	public void saveOrUpdate(Object entity) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	public String getJobNames() {
		return (String) getSqlSessionTemplate().selectOne("QrtzTriggers.getJobNames", null);
	}

}
