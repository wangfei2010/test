/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;

import com.zp.common.base.BaseIbatis3QtrzDao;
import com.zp.zpquartz.model.QrtzJobDetails;
import com.zp.zpquartz.vo.query.QrtzJobDetailsQuery;


@Repository
public class QrtzJobDetailsDao extends BaseIbatis3QtrzDao<QrtzJobDetails,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "QrtzJobDetails";
	}
	
	public void saveOrUpdate(QrtzJobDetails entity) {
		if(entity.getJobId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(QrtzJobDetailsQuery query) {
		return pageQuery("QrtzJobDetails.findPage",query);
	}

	@Override
	public void saveOrUpdate(Object entity) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
	

}
