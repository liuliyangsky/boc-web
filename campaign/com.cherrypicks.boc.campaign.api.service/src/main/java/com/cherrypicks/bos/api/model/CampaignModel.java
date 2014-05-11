package com.cherrypicks.bos.api.model;

import java.io.Serializable;

/**
 * 
 * @author more.li
 *
 */
public class CampaignModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999675528559174931L;

	private Long campaignId;
	
	private String startDate;
	
	private String endDate;
	
	private String campaignIcon;
	
	private String campaignName;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCampaignIcon() {
		return campaignIcon;
	}

	public void setCampaignIcon(String campaignIcon) {
		this.campaignIcon = campaignIcon;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
}
