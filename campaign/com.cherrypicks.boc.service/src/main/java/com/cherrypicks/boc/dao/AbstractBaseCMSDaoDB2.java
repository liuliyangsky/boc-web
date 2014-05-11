package com.cherrypicks.boc.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.cherrypicks.boc.common.jdbc.SimpleStatementParameter;
import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.common.model.AbstractEntity;
import com.cherrypicks.boc.dao.paging.ArrayPagingList;
import com.cherrypicks.boc.dao.paging.PagingList;
import com.cherrypicks.boc.jdbc.JdbcCMS;

public abstract class AbstractBaseCMSDaoDB2<T extends AbstractEntity> extends JdbcCMS {

	private Log logger = LogFactory.getLog(this.getClass());

	protected Class<T> baseModelClass;
	protected String tableName;
	private boolean existBaseTable;
	private String baseTableIdName;

	@PostConstruct
	@SuppressWarnings("unchecked")
	public void init() {
		logger.info("init");
		this.baseModelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.tableName = underscoreName(this.baseModelClass.getSimpleName());
		// system_config_lang => system_config_id
		// campaign_content => campaign_id
		if (isExistBaseTable()) {
			existBaseTable = true;
			this.baseTableIdName = new StringBuilder(this.tableName.substring(0, this.tableName.lastIndexOf("_") + 1)).append("id").toString();
		}
	}
	
	private boolean isExistBaseTable() {
		return this.tableName.lastIndexOf("_") != -1;
	}

	@PreDestroy
	public void destroy() {
		logger.info("destroy");
	}

