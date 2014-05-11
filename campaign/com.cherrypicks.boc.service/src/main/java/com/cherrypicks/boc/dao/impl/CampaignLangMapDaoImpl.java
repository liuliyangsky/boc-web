package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ICampaignLangMapDao;
import com.cherrypicks.boc.model.CampaignLangMap;

public class CampaignLangMapDaoImpl extends AbstractBaseDaoDB2<CampaignLangMap> implements ICampaignLangMapDao{

	public List<CampaignLangMap> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public CampaignLangMap findCampaignLangMapExists(Object campaignId,
			String langCode) {
		StringBuffer stringBufferSql = new StringBuffer();
		stringBufferSql.append("SELECT * FROM BOC_CAMPAIGN.CAMPAIGN_LANG_MAP WHERE CAMPAIGN_ID = ? AND CODE = ?");
		StatementParameter param = new StatementParameter();
		return query(stringBufferSql.toString(), CampaignLangMap.class, param);
	}

}
