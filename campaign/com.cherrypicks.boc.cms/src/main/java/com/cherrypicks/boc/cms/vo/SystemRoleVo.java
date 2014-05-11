package com.cherrypicks.boc.cms.vo;

import com.cherrypicks.boc.model.SystemRole;

public class SystemRoleVo extends SystemRole {

	private static final long serialVersionUID = -1776682468197795759L;

	private Long[] functions;

	public Long[] getFunctions() {
		return functions;
	}

	public void setFunctions(Long[] functions) {
		this.functions = functions;
	}


	
}