	/**
	 * Convert a name in camelCase to an underscored name in Upper case.
	 * 
	 * @param name
	 *            the string containing original name
	 * @return the converted name
	 */
	protected String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toUpperCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toUpperCase());
				} else {
					result.append(s.toUpperCase());
				}
			}	
		}
		return result.toString();
	}
	
	/**
	 * 只適用于主键是数据库自动生長的表的情况下 
     * executeAndReturnKey用于执行并获取自动生成主键（注意是Number类型）
	 * 必须首先通过setGeneratedKeyName设置主键然后才能获取，如果想获取复
	 * 合主键请使用setGeneratedKeyNames描述主键然后通过executeReturningKeyHolder
	 * 获取复合主键KeyHolder对象；  
	 */
	@SuppressWarnings("rawtypes")
	public T add(T object) {
		SimpleStatementParameter param = new SimpleStatementParameter(this.tableName, "id");

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(baseModelClass);
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			String underscoredName = underscoreName(propertyName);
			Class propertyType = beanWrapper.getPropertyType(propertyName);
			Object propertyValue = beanWrapper.getPropertyValue(propertyName);

			if (propertyValue != null) {
				if (propertyType.equals(Date.class)) {
					param.setDate(underscoredName, (Date) propertyValue);
				} else if (propertyType.equals(String.class)) {
					param.setString(underscoredName, (String) propertyValue);
				} else if (propertyType.equals(Boolean.class)) {
					param.setBool(underscoredName, (Boolean) propertyValue);
				} else if (propertyType.equals(Integer.class)) {
					param.setInt(underscoredName, (Integer) propertyValue);
				} else if (propertyType.equals(Long.class)) {
					param.setLong(underscoredName, (Long) propertyValue);
				} else if (propertyType.equals(Double.class)) {
					param.setDouble(underscoredName, (Double) propertyValue);
				} else if (propertyType.equals(Float.class)) {
					param.setFloat(underscoredName, (Float) propertyValue);
				}
				
				/*針對主外鍵鍵是object的類型*/
				if (propertyType.equals(Object.class)) {
					if(propertyValue instanceof Long){
						param.setLong(underscoredName, (Long)propertyValue);
					}
					if(propertyValue instanceof String){
						param.setString(underscoredName, (String)propertyValue);
					}
					if(propertyValue instanceof Integer){
						param.setInt(underscoredName, (Integer)propertyValue);
					}
				}
				
			}
		}

		object.setId(super.executeAndReturnKey(param));

		return object;
	}
	
	/**
	 * 只適用于主键是由自己生成的表的情况下 
     * execute用于执行并获取自动生成主键（注意是Number类型）
	 */
	@SuppressWarnings("rawtypes")
	public T addPKObject(T object) {
		SimpleStatementParameter param = new SimpleStatementParameter(this.tableName, "id");

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(baseModelClass);
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			String underscoredName = underscoreName(propertyName);
			Class propertyType = beanWrapper.getPropertyType(propertyName);
			Object propertyValue = beanWrapper.getPropertyValue(propertyName);

			if (propertyValue != null) {
				if (propertyType.equals(Date.class)) {
					param.setDate(underscoredName, (Date) propertyValue);
				} else if (propertyType.equals(String.class)) {
					param.setString(underscoredName, (String) propertyValue);
				} else if (propertyType.equals(Boolean.class)) {
					param.setBool(underscoredName, (Boolean) propertyValue);
				} else if (propertyType.equals(Integer.class)) {
					param.setInt(underscoredName, (Integer) propertyValue);
				} else if (propertyType.equals(Long.class)) {
					param.setLong(underscoredName, (Long) propertyValue);
				} else if (propertyType.equals(Double.class)) {
					param.setDouble(underscoredName, (Double) propertyValue);
				} else if (propertyType.equals(Float.class)) {
					param.setFloat(underscoredName, (Float) propertyValue);
				}
				
				/*針對主外鍵鍵是object的類型*/
				if (propertyType.equals(Object.class)) {
					if(propertyValue instanceof Long){
						param.setLong(underscoredName, (Long)propertyValue);
					}
					if(propertyValue instanceof String){
						param.setString(underscoredName, (String)propertyValue);
					}
					if(propertyValue instanceof Integer){
						param.setInt(underscoredName, (Integer)propertyValue);
					}
				}
				
			}
		}

		super.execute(param);
		return object;
	}

	public boolean del(Object id) throws SQLException {
		String sql = "DELETE FROM " + this.tableName + " WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		//param.setString((String) id);
		param.setObject(id);
		return super.updateForBoolean(sql, param);
	}
	/**
	 * 邏輯刪除 標記 IS_DELETED=false
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean delData(Object id) throws SQLException {
		String sql = "UPDATE " + this.tableName + " SET IS_DELETED=true  WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		//param.setString((String) id);
		param.setObject(id);
		return super.updateForBoolean(sql, param);
	}

	@SuppressWarnings("rawtypes")
	public T update(T object) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE " + this.tableName + " SET");
		StatementParameter param = new StatementParameter();

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(baseModelClass);
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			String underscoredName = underscoreName(propertyName);
			Class propertyType = beanWrapper.getPropertyType(propertyName);
			Object propertyValue = beanWrapper.getPropertyValue(propertyName);

			if (propertyValue != null && !propertyName.equals("id") && !propertyName.equals("class")) {
				if (param.size() > 0) {
					sql.append(",");
				}
				sql.append(" " + underscoredName + "=?");

				if (propertyType.equals(Date.class)) {
					param.setDate((Date) propertyValue);
				} else if (propertyType.equals(String.class)) {
					param.setString((String) propertyValue);
				} else if (propertyType.equals(Boolean.class)) {
					param.setBool((Boolean) propertyValue);
				} else if (propertyType.equals(Integer.class)) {
					param.setInt((Integer) propertyValue);
				} else if (propertyType.equals(Long.class)) {
					param.setLong((Long) propertyValue);
				} else if (propertyType.equals(Double.class)) {
					param.setDouble((Double) propertyValue);
				} else if (propertyType.equals(Float.class)) {
					param.setFloat((Float) propertyValue);
				}
				
				/*針對主外鍵鍵是object的類型*/
				if (propertyType.equals(Object.class)) {
					if(propertyValue instanceof Long){
						param.setLong((Long)propertyValue);
					}
					if(propertyValue instanceof String){
						param.setString((String)propertyValue);
					}
					if(propertyValue instanceof Integer){
						param.setInt((Integer)propertyValue);
					}
				}
			}
		}

		sql.append(" WHERE ID=? ");
		//param.setString(object.getId().toString());
		param.setObject(object.getId());
		
		boolean updatedSuccess = false;
		if (param.size() > 0) {
			updatedSuccess = super.updateForBoolean(sql.toString(), param);
		} else {
			updatedSuccess = false;
		}
		return updatedSuccess ? object : null;
	}

	public T get(Object id) throws SQLException {
		String sql = "SELECT * FROM " + this.tableName + " WHERE ID = ? AND IS_DELETED=false";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		//param.setString((String) id);
		return super.query(sql, this.baseModelClass, param);
	}
	
	public T defaultGet(Object id) throws SQLException {
		String sql = "SELECT * FROM " + this.tableName + " WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		//param.setString((String) id);
		return super.query(sql, this.baseModelClass, param);
	}
	
