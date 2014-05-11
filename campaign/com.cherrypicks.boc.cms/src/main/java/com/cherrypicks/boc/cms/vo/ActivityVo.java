package com.cherrypicks.boc.cms.vo;

import com.cherrypicks.boc.model.Activity;

public class ActivityVo extends Activity{
	
	private Long [] activityAddressIds;
	
	private String [] activityAddresses;

	public Long[] getActivityAddressIds() {
		return activityAddressIds;
	}

	public void setActivityAddressIds(Long[] activityAddressIds) {
		this.activityAddressIds = activityAddressIds;
	}

	public String[] getActivityAddresses() {
		return activityAddresses;
	}

	public void setActivityAddresses(String[] activityAddresses) {
		this.activityAddresses = activityAddresses;
	}

	
}
