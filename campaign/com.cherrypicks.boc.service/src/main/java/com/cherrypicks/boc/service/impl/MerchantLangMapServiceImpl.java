package com.cherrypicks.boc.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.IMerchantLangMapDao;
import com.cherrypicks.boc.model.MerchantLangMap;
import com.cherrypicks.boc.service.IMerchantLangMapService;

public class MerchantLangMapServiceImpl extends AbstractBaseService<MerchantLangMap> implements IMerchantLangMapService{
	
	@Autowired
	private IMerchantLangMapDao merchantLangMapDao;
	
	@Resource(name="merchantLangMapDao")
	public void setBaseDao(IBaseDao<MerchantLangMap> baseDao){
		this.baseDao = baseDao;
	}


}
