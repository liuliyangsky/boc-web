package com.cherrypicks.boc.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.model.AbstractEntity;


public interface IBaseService<T extends AbstractEntity> {

	public T add(T object);

	public T update(T object);

	public void delete(T object);

	public void remove(Collection<Object> ids, String updatedBy);

	public T findById(Object key);

	public List<T> findAll();

	public List<T> findAll(int... args);

	public List<T> findAll(String sortField, String sortType);
	
	public List<T> findAll(String sortField, String sortType,int... args);

	public List<T> findByIds(Collection<Object> ids);

	public List<T> findByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int... args);

}
