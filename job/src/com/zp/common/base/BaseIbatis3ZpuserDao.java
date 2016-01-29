package com.zp.common.base;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;

public abstract class BaseIbatis3ZpuserDao <E,PK extends Serializable> extends  BaseIbatis3Dao<E, Serializable>{

	public void setSqlSessionFactory3(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
