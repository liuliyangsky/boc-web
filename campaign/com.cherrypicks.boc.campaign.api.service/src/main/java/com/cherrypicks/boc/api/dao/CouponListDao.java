package com.cherrypicks.boc.api.dao;

import java.util.List;

import com.cherrypicks.bos.api.model.CouponListModel;
public interface CouponListDao {

	List<CouponListModel> getMycouponList(String userId,String lang);
	
}
