package com.cherrypicks.boc.service;

import com.cherrypicks.boc.model.CampaignLangMap;

public interface ICampaignLangMapService extends IBaseService<CampaignLangMap>{
	
	/**
	 * find Campaign Lang Map Exists
	 * 
	 * check Campaign Language is not exists
	 * 
	 * @param campaignId
	 * @param langCode
	 * @return
	 */
	public CampaignLangMap findCampaignLangMapExists(Object campaignId,String langCode);
	

}
