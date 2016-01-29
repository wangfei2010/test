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


public class QrtzJobDetails extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "QrtzJobDetails";
	public static final String ALIAS_JOB_ID = "jobId";
	public static final String ALIAS_JOB_CLASS = "jobClass";
	public static final String ALIAS_JOB_NAME = "jobName";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * jobId       db_column: job_id 
     */	
	
	private java.lang.Integer jobId;
    /**
     * jobClass       db_column: job_class 
     */	
	@Length(max=255)
	private java.lang.String jobClass;
    /**
     * jobName       db_column: job_name 
     */	
	@Length(max=255)
	private java.lang.String jobName;
	//columns END

	public QrtzJobDetails(){
	}

	public QrtzJobDetails(
		java.lang.Integer jobId
	){
		this.jobId = jobId;
	}

	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	public void setJobClass(java.lang.String value) {
		this.jobClass = value;
	}
	
	public java.lang.String getJobClass() {
		return this.jobClass;
	}
	public void setJobName(java.lang.String value) {
		this.jobName = value;
	}
	
	public java.lang.String getJobName() {
		return this.jobName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("JobId",getJobId())
			.append("JobClass",getJobClass())
			.append("JobName",getJobName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getJobId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QrtzJobDetails == false) return false;
		if(this == obj) return true;
		QrtzJobDetails other = (QrtzJobDetails)obj;
		return new EqualsBuilder()
			.append(getJobId(),other.getJobId())
			.isEquals();
	}
}

