package com.cherrypicks.boc.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherrypicks.boc.api.dao.CouponListDao;
import com.cherrypicks.boc.api.dao.impl.CouponListDaoImpl;
import com.cherrypicks.boc.api.service.CouponService;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.CouponListModel;
@Service
public class CouponServiceImpl extends Context implements CouponService{
    
	@Autowired
	private CouponListDao couponListDao;

	
	@Override
	public List<CouponListModel> getCouponList(String userId,String lang) {
		return couponListDao.getMycouponList(userId,lang);
	}

}
