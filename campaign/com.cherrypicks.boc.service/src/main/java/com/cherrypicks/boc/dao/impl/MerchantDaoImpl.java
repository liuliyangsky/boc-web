package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.dao.IMerchantDao;
import com.cherrypicks.boc.model.Merchant;

public class MerchantDaoImpl extends AbstractBaseDaoDB2<Merchant> implements IMerchantDao{


	public List<Merchant> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
