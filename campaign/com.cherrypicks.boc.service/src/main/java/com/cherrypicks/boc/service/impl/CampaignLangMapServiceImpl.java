package com.cherrypicks.boc.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.ICampaignLangMapDao;
import com.cherrypicks.boc.model.CampaignLangMap;
import com.cherrypicks.boc.service.ICampaignLangMapService;

public class CampaignLangMapServiceImpl extends AbstractBaseService<CampaignLangMap> implements ICampaignLangMapService{
	
	@Autowired
	private ICampaignLangMapDao campaignLangMapDao;
	
	@Resource(name ="campaignLangMapDao")
	public void setBaseDao(IBaseDao<CampaignLangMap> baseDao){
		this.baseDao = baseDao;
	}
	/**
	 * find Campaign Lang Map Exists
	 * 
	 * check Campaign Language is not exists
	 * 
	 * @param campaignId
	 * @param langCode
	 * @return
	 */
	public CampaignLangMap findCampaignLangMapExists(Object campaignId,
			String langCode) {
		

		return null;
	}
	
}
