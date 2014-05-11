package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;

public class CampaignActionLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 738954274270661207L;
	
	private String deviceId;
	
	private String deviceOs;
	
	private String deviceNum;
	
	private String bankName;
	
	private Integer actionType;//1 download offer;2redeem offer
	
	private String userId; 

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceOs() {
		return deviceOs;
	}

	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
