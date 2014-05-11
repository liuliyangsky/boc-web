package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;


public class SystemRole extends AbstractEntity {

	private static final long serialVersionUID = 3504787302147725963L;

	private String roleName;

	private String roleDesc;

	public SystemRole() {
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}
