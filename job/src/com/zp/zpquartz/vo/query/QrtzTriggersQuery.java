/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package com.zp.zpquartz.vo.query;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

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


public class QrtzTriggersQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** triggerId */
	private java.lang.Integer triggerId;
	/** triggerName */
	private java.lang.String triggerName;
	/** triggerGroup */
	private java.lang.String triggerGroup;
	/** jobId */
	private java.lang.Integer jobId;
	/** nextFireTime */
	private java.lang.Integer nextFireTime;
	/** 0:停止，1:启用 */
	private java.lang.String triggerState;
	/** triggerCron */
	private java.lang.String triggerCron;

	public java.lang.Integer getTriggerId() {
		return this.triggerId;
	}
	
	public void setTriggerId(java.lang.Integer value) {
		this.triggerId = value;
	}
	
	public java.lang.String getTriggerName() {
		return this.triggerName;
	}
	
	public void setTriggerName(java.lang.String value) {
		this.triggerName = value;
	}
	
	public java.lang.String getTriggerGroup() {
		return this.triggerGroup;
	}
	
	public void setTriggerGroup(java.lang.String value) {
		this.triggerGroup = value;
	}
	
	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	
	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	public java.lang.Integer getNextFireTime() {
		return this.nextFireTime;
	}
	
	public void setNextFireTime(java.lang.Integer value) {
		this.nextFireTime = value;
	}
	
	public java.lang.String getTriggerState() {
		return this.triggerState;
	}
	
	public void setTriggerState(java.lang.String value) {
		this.triggerState = value;
	}
	
	public java.lang.String getTriggerCron() {
		return this.triggerCron;
	}
	
	public void setTriggerCron(java.lang.String value) {
		this.triggerCron = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

