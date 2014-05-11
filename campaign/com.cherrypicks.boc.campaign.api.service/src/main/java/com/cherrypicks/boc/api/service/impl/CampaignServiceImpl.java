package com.cherrypicks.boc.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherrypicks.boc.api.dao.CampaignCouponDao;
import com.cherrypicks.boc.api.dao.CampaignDao;
import com.cherrypicks.boc.api.dao.MerchantDao;
import com.cherrypicks.boc.api.service.CampaignService;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CampaignModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;
import com.cherrypicks.bos.api.model.MerchantDetailModel;

/**
 * 
 * @author kelvin.tie
 */
@Service
public class CampaignServiceImpl extends Context implements CampaignService {

	@Autowired
	private  CampaignDao campaignDao;
	
	@Autowired
	private CampaignCouponDao campaignCouponDao;
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public List<CampaignModel> getAllCampaign(String lang) {
		return campaignDao.getAllCampaign(lang);
	}

	@Override
	public List<CampaignCouponModel> getCouponList(String lang, String campaignId) {
		return campaignCouponDao.getCouponList(lang,campaignId);
	}

	@Override
	public CouponDetailModel getCouponDetail(String lang, String couponId) {
		return campaignCouponDao.getCouponDetail(lang,couponId);
	}

	@Override
	public MerchantDetailModel getMerchantDetail(String lang, String merchantId) {
		return merchantDao.getMerchantDetail(lang,merchantId);
	}



}
