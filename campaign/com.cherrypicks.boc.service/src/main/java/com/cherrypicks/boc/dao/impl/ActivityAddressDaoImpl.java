package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.dao.IActivityAddressDao;
import com.cherrypicks.boc.model.ActivityAddress;

public class ActivityAddressDaoImpl extends AbstractBaseDaoDB2<ActivityAddress> implements IActivityAddressDao{

	public List<ActivityAddress> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		
		return findAll(sortField, sortType, args);
	}

}
