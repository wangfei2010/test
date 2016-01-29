package com.zp.zpquartz.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.org.rapid_framework.util.holder.ApplicationContextHolder;

import com.zp.zpquartz.service.QrtzTriggersManager;

/**
 * 产品定时下架
 * @author WangFei
 */
public class UpdateSoldOffJob implements Job {
	private static Log log = LogFactory.getLog(UpdateSoldOffJob.class);
	

	/**
	 * 执行定时任务，更新产品下架状态
	 * <p>Author : WangFei</p>
	 * <p>Date : 2015年10月8日18:01:25</p>
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		// 0 0 3 * * ? //定时任务执行时间
		log.info("UpdateSoldOffJob----------定时任务更新产品下架状态------------");
		QrtzTriggersManager qrtzTriggersManager = (QrtzTriggersManager) ApplicationContextHolder.getBean("qrtzTriggersManager");

		// 运行出现错误写错误日志
		/*QrtzLogErrorManager qrtzLogErrorManager = (QrtzLogErrorM anager) ApplicationContextHolder
				.getBean("qrtzLogErrorManager");
		QrtzLogError qe = new QrtzLogError();
		qe.setJobname("CustomDownJob");
		qe.setError(e.getMessage());
		qrtzLogErrorManager.insertError(qe);*/
		
		System.out.println("执行");
		
		qrtzTriggersManager.updateFireTimeByJobName(this.getClass().getSimpleName());
	}

}
