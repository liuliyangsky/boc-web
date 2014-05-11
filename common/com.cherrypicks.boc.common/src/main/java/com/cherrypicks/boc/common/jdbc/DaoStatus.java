package com.cherrypicks.boc.common.jdbc;

public class DaoStatus {

	private Object mysqlResult;
	private Object redisResult;

	public Object getMysqlResult() {
		return mysqlResult;
	}

	public void setMysqlResult(Object mysqlResult) {
		this.mysqlResult = mysqlResult;
	}

	public Object getRedisResult() {
		return redisResult;
	}

	public void setRedisResult(Object redisResult) {
		this.redisResult = redisResult;
	}

}
