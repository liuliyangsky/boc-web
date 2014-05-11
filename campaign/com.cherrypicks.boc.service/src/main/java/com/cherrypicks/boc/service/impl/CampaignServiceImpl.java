package com.cherrypicks.boc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.ICampaignDao;
import com.cherrypicks.boc.join.model.CampaignLanguage;
import com.cherrypicks.boc.model.Campaign;
import com.cherrypicks.boc.service.ICampaignService;

public class CampaignServiceImpl extends AbstractBaseService<Campaign> implements ICampaignService{
	
	@Autowired
	private ICampaignDao campaignDao;
	
	@Resource(name="campaignDao")
	public void setBaseDao(IBaseDao<Campaign> baseDao) {
		this.baseDao = baseDao;
	}

	public CampaignLanguage findCampaignAndLanguageById(Object id) {
		
		return campaignDao.findCampaignAndLanguageById(id);
	}

	public List<CampaignLanguage> findCampaignAndLanguageAll() {
		
		return campaignDao.findCampaignAndLanguageAll();
	}
}
