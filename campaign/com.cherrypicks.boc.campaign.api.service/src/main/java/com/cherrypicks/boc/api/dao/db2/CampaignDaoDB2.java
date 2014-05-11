package com.cherrypicks.boc.api.dao.db2;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.Campaign;
import com.cherrypicks.bos.api.model.CampaignModel;

@Repository
public class CampaignDaoDB2 extends AbstractBaseDaoDB2<Campaign>{

	public List<CampaignModel> getAllCampaign(String lang) {
		StatementParameter param=new StatementParameter();
		param.setString(lang);
		List<CampaignModel> campaignList=this.queryForList("select C.ID campaignId,to_char(C.START_DATE,'YYYY-MM-dd') startDate,to_char(C.END_DATE,'YYYY-MM-dd') endDate,M.CAMPAIGN_NAME campaignName,M.ICON campaignIcon from "+
		
		DBSchemaEnum.BOC_CAMPAIGN+"."+this.tableName+" C left join "+DBSchemaEnum.BOC_CAMPAIGN+"."+
				
		"CAMPAIGN_LANG_MAP M ON M.CAMPAIGN_ID = C.ID where M.LANG_CODE=? ORDER BY C.DISPLAY_ORDER", CampaignModel.class,param );
		return  campaignList;
	}

}
