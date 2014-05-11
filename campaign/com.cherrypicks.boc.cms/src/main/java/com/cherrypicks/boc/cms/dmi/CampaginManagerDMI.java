package com.cherrypicks.boc.cms.dmi;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.security.UserLoginHelper;
import com.cherrypicks.boc.cms.vo.CamPaignVo;
import com.cherrypicks.boc.join.model.CampaignLanguage;
import com.cherrypicks.boc.model.Campaign;
import com.cherrypicks.boc.model.CampaignLangMap;
import com.cherrypicks.boc.model.Merchant;
import com.cherrypicks.boc.service.IBaseService;
import com.cherrypicks.boc.service.ICampaignLangMapService;
import com.cherrypicks.boc.service.ICampaignService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class CampaginManagerDMI extends AbstractDMI<Campaign> {
	 
	@Autowired
	private ICampaignService campaignService;
	
	@Autowired
	private ICampaignLangMapService campaignLangMapService;
	
	@Resource(name="campaignService")
	public void setbaseService(IBaseService<Campaign> baseService){
		this.baseService = baseService;
	}

	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response) {
		long startRow = dsRequest.getStartRow();
		long endRow = dsRequest.getEndRow();
		DSResponse dsResponse = new DSResponse();
		List<CampaignLanguage> campaignLanguageList = campaignService.findCampaignAndLanguageAll();
		dsResponse.setData(campaignLanguageList);
		return dsResponse;
	}
	/**
	 * add CamPaign
	 * @param dsRequest
	 * @param record
	 * @return
	 */
	public DSResponse add(DSRequest dsRequest,CamPaignVo record){
		DSResponse dsResponse = new DSResponse();
		try{
			Campaign campaign = new Campaign();
			BeanUtils.copyProperties(record, campaign);
			campaign.setCreatedTime(new Date());
			campaign.setCreatedBy(UserLoginHelper.getLoginUserName());
			campaign = campaignService.add(campaign);
			
			CampaignLangMap campaignLangMap = new CampaignLangMap();
			campaignLangMap.setCampaignId(campaign.getId());
			campaignLangMap.setCampaignName(record.getCampaignName());
			campaignLangMap.setIcon(record.getIcon());
			campaignLangMap.setCreatedTime(campaign.getCreatedTime());
			campaignLangMap.setCreatedBy(UserLoginHelper.getLoginUserName());
			campaignLangMap.setLangCode(record.getLangCode());
			campaignLangMapService.add(campaignLangMap);
			
			dsResponse.setData(campaign);
			dsResponse.setSuccess();
		}catch (Exception err){
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	public DSResponse update(DSRequest dsRequest, CamPaignVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			
			
			record.setUpdatedTime(new Date());
			dsResponse.setData(record);
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	public DSResponse remove(DSRequest dsRequest) {
		DSResponse dsResponse = new DSResponse();
		try {
			Map<String, List<Object>> criteria = dsRequest.getOldValues();
			List<Object> ids = (List<Object>) criteria.get("ids");
			if (null != ids) {
				List<Campaign> removeData = campaignService.findByIds(ids);
				if(null != removeData && !removeData.isEmpty()){
					campaignService.delete(removeData.get(0));
				}
				dsResponse.setSuccess();
				dsResponse.setData(removeData);
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
}
