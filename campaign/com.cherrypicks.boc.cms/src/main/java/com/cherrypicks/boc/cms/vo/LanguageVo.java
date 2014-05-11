package com.cherrypicks.boc.cms.vo;

public class LanguageVo extends AbstractEntity {

	private static final long serialVersionUID = 8660547235152505758L;
	private String img;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
