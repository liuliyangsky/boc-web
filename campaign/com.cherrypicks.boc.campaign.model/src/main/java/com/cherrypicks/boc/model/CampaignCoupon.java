package com.cherrypicks.boc.model;

import java.util.Date;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class CampaignCoupon extends AbstractEntity {

	/**
	 * status for CampaignCoupon
	 * 0 - Pending
	 * 1 - Submit for approval
	 * 2 - Approved (Approved and waiting for start)
	 * 3 - ACTIVE
	 * 4 - Suspend
	 * 5 - Expired
	 * 6 - Deleted
	 */
	public static final int PENDING=0;
	public static final int SUBMIT_FOR_APPROVAL=1; 
	public static final int APPROVED=2;
	public static final int ACTIVE=3;
	public static final int SUSPEND=4;
	public static final int EXPIRED=5;
	public static final int DELETED=6;
	public static final int couponType_0=0;
	public static final int couponType_1=1;
	public static final int couponType_2=2;
	public static final int couponType_3=3;
	
	private static final long serialVersionUID = -283691623372710792L;

	private Object campaignId;
	
	private Integer couponType; // 0 無; 1 BarCode; 2 PassCode; 3 QRcode
	
	private String couponCode;
	
	private Integer displayOrer;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date  expiryDate;
	
	private Integer status;
	
	private Integer gnSum;
	
	private Integer rnSum;
	
	private Integer maxDistributionQuota;
	
	private Integer maxRedeemQuota;
	
	private Integer maxDistributionPreDevice;
	
	private Integer maxRedeemPreDevice;
	
	private Object merchantId;

	public Object getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Object campaignId) {
		this.campaignId = campaignId;
	}

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getDisplayOrer() {
		return displayOrer;
	}

	public void setDisplayOrer(Integer displayOrer) {
		this.displayOrer = displayOrer;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMaxDistributionQuota() {
		return maxDistributionQuota;
	}

	public void setMaxDistributionQuota(Integer maxDistributionQuota) {
		this.maxDistributionQuota = maxDistributionQuota;
	}

	public Integer getMaxRedeemQuota() {
		return maxRedeemQuota;
	}

	public void setMaxRedeemQuota(Integer maxRedeemQuota) {
		this.maxRedeemQuota = maxRedeemQuota;
	}

	public Integer getMaxDistributionPreDevice() {
		return maxDistributionPreDevice;
	}

	public void setMaxDistributionPreDevice(Integer maxDistributionPreDevice) {
		this.maxDistributionPreDevice = maxDistributionPreDevice;
	}

	public Integer getMaxRedeemPreDevice() {
		return maxRedeemPreDevice;
	}

	public void setMaxRedeemPreDevice(Integer maxRedeemPreDevice) {
		this.maxRedeemPreDevice = maxRedeemPreDevice;
	}

	public Object getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Object merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getGnSum() {
		return gnSum;
	}

	public void setGnSum(Integer gnSum) {
		this.gnSum = gnSum;
	}

	public Integer getRnSum() {
		return rnSum;
	}

	public void setRnSum(Integer rnSum) {
		this.rnSum = rnSum;
	}
	
}
