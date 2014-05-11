package com.cherrypicks.boc.api.service;

import java.util.List;

import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CampaignModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;
import com.cherrypicks.bos.api.model.MerchantDetailModel;


/**
 * 
 * @author kelvin.tie
 */
public interface CampaignService {

	List<CampaignModel> getAllCampaign(String lang);

	List<CampaignCouponModel> getCouponList(String lang, String campaignId);

    CouponDetailModel getCouponDetail(String lang, String couponId);

	MerchantDetailModel getMerchantDetail(String lang, String merchantId);
	 
	

}
