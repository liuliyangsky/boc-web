package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class UserDeviceCoupon extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264108018291587875L;
	public static final Integer ACTION_GET=1;
	public static final Integer ACTION_REDEEM=2;
	private String deviceId;
	
	private String userId;
	
	private Object couponQuotaId;
	
	private Object couponId;
	
	private Integer actionType; //1 還未使用;2已經使用

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Object getCouponQuotaId() {
		return couponQuotaId;
	}

	public void setCouponQuotaId(Object couponQuotaId) {
		this.couponQuotaId = couponQuotaId;
	}

	public Object getCouponId() {
		return couponId;
	}

	public void setCouponId(Object couponId) {
		this.couponId = couponId;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
}
