package com.cherrypicks.bos.api.model;

import java.io.Serializable;
import java.util.Date;

public class CouponListModel implements Serializable {

	/**
	 * @author Alan.kuang
	 */
	private static final long serialVersionUID = 1207651545460092552L;

	public Long campaignid;
	
	public Long userCouponId;
	
	public Long couponId;
	
	public String startDate;
	
	public String endDate;
	
	public String couponName;
	
	public String couponIcon;
	
	public int isKeew=0;
	
	public String status;

	public Long getCampaignid() {
		return campaignid;
	}

	public void setCampaignid(Long campaignid) {
		this.campaignid = campaignid;
	}

	public Long getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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

	public int getIsKeew() {
		return isKeew;
	}

	public void setIsKeew(int isKeew) {
		this.isKeew = isKeew;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
