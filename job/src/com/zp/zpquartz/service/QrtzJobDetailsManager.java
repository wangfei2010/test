/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.org.rapid_framework.page.Page;

import com.zp.common.base.BaseManager;
import com.zp.common.base.EntityDao;
import com.zp.zpquartz.dao.QrtzJobDetailsDao;
import com.zp.zpquartz.model.QrtzJobDetails;
import com.zp.zpquartz.vo.query.QrtzJobDetailsQuery;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class QrtzJobDetailsManager extends BaseManager<QrtzJobDetails,java.lang.Integer>{

	private QrtzJobDetailsDao qrtzJobDetailsDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setQrtzJobDetailsDao(QrtzJobDetailsDao dao) {
		this.qrtzJobDetailsDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.qrtzJobDetailsDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(QrtzJobDetailsQuery query) {
		return qrtzJobDetailsDao.findPage(query);
	}
	
}
