package com.cherrypicks.boc.api.service;

import java.util.List;

import com.cherrypicks.bos.api.model.CouponListModel;

public interface CouponService {

	 List<CouponListModel> getCouponList(String userId,String lang);


}
