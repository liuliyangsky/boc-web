package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.dao.ISystemFunctionDao;
import com.cherrypicks.boc.model.SystemFunction;


public class SystemFunctionDaoImpl extends AbstractBaseDaoDB2<SystemFunction> implements ISystemFunctionDao {

	@Override
	public List<SystemFunction> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		return null;
	}

}
