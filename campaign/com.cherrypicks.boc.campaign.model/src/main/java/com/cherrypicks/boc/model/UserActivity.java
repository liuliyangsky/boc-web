package com.cherrypicks.boc.model;

import java.util.Date;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class UserActivity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2602329849139059484L;
	
	private String userId;
	
	private String activityId;
	
	private Object activityAddressId;
	
	private Date participationTime;
	
	private Integer numOfPeople;
	
	private String userName;
	
	private Integer userSex;
	
	private String userPhone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Object getActivityAddressId() {
		return activityAddressId;
	}

	public void setActivityAddressId(Object activityAddressId) {
		this.activityAddressId = activityAddressId;
	}

	public Date getParticipationTime() {
		return participationTime;
	}

	public void setParticipationTime(Date participationTime) {
		this.participationTime = participationTime;
	}

	public Integer getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(Integer numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
}
