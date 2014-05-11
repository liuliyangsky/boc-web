package com.cherrypicks.boc.api.dao.db2;

import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.cherrypicks.boc.common.jdbc.Jdbc;
import com.cherrypicks.boc.common.jdbc.SimpleStatementParameter;
import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.common.model.AbstractEntity;

public abstract class AbstractBaseDaoDB2<T extends AbstractEntity> extends Jdbc {
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
		this.tableName =underscoreName(this.baseModelClass.getSimpleName());
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
	public T add(T object,String schemaName) {
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

		object.setId(executeAndReturnKey(param,schemaName));

		return object;
	}
	
	/**
	 * 只適用于主键是由自己生成的表的情况下 
     * execute用于执行并获取自动生成主键（注意是Number类型）
	 */
	@SuppressWarnings("rawtypes")
	public T addPKObject(T object,String schemaName) {
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

		execute(param,schemaName);
		return object;
	}

	public boolean del(Object id,String schemaName) throws SQLException {
		String sql = "DELETE FROM " + this.getSchemaName_Table(schemaName,this.tableName) + " WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		return super.updateForBoolean(sql, param);
	}
	/**
	 * 邏輯刪除 標記 IS_DELETED=1
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public boolean delData(Object id,String schemaName) throws SQLException {
		String sql = "UPDATE " + this.getSchemaName_Table(schemaName,this.tableName) + " SET IS_DELETED=1  WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		return super.updateForBoolean(sql, param);
	}

	@SuppressWarnings("rawtypes")
	public boolean update(T object,String schemaName) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE " + this.getSchemaName_Table(schemaName,this.tableName) + " SET");
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

		sql.append(" WHERE ID=? AND IS_DELETED!=1");
		param.setObject(object.getId());
		
		if (param.size() > 0) {
			return super.updateForBoolean(sql.toString(), param);
		} else {
			return false;
		}
	}

	public T get(Object id,String schemaName) throws SQLException {
		String sql = "SELECT * FROM " +getSchemaName_Table(schemaName,this.tableName) + " WHERE ID = ? AND IS_DELETED!=1";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		return super.query(sql, this.baseModelClass, param);
	}
	
	public T defaultGet(Object id,String schemaName) throws SQLException {
		String sql = "SELECT * FROM " + getSchemaName_Table(schemaName,this.tableName) + " WHERE ID = ?";
		StatementParameter param = new StatementParameter();
		param.setObject(id);
		return super.query(sql, this.baseModelClass, param);
	}

	public List<T> findByBaseId(Object id,String schemaName) throws SQLException {
		if(existBaseTable) {
			String sql = "SELECT * FROM " + getSchemaName_Table(schemaName,this.tableName) + " WHERE " + this.baseTableIdName + " = ? AND IS_DELETED!=1";
			StatementParameter param = new StatementParameter();
			//param.setString((String) id);
			param.setObject(id);
			return super.queryForList(sql, this.baseModelClass, param);
		} else {
			logger.error("Base Table not found");
			return null;
		}
	}
	
	public List<T> findAll(String schemaName) throws SQLException {
		if(existBaseTable) {
			String sql = "SELECT * FROM " + getSchemaName_Table(schemaName,this.tableName) + " WHERE IS_DELETED!=1";
			StatementParameter param = new StatementParameter();
			
			return super.queryForList(sql, this.baseModelClass, param);
		} else {
			logger.error("Base Table not found");
			return null;
		}
	}


	protected Long executeAndReturnKey(SimpleStatementParameter param,String schemaName) {
		
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource());
		insertActor.withSchemaName(schemaName).withTableName(param.getTable()).usingGeneratedKeyColumns(param.getKeyColumn());

		Number newId = insertActor.executeAndReturnKey(param.getParameters());
		return newId.longValue();
	}
	

	protected Boolean execute(SimpleStatementParameter param,String schemaName) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(this.getJdbcTemplate().getDataSource());
		insertActor.withSchemaName(schemaName).withTableName(param.getTable());
		Integer row = insertActor.execute(param.getParameters());
		if(row>0){
			return true;
		}
		return false;
	}

	private String getSchemaName_Table(String schemaName,String tableName){
		if(StringUtils.isBlank(schemaName)){
			return tableName;
		}
		return schemaName+"."+tableName;
	}
}
