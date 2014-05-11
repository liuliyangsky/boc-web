package com.cherrypicks.boc.common.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author carlye
 */
public class CacheExecute {

	private static Log logger = LogFactory.getLog(CacheExecute.class);

	public static DaoStatus execute(DaoInvoker mysqlInvoker, DaoInvoker redisInvoker) {

		Object mysqlStatus = null;
		Object redisStatus = null;

		try {
			mysqlStatus = mysqlInvoker.execute();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

		try {
			redisStatus = redisInvoker.execute();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}

		DaoStatus daoStatus = new DaoStatus();
		daoStatus.setMysqlResult(mysqlStatus);
		daoStatus.setRedisResult(redisStatus);

		return daoStatus;
	}
}
