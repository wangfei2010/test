package com.zp.zpquartz.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.org.rapid_framework.util.holder.ApplicationContextHolder;

import com.zp.zpquartz.service.QrtzTriggersManager;
import com.zp.zpquartz.service.QueryOrderManager;

public class QueryOrdersJob implements Job {
	private static Log log = LogFactory.getLog(QueryOrdersJob.class);

	@Override
	public void execute(JobExecutionContext job) throws JobExecutionException {
		// 0 0 3 * * ? //定时任务执行时间
		log.info("QueryOrdersJob----------查询订单------------");
		QrtzTriggersManager qrtzTriggersManager = (QrtzTriggersManager) ApplicationContextHolder.getBean("qrtzTriggersManager");
		
		QueryOrderManager queryOrderManager = (QueryOrderManager) ApplicationContextHolder.getBean("queryOrderManager");
		
		Map<String , String> param = new HashMap<String , String>();
		param.put("startTime", "2016-01-13");
		param.put("endTime", "2016-01-14");
		
		Object  result = queryOrderManager.queryOrders(param);
		
		System.out.println(result);
		
		qrtzTriggersManager.updateFireTimeByJobName(this.getClass().getSimpleName());
	}

}
