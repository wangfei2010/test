/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.org.rapid_framework.util.*;
import cn.org.rapid_framework.web.util.*;
import cn.org.rapid_framework.page.*;
import cn.org.rapid_framework.page.impl.*;

import com.zp.zpquartz.model.*;
import com.zp.common.base.*;
import com.zp.common.util.*;
import com.zp.zpquartz.dao.*;
import com.zp.zpquartz.service.*;
import com.zp.zpquartz.vo.query.*;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
@Transactional
public class QrtzLogErrorManager extends BaseManager<QrtzLogError,java.lang.Integer>{

	private QrtzLogErrorDao qrtzLogErrorDao;
	/**增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,请注意大小写*/
	public void setQrtzLogErrorDao(QrtzLogErrorDao dao) {
		this.qrtzLogErrorDao = dao;
	}
	public EntityDao getEntityDao() {
		return this.qrtzLogErrorDao;
	}
	
	@Transactional(readOnly=true)
	public Page findPage(QrtzLogErrorQuery query) {
		return qrtzLogErrorDao.findPage(query);
	}
	public void insertError(QrtzLogError qe) {
		qrtzLogErrorDao.insertError(qe);
		
	}
	
}
