package com.zp.common.quartz;

import java.util.List;

import org.quartz.Job;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zp.common.util.QuartzManager;
import com.zp.zpquartz.model.QrtzJobDetails;
import com.zp.zpquartz.model.QrtzTriggers;
import com.zp.zpquartz.service.QrtzJobDetailsManager;
import com.zp.zpquartz.service.QrtzTriggersManager;
@Component
public class QuartzInit implements  InitializingBean{
	@Autowired
	private QrtzTriggersManager qrtzTriggersManager;
	@Autowired
	private QrtzJobDetailsManager qrtzJobDetailsManager;
	@Override
	public void afterPropertiesSet() throws Exception {
		List<QrtzTriggers> qrtzTriggers = qrtzTriggersManager.findAllData();
		
		for(QrtzTriggers qrt : qrtzTriggers){
			//1是可用
			if("1".equals(qrt.getTriggerState())){
				QrtzJobDetails qdetails = qrtzJobDetailsManager.getById(qrt.getJobId());
				String classname = qdetails.getJobClass();
				String jobname = qdetails.getJobName();
				Class job = Class.forName(classname);
				
				QuartzManager m = new QuartzManager();
				m.addJob(jobname,job,qrt.getTriggerCron());  
				//[秒] [分] [小时] [日] [月] [周] [年] 
			}
		}
		
	}
	
}