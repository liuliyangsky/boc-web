package com.cherrypicks.boc.api.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.CampaignCouponDao;
import com.cherrypicks.boc.api.dao.db2.CampaignCouponDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;

@Repository
public class CampaignCouponDaoImpl extends Context implements CampaignCouponDao{

	@Autowired
	private CampaignCouponDaoDB2 campaignCouponDaoDB2;
	
	@Override
	public List<CampaignCouponModel> getCouponList(String lang,String campaignId) {
		return campaignCouponDaoDB2.getCouponList(lang,campaignId);
	}

	@Override
	public CouponDetailModel getCouponDetail(String lang, String couponId) {
		return campaignCouponDaoDB2.getCouponDetail(lang,couponId);
	}

	@Override
	public CampaignCoupon getById(Long id) throws SQLException {
		return campaignCouponDaoDB2.get(id, DBSchemaEnum.BOC_CAMPAIGN.toString());
	}

	@Override
	public boolean updateCouponGnSum(Long id) {	
		return campaignCouponDaoDB2.updateCouponGnSum(id);
	}

	@Override
	public boolean updateCouponRnSum(Long id) {
		return campaignCouponDaoDB2.updateCouponRnSum(id);
	}

}
