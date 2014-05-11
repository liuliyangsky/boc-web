package com.cherrypicks.bos.api.model;

import java.io.Serializable;

public class CouponDetailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6099655850698004165L;
	
	public static int NO_KEWEEW=0;
	
	public static int IS_KEWEEW=1;

	private Long campaignId;
	
	private Long couponId;
	
	private String couponName;
	
	private String couponContent;
	
	private String couponDetail;
	
	private String couponTc;
	
	private String couponIcon;
	
	private String couponBanner;
	
	private String couponCode;
	
	private Integer couponType;//1.none.2.QR code.3條形碼4.passCode
	
	private String startDate;
	
	private String endDate;
	
	private Integer status;
	
	private Integer isKeew=0;//0:no,1:yes
	
	private Long  merchantId;

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

	public String getCouponContent() {
		return couponContent;
	}

	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}

	public String getCouponDetail() {
		return couponDetail;
	}

	public void setCouponDetail(String couponDetail) {
		this.couponDetail = couponDetail;
	}

	public String getCouponTc() {
		return couponTc;
	}

	public void setCouponTc(String couponTc) {
		this.couponTc = couponTc;
	}

	public String getCouponIcon() {
		return couponIcon;
	}

	public void setCouponIcon(String couponIcon) {
		this.couponIcon = couponIcon;
	}

	public String getCouponBanner() {
		return couponBanner;
	}

	public void setCouponBanner(String couponBanner) {
		this.couponBanner = couponBanner;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsKeew() {
		return isKeew;
	}

	public void setIsKeew(Integer isKeew) {
		this.isKeew = isKeew;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	
}
