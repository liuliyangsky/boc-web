package com.cherrypicks.boc.model;

import java.util.Date;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class Activity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6585880062050525990L;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer status;
	
	private Integer activityType;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

}
