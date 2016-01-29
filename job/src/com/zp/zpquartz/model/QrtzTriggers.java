/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.model;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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


public class QrtzTriggers extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "QrtzTriggers";
	public static final String ALIAS_TRIGGER_ID = "triggerId";
	public static final String ALIAS_TRIGGER_NAME = "triggerName";
	public static final String ALIAS_TRIGGER_GROUP = "triggerGroup";
	public static final String ALIAS_JOB_ID = "jobId";
	public static final String ALIAS_NEXT_FIRE_TIME = "nextFireTime";
	public static final String ALIAS_TRIGGER_STATE = "0:停止，1:启用";
	public static final String ALIAS_TRIGGER_CRON = "triggerCron";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * triggerId       db_column: trigger_id 
     */	
	
	private java.lang.Integer triggerId;
    /**
     * triggerName       db_column: trigger_name 
     */	
	@Length(max=255)
	private java.lang.String triggerName;
    /**
     * triggerGroup       db_column: trigger_group 
     */	
	@Length(max=255)
	private java.lang.String triggerGroup;
    /**
     * jobId       db_column: job_id 
     */	
	
	private java.lang.Integer jobId;
    /**
     * nextFireTime       db_column: next_fire_time 
     */	
	
	private java.lang.Integer nextFireTime;
    /**
     * 0:停止，1:启用       db_column: trigger_state 
     */	
	@Length(max=1)
	private java.lang.String triggerState;
    /**
     * triggerCron       db_column: trigger_cron 
     */	
	@Length(max=255)
	private java.lang.String triggerCron;
	//columns END

	public QrtzTriggers(){
	}

	public QrtzTriggers(
		java.lang.Integer triggerId
	){
		this.triggerId = triggerId;
	}

	public void setTriggerId(java.lang.Integer value) {
		this.triggerId = value;
	}
	
	public java.lang.Integer getTriggerId() {
		return this.triggerId;
	}
	public void setTriggerName(java.lang.String value) {
		this.triggerName = value;
	}
	
	public java.lang.String getTriggerName() {
		return this.triggerName;
	}
	public void setTriggerGroup(java.lang.String value) {
		this.triggerGroup = value;
	}
	
	public java.lang.String getTriggerGroup() {
		return this.triggerGroup;
	}
	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	public void setNextFireTime(java.lang.Integer value) {
		this.nextFireTime = value;
	}
	
	public java.lang.Integer getNextFireTime() {
		return this.nextFireTime;
	}
	public void setTriggerState(java.lang.String value) {
		this.triggerState = value;
	}
	
	public java.lang.String getTriggerState() {
		return this.triggerState;
	}
	public void setTriggerCron(java.lang.String value) {
		this.triggerCron = value;
	}
	
	public java.lang.String getTriggerCron() {
		return this.triggerCron;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("TriggerId",getTriggerId())
			.append("TriggerName",getTriggerName())
			.append("TriggerGroup",getTriggerGroup())
			.append("JobId",getJobId())
			.append("NextFireTime",getNextFireTime())
			.append("TriggerState",getTriggerState())
			.append("TriggerCron",getTriggerCron())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTriggerId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QrtzTriggers == false) return false;
		if(this == obj) return true;
		QrtzTriggers other = (QrtzTriggers)obj;
		return new EqualsBuilder()
			.append(getTriggerId(),other.getTriggerId())
			.isEquals();
	}
}

