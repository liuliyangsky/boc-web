package com.cherrypicks.boc.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.CampaignDao;
import com.cherrypicks.boc.api.dao.db2.CampaignDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.CampaignModel;

@Repository
public class CampaignDaoImpl extends Context implements CampaignDao {

	@Autowired
	private CampaignDaoDB2 campaignDaoDB2;
	
	@Override
	public List<CampaignModel> getAllCampaign(String lang) {
		return campaignDaoDB2.getAllCampaign(lang);
	}

}
