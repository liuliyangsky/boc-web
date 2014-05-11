package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;

public class SystemUser extends AbstractEntity {

	private static final long serialVersionUID = -6750254896212063692L;
	public static final String USER_STATUS_ACTIVE = "A";
	public static final String USER_STATUS_DEACTIVE = "D";

	private String userName;

	private String password;
	
	private String mobile;
	
	private String email;

	private Integer status;


	public SystemUser() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
