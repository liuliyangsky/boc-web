package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.dao.IActivityDao;
import com.cherrypicks.boc.model.Activity;

public class ActivityDaoImpl extends AbstractBaseDaoDB2<Activity> implements IActivityDao{

	
	
	public List<Activity> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		
		
		return null;
	}

}
