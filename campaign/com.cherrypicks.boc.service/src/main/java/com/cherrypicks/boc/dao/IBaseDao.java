package com.cherrypicks.boc.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.model.AbstractEntity;


public interface IBaseDao<T extends AbstractEntity> {
	
	public T add(T object);
	
	public T add(T object,String schemaName);

	public T update(T object) throws SQLException;
	
	public boolean del(Object key) throws SQLException;

	public Integer deleteByIds(Collection<Object> keys);

	public boolean remove(Object key, String updatedBy);

	public T get(Object key) throws SQLException;
	
	public List<T> findAll();

	public List<T> findAll(int... args);

	public List<T> findAll(String orderField, String orderType, int... args);

	public List<T> findByIds(Collection<Object> ids);

	public List<T> findByFilter(Map<String, Object> criteriaMap,String sortField, String sortType,int... args);
	
	
}
