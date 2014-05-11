package com.cherrypicks.boc.model;

import java.util.Date;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class Campaign extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6907723258098347064L;
	
	private Date startDate;
	
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

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}
	
}
