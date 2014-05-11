package com.cherrypicks.boc.api.dao;

import java.sql.SQLException;
import java.util.List;

import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;

public interface CampaignCouponDao {

	List<CampaignCouponModel> getCouponList(String lang, String campaignId);

	CouponDetailModel getCouponDetail(String lang, String couponId);
	
	CampaignCoupon getById(Long id) throws SQLException;
	
	/**
	 * update GN_SUM+1
	 */
	public boolean updateCouponGnSum(Long id);
	
	/**
	 * update RN_SUM+1
	 */
	public boolean updateCouponRnSum(Long id);
}
