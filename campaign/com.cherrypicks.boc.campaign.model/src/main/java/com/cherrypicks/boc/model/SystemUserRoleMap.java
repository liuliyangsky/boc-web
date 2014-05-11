package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;


public class SystemUserRoleMap extends AbstractEntity {
	
	private static final long serialVersionUID = -5508929520489611894L;
	
	private Object userId;

	private Object roleId;

	public Object getUserId() {
		return userId;
	}

	public void setUserId(Object userId) {
		this.userId = userId;
	}

	public Object getRoleId() {
		return roleId;
	}

	public void setRoleId(Object roleId) {
		this.roleId = roleId;
	}

   
	
	
	

	
	
	
}
