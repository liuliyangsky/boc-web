package com.cherrypicks.boc.api.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.MerchantDao;
import com.cherrypicks.boc.api.dao.db2.MerchantDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.MerchantDetailModel;

@Repository
public class MerchantDaoImpl  extends Context implements MerchantDao{

	@Autowired
	private MerchantDaoDB2 merchantDaoDB2;
	
	@Override
	public MerchantDetailModel getMerchantDetail(String lang, String merchantId) {
		return merchantDaoDB2.getMerchantDetail(lang,merchantId);
	}

}
