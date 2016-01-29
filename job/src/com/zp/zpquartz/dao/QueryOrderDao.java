package com.zp.zpquartz.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.zp.common.base.ZpReadDao;

@Repository
public class QueryOrderDao extends ZpReadDao{

	public void saveOrUpdate(Object entity) throws DataAccessException {
		
	}

	public Object queryOrders(Map<String,String> time){
		
		return getSqlSessionTemplate().selectList("QueryOrder.queryOrder", time);
	}
}
