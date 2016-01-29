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


public class QrtzLogError extends BaseEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "QrtzLogError";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JOBNAME = "jobname";
	public static final String ALIAS_ERROR = "error";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: id 
     */	
	
	private java.lang.Integer id;
    /**
     * jobname       db_column: jobname 
     */	
	@Length(max=500)
	private java.lang.String jobname;
    /**
     * error       db_column: error 
     */	
	@Length(max=500)
	private java.lang.String error;
	//columns END

	public QrtzLogError(){
	}

	public QrtzLogError(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setJobname(java.lang.String value) {
		this.jobname = value;
	}
	
	public java.lang.String getJobname() {
		return this.jobname;
	}
	public void setError(java.lang.String value) {
		this.error = value;
	}
	
	public java.lang.String getError() {
		return this.error;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Jobname",getJobname())
			.append("Error",getError())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof QrtzLogError == false) return false;
		if(this == obj) return true;
		QrtzLogError other = (QrtzLogError)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

