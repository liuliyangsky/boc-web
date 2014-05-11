package com.cherrypicks.boc.service.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.model.AbstractEntity;
import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.service.IBaseService;

public abstract class AbstractBaseService<T extends AbstractEntity> implements IBaseService<T> {
	
	protected IBaseDao<T> baseDao;

	public T add(T object) {
		if (null == object) {
			throw new IllegalArgumentException();
		}
		return baseDao.add(object);
	}

	public T update(T object) {
		if (null == object) {
			throw new IllegalArgumentException();
		}
		try {
			return baseDao.update(object);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   return null;
	}

	public void delete(T object) {
		if (null == object) {
			throw new IllegalArgumentException();
		}
		try {
			baseDao.del(object.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Collection<Object> ids, String updatedBy) {
		if (null == ids) {
			throw new IllegalArgumentException();
		}
		baseDao.deleteByIds(ids);
	}

	public T findById(Object key) {
		if (null == key) {
			throw new IllegalArgumentException();
		}
		try {
			return baseDao.get(key);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		    return null;
	}

	public List<T> findAll() {
		return baseDao.findAll();
	}

	public List<T> findAll(int... args) {
		return baseDao.findAll(args);
	}

	public List<T> findAll(String sortField, String sortType) {
		return baseDao.findAll(sortField, sortType, null);
	}
	
	public List<T> findAll(String sortField, String sortType,int... args){
		return baseDao.findAll(sortField, sortType, args);
	}

	public List<T> findByIds(Collection<Object> ids) {
		if (null == ids) {
			throw new IllegalArgumentException();
		}
		return baseDao.findByIds(ids);
	}

	public List<T> findByFilter(Map<String, Object> criteriaMap,String sortField, String sortType,int... args) {
		if (null == criteriaMap) {
			throw new IllegalArgumentException();
		}
		return baseDao.findByFilter(criteriaMap,sortField,sortType,args);
	}

	public IBaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

}
