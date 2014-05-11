package com.cherrypicks.bos.api.model;

import java.io.Serializable;

public class CampaignCouponModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8963214061292793753L;
	
	private Long campaignId;
	
	private Long couponId;
	
	private String couponName;
	
	private String couponIcon;
	
	private String startDate;
	
	private String endDate;
	
	private Integer status;//0:已经过期，1：未过期

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponIcon() {
		return couponIcon;
	}

	public void setCouponIcon(String couponIcon) {
		this.couponIcon = couponIcon;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
