package com.cherrypicks.boc.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.CouponListDao;
import com.cherrypicks.boc.api.dao.db2.CouponListDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.CouponListModel;

@Repository
public class CouponListDaoImpl extends Context implements CouponListDao{
    
	@Autowired
	private CouponListDaoDB2 couponListDaoDB2;
	
	@Override
	public List<CouponListModel> getMycouponList(String userId,String lang) {
	
		return couponListDaoDB2.getMyounponList(userId,lang);
	}



}
