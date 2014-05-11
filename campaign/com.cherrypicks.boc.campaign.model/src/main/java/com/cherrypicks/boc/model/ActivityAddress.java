package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class ActivityAddress extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7851746329417447853L;
	
	private Object activityId;
	
	private Integer numberOfPeople;
	
	private String address;

	public Object getActivityId() {
		return activityId;
	}

	public void setActivityId(Object activityId) {
		this.activityId = activityId;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
