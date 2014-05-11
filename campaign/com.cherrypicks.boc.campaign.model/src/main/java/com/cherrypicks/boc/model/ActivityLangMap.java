package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class ActivityLangMap extends AbstractEntity {

	/**
	 *  
	 */
	private static final long serialVersionUID = 5949203906692565616L;

	private Object activityId;
	
	private String activityName;
	
	private String activityTitle;
	
	private String activityDesc;
	
	private Integer actionType;
	
	private String tAndC;
	
	private String langCode;

	public Object getActivityId() {
		return activityId;
	}

	public void setActivityId(Object activityId) {
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

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String gettAndC() {
		return tAndC;
	}

	public void settAndC(String tAndC) {
		this.tAndC = tAndC;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
}

