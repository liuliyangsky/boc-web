package com.cherrypicks.boc.cms.dmi;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.security.UserLoginHelper;
import com.cherrypicks.boc.model.Campaign;
import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.boc.service.IBaseService;
import com.cherrypicks.boc.service.ICampaignCouponService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class CampaignCouponDMI extends AbstractDMI<CampaignCoupon>{
	
	@Autowired
	private ICampaignCouponService campaignCouponService;
	
	@Resource(name="campaignCouponService")
	public void setBaseService(IBaseService<CampaignCoupon> baseService){
		this.baseService = baseService;
	}
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response) {
		long startRow = dsRequest.getStartRow();
		long endRow = dsRequest.getEndRow();
		DSResponse dsResponse = new DSResponse();
		List<CampaignCoupon> campaignCouponList = campaignCouponService.findAll();
		dsResponse.setData(campaignCouponList);
		return dsResponse;
	}
	/**
	 * add CamPaign
	 * @param dsRequest
	 * @param record
	 * @return
	 */
	public DSResponse add(DSRequest dsRequest,Campaign record){
		DSResponse dsResponse = new DSResponse();
		try{
			record.setCreatedTime(new Date());
			record.setCreatedBy(UserLoginHelper.getLoginUserName());
			dsResponse.setData(null);
			dsResponse.setSuccess();
		}catch (Exception err){
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	public DSResponse remove(DSRequest dsRequest) {
		DSResponse dsResponse = new DSResponse();
		try {
			dsResponse.setSuccess();
			dsResponse.setData(null);
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}

}
