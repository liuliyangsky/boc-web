package com.cherrypicks.bos.api.model;

import java.io.Serializable;

public class ActivityModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8826469354442227866L;
	
	private Long activityId;
	
	private String activityName;
	
	private String activityTitle;
	
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

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
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
