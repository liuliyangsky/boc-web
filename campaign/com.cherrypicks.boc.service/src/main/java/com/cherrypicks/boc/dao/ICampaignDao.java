package com.cherrypicks.boc.dao;

import java.util.List;

import com.cherrypicks.boc.join.model.CampaignLanguage;
import com.cherrypicks.boc.model.Campaign;

public interface ICampaignDao extends IBaseDao<Campaign> {
	
	
	public CampaignLanguage findCampaignAndLanguageById(Object id);
	
	public List<CampaignLanguage> findCampaignAndLanguageAll();
	
}
