package com.cherrypicks.bos.api.model;

import java.io.Serializable;
import java.util.List;

public class ActivityDetailModel implements Serializable{

	/**
	 * @author Alan.kuang
	 */
	private static final long serialVersionUID = 3905163678620424486L;
	
	private Long activityId;
	
	private String activityName;
	
	private String activityDesc;
	
	private String activityTitle;
	
	private String activityIcon;
	
	private List<AddressModel> address; 
	
	private String tc;
	
	private String startDate;
	
	private String endDate;
	
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityIcon() {
		return activityIcon;
	}

	public void setActivityIcon(String activityIcon) {
		this.activityIcon = activityIcon;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AddressModel> getAddress() {
		return address;
	}

	public void setAddress(List<AddressModel> address) {
		this.address = address;
	}




	
	

}
