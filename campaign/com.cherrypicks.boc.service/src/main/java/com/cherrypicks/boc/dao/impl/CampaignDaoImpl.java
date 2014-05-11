package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ICampaignDao;
import com.cherrypicks.boc.join.model.CampaignLanguage;
import com.cherrypicks.boc.model.Campaign;

public class CampaignDaoImpl extends AbstractBaseDaoDB2<Campaign> implements ICampaignDao{

	
	public List<Campaign> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public CampaignLanguage findCampaignAndLanguageById(Object id) {
		StringBuffer stringBufferSql = new StringBuffer();
		stringBufferSql.append("SELECT CAMPAIGN.ID ,CAMPAIGN.START_DATE,CAMPAIGN.END_DATE,CAMPAIGN.CAMPAIGN_TYPE,CAMPAIGN.PLAY_TYPE,CAMPAIGN.DISPLAY_ORDER,");
		stringBufferSql.append("CAMPAIGN.CREATED_TIME,CAMPAIGN.CREATED_BY,CAMPAIGN.UPDATED_TIME,CAMPAIGN.UPDATED_BY,");
		stringBufferSql.append("CAMPAIGN_LANG.CAMPAIGN_NAME ,CAMPAIGN_LANG.ICON,CAMPAIGN_LANG.LANG_CODE ");
		stringBufferSql.append("FROM BOC_CAMPAIGN.CAMPAIGN CAMPAIGN LEFT OUTER JOIN BOC_CAMPAIGN.CAMPAIGN_LANG_MAP CAMPAIGN_LANG ON ");
		stringBufferSql.append("CAMPAIGN.ID = CAMPAIGN_LANG.CAMPAIGN_ID");
		StatementParameter param = new StatementParameter();
		List<CampaignLanguage> campaignLanguageList = queryForList(stringBufferSql.toString(), CampaignLanguage.class, param);
		if(campaignLanguageList != null && !campaignLanguageList.isEmpty()){
			return campaignLanguageList.get(0);
		}
		return null;
	}

	@Override
	public List<CampaignLanguage> findCampaignAndLanguageAll() {
		StringBuffer stringBufferSql = new StringBuffer();
		stringBufferSql.append("SELECT CAMPAIGN.ID ,CAMPAIGN.START_DATE,CAMPAIGN.END_DATE,CAMPAIGN.CAMPAIGN_TYPE,CAMPAIGN.PLAY_TYPE,CAMPAIGN.DISPLAY_ORDER,");
		stringBufferSql.append("CAMPAIGN.CREATED_TIME,CAMPAIGN.CREATED_BY,CAMPAIGN.UPDATED_TIME,CAMPAIGN.UPDATED_BY,");
		stringBufferSql.append("CAMPAIGN_LANG.CAMPAIGN_NAME ,CAMPAIGN_LANG.ICON,CAMPAIGN_LANG.LANG_CODE ");
		stringBufferSql.append("FROM BOC_CAMPAIGN.CAMPAIGN CAMPAIGN LEFT OUTER JOIN BOC_CAMPAIGN.CAMPAIGN_LANG_MAP CAMPAIGN_LANG ON ");
		stringBufferSql.append("CAMPAIGN.ID = CAMPAIGN_LANG.CAMPAIGN_ID");
		StatementParameter param = new StatementParameter();
		List<CampaignLanguage> campaignLanguageList = queryForList(stringBufferSql.toString(), CampaignLanguage.class, param);
		return campaignLanguageList;
	}

}
