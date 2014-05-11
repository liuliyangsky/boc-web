package com.cherrypicks.boc.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.cherrypicks.boc.common.jdbc.Jdbc;
import com.cherrypicks.boc.common.jdbc.ListUtil;
import com.cherrypicks.boc.common.jdbc.StatementParameter;

@Component
public abstract class JdbcComponent extends Jdbc {

	//private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(getDataSource());
	}

	/*public void setNamedParameterJdbcTemplate() {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(super.getDataSource(););
	}*/

	private void log(List<?> list, String sql, StatementParameter param) {
		int size = ListUtil.size(list);
		String sql1;
		if (param == null) {
			sql1 = sql;
		} else {
			sql1 = this.getSQL(sql, param);
		}
		this.logger.info("result size:" + size + " sql:" + sql1);
	}

	/**
	 * query for named NamedParams
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	protected long queryForLongWithNamedParam(String sql, Map<String, ?> paramMap) {
		try {
			long result = this.getNamedParameterJdbcTemplate().queryForLong(sql, paramMap);
			if (log) {
				logger.info("result:" + result + " sql:" + sql);
			}
			return result;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}
	
	/**
	 * query list for named NamedParams
	 * @param sql
	 * @param elementType
	 * @param paramMap
	 * @return
	 */
	protected <T> List<T> queryForListWithNamedParam(String sql, Class<T> elementType, Map<String, ?> paramMap) {
		try {
			List<T> list = this.getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<T>(elementType));
			if (log) {
				this.log(list, sql, null);
			}
			return list;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * query list for named NamedParams
	 * @param sql
	 * @param elementType
	 * @param paramMap
	 * @return
	 */
	protected List<String> queryForStringtWithNamedParam(String sql, Map<String, ?> paramMap) {
		try {
			List<String> list = this.getNamedParameterJdbcTemplate().queryForList(sql, paramMap, String.class);
			if (log) {
				this.log(list, sql, null);
			}
			return list;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * query list for named NamedParams
	 * @param sql
	 * @param elementType
	 * @param paramMap
	 * @return
	 */
	protected int queryForIntWithNamedParam(String sql, Map<String, ?> paramMap) {
		try {
			int result = this.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
			if (log) {
				logger.info("result:" + result + " sql:" + sql);
			}
			return result;
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}
	
	/**
	 * update for named NamedParams
	 * @param sql
	 * @param param
	 * @return
	 */
	protected int updateForRecordWithNamedParam(String sql, Map<String, ?> paramMap) {
		int updatedCount = this.getNamedParameterJdbcTemplate().update(sql, paramMap);
		if (log) {
			logger.info("updatedCount:" + updatedCount + " sql:" + sql);
		}
		return updatedCount;
	}
	
}
