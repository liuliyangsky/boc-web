package com.cherrypicks.boc.api.dao;

import java.util.List;

import com.cherrypicks.bos.api.model.CampaignModel;

public interface CampaignDao {

	List<CampaignModel> getAllCampaign(String lang);

}
