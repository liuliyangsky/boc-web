package com.cherrypicks.boc.cms.vo;

import com.cherrypicks.boc.model.SystemUser;


public class SystemUserVo extends SystemUser {

	private static final long serialVersionUID = -7292864684082286299L;

	private Long[] roles;
	
	private String[] roleNames;
	
	public Long[] getRoles() {
		return roles;
	}

	public void setRoles(Long[] roles) {
		this.roles = roles;
	}

	public String[] getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}
}
