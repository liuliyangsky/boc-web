package com.cherrypicks.boc.cms.vo;

import java.util.Date;

public class CamPaignVo extends AbstractEntity{
	
	private Date startDate;
	
	private String campaignName;
	
	private String icon;
	
	private String langCode;
	
	private Date endDate;
	
	private Integer playType;
	
	private Integer campaignType;
	
	private  Integer displayOrder;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

	public Integer getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(Integer campaignType) {
		this.campaignType = campaignType;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
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