/*	private int getTypes(Object value) {
		if (value instanceof String) {
			return Types.VARCHAR;
		} else if (value instanceof Date) {
			return Types.DATE;
		} else if (value instanceof Integer) {
			return Types.INTEGER;
		} else if (value instanceof Long) {
			return Types.BIGINT;
		} else if (value instanceof Double) {
			return Types.DOUBLE;
		} else if (value instanceof Float) {
			return Types.FLOAT;
		} else {
			throw new RuntimeException("未知参数类型[" + value + "].");
		}
	}*/

	public List<T> findByBaseId(Object id) throws SQLException {
		if(existBaseTable) {
			String sql = "SELECT * FROM " + this.tableName + " WHERE " + this.baseTableIdName + " = ? AND IS_DELETED=false";
			StatementParameter param = new StatementParameter();
			//param.setString((String) id);
			param.setObject(id);
			return super.queryForList(sql, this.baseModelClass, param);
		} else {
			logger.error("Base Table not found");
			return null;
		}
	}

	public T getByLang(Object id, String lang) throws SQLException {
		if(existBaseTable) {
			String sql = "SELECT * FROM " + this.tableName + " WHERE " + this.baseTableIdName + " = ? AND LANG = ? AND IS_DELETED=false";
			StatementParameter param = new StatementParameter();
			param.setObject(id);
			param.setString(lang);
			return super.query(sql, this.baseModelClass, param);
		} else {
			logger.error("Base Table not found");
			return null;
		}
	}
	
	public List<T> findByIds(Collection<Object> keys) {
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(this.tableName).append(" WHERE ID in (:keys)");
		Map<String, Collection<Object>> paramMap = Collections.singletonMap("keys", keys);
		return super.queryForListWithNamedParam(sql.toString(), this.baseModelClass, paramMap);
	}
	
	/**
	 * Get paging list
	 * @param object
	 * @param criteria
	 * @param args
	 * @return
	 */
	public PagingList<T> getPagingList(Class<T> object, StringBuilder sqlCount, StringBuilder sql, StatementParameter param, int... args) {
		final PagingList<T> resultList = new ArrayPagingList<T>();
		
		if (args == null || args.length < 2) {
			if (null != param) {
				resultList.addAll(super.queryForList(sql.toString(), this.baseModelClass, param));
			} else {
				resultList.addAll(super.queryForList(sql.toString(), this.baseModelClass));
			}
			return resultList;
		}
		
		final int startRecord = args[0];
		final int maxRecords = args[1];
		
		int page = startRecord / maxRecords;
		Long totalRecords = (null != param) ? (long) super.queryForInt(sqlCount.toString(), param) : super.queryForInt(sqlCount.toString());
		
		// set startRecord and maxRecord to sql
		sql.append(" LIMIT ?, ? ");
		if (null != param) {
			param.setInt(startRecord);
			param.setInt(maxRecords);
		} else {
			param = new StatementParameter();
			param.setInt(startRecord);
			param.setInt(maxRecords);
		}
		
		resultList.setStartRecord(startRecord);
		resultList.setMaxRecords(maxRecords);
		resultList.setTotalRecords(totalRecords);
		resultList.setPage(page);
		resultList.setTotalPages(new BigDecimal(totalRecords).divide(new BigDecimal(maxRecords), BigDecimal.ROUND_UP).intValue());
		resultList.addAll(super.queryForList(sql.toString(), this.baseModelClass, param));
		
		if (log) {
			logger.info("result size:" + resultList.size() + " sql:" + this.getSQL(sql.toString(), param));
		}
		return resultList;
	}
	
	
	/**
	 * Get paging list and sort Field
	 * @param object
	 * @param criteria
	 * @param args
	 * @return
	 */
	public PagingList<T> getPagingList(Class<T> object, StringBuilder sqlCount, StringBuilder sql, StatementParameter param,String sortField,String sortType,String tableAlias,int... args) {
		if (StringUtils.isNotEmpty(sortField)) {
			 sortField = underscoreName(sortField);
			if(tableAlias != null){
			  sortField = tableAlias+"."+sortField;
			} 
			if ("DESC".equals(sortType)) {
				sqlCount.append(" ORDER BY "+sortField+" DESC ");
				sql.append(" ORDER BY "+sortField+" DESC ");
			} else {
				sqlCount.append(" ORDER BY "+sortField+" ASC ");
				sql.append(" ORDER BY "+sortField+" ASC ");
			}
		}
		return getPagingList(object, sqlCount, sql, param, args);
	}
	
	public boolean remove(Object id, String updatedBy) {
		StringBuilder sql = new StringBuilder("UPDATE ").append(this.tableName).append(" SET IS_DELETED = 1, UPDATED_TIME = ?, UPDATED_BY = ? WHERE ID = ?");
		StatementParameter param = new StatementParameter();
		param.setDate(new Date());
		param.setString(updatedBy);
		try {
			param.setObject(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.updateForBoolean(sql.toString(), param);
	}
	
	public List<T> findAll() {
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(this.tableName);
		return super.queryForList(sql.toString(), this.baseModelClass);
	}
	
	public List<T> findAll(int...args) {
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM ").append(this.tableName);
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(this.tableName);
    	return getPagingList(this.baseModelClass, sqlCount, sql,null,args);
	}
	
	public List<T> findAll(String sortField, String sortType, int...args) {
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(*) FROM ").append(this.tableName);
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(this.tableName);
		return getPagingList(this.baseModelClass, sqlCount, sql, null,sortField,sortType,null,args);
	}
	
	public Integer deleteByIds(Collection<Object> keys) {
		StringBuilder sql = new StringBuilder("DELETE FROM ").append(this.tableName).append(" WHERE ID in (:keys)");
		Map<String, Collection<Object>> paramMap = Collections.singletonMap("keys", keys);
		return super.updateForRecordWithNamedParam(sql.toString(), paramMap);
	}
	
	public static void main (String aa[]){
		String name="PillarPlan";
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toUpperCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toUpperCase());
				} else {
					result.append(s.toUpperCase());
				}
			}
			//result.append(name.toUpperCase());
			
		}
		System.out.println(result.toString());
	}
}
