package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class CampaignLangMap extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8212885677627508755L;
	
	private Object campaignId;
	
	private String campaignName;
	
	private String icon;
	
	private String langCode;

	public Object getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Object campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
	

}
