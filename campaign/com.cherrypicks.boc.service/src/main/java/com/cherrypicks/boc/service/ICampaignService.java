package com.cherrypicks.boc.service;

import java.util.List;

import com.cherrypicks.boc.join.model.CampaignLanguage;
import com.cherrypicks.boc.model.Campaign;

public interface ICampaignService extends IBaseService<Campaign>{
	
	
	public CampaignLanguage findCampaignAndLanguageById(Object id); 
	
	
	public List<CampaignLanguage> findCampaignAndLanguageAll();
	

}
