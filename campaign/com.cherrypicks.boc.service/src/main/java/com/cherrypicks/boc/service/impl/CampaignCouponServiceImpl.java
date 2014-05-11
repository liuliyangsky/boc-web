package com.cherrypicks.boc.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.ICampaignCouponDao;
import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.boc.service.ICampaignCouponService;

public class CampaignCouponServiceImpl extends AbstractBaseService<CampaignCoupon> implements ICampaignCouponService{
	
	@Autowired
	private ICampaignCouponDao campaignCouponDao;
	
	@Resource(name = "campaignCouponDao")
	public void setBaseDao(IBaseDao<CampaignCoupon> baseDao){
		this.baseDao = baseDao;
	}
	
}
