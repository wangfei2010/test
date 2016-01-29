package com.zp.common.base;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSessionFactory;

public abstract class BaseIbatis3ZPlogDao<E,PK extends Serializable>  extends BaseIbatis3Dao{

	public void setSqlSessionFactory5(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
}
