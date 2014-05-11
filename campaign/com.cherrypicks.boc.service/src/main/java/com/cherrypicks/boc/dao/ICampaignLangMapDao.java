package com.cherrypicks.boc.dao;

import com.cherrypicks.boc.model.CampaignLangMap;

public interface ICampaignLangMapDao extends IBaseDao<CampaignLangMap>{
	
	/**
	 * find Campaign Lang Map Exists
	 * 
	 * check Campaign Language is not exists
	 * 
	 * @param campaignId
	 * @param langCode
	 * @return
	 */
	public CampaignLangMap findCampaignLangMapExists(Object campaignId,String langCode) ;

}
