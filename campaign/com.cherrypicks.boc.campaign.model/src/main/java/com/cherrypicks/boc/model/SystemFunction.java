package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;


public class SystemFunction extends AbstractEntity {
	
	private static final long serialVersionUID = 8970487667238335303L;
	
	private String funcName;

	private String funcCode;

	private String funcDesc;

	public SystemFunction() {
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncCode() {
		return funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

}
