package com.zp.zpquartz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zp.zpquartz.dao.QueryOrderDao;

@Service
@Transactional
public class QueryOrderManager {
	@Resource
	private QueryOrderDao queryOrderDao;
	
	public Object queryOrders(Map<String , String> param){
		return queryOrderDao.queryOrders(param);
	}
}
