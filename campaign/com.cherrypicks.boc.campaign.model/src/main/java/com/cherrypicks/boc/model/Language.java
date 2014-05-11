package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;

/**
 * 
 * @author more.li
 *
 */
public class Language extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6649004565780852317L;
	
	private String name;
	
	private String code;

	private Integer isDefault;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
