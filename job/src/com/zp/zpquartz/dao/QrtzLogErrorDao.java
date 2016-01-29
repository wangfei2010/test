/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.dao;

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


import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Repository
public class QrtzLogErrorDao extends BaseIbatis3QtrzDao<QrtzLogError,java.lang.Integer>{
	
	@Override
	public String getIbatisMapperNamesapce() {
		return "QrtzLogError";
	}
	
	public void saveOrUpdate(QrtzLogError entity) {
		if(entity.getId() == null) 
			save(entity);
		else 
			update(entity);
	}
	
	public Page findPage(QrtzLogErrorQuery query) {
		return pageQuery("QrtzLogError.findPage",query);
	}

	public Object insertError(QrtzLogError qe) {
		return getSqlSessionTemplate().insert("QrtzLogError.insertError", qe);
	}

	@Override
	public void saveOrUpdate(Object entity) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}
	

}
