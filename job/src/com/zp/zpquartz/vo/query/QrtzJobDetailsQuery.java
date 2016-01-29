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


public class QrtzJobDetailsQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** jobId */
	private java.lang.Integer jobId;
	/** jobClass */
	private java.lang.String jobClass;
	/** jobName */
	private java.lang.String jobName;

	public java.lang.Integer getJobId() {
		return this.jobId;
	}
	
	public void setJobId(java.lang.Integer value) {
		this.jobId = value;
	}
	
	public java.lang.String getJobClass() {
		return this.jobClass;
	}
	
	public void setJobClass(java.lang.String value) {
		this.jobClass = value;
	}
	
	public java.lang.String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(java.lang.String value) {
		this.jobName = value;
	}
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
	
}

