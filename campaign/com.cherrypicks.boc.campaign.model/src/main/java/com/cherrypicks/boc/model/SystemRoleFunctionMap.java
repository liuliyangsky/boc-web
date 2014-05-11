package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;


public class SystemRoleFunctionMap extends AbstractEntity {

	private static final long serialVersionUID = 5044529156360088242L;
	
	private Object roleId;
	
	private Object funcId;

	public Object getRoleId() {
		return roleId;
	}

	public void setRoleId(Object roleId) {
		this.roleId = roleId;
	}

	public Object getFuncId() {
		return funcId;
	}

	public void setFuncId(Object funcId) {
		this.funcId = funcId;
	}

	
	
	
    
	
	

}
