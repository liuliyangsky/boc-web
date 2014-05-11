package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class CampaignCouponLangMap extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4103083845318569440L;
	
	private Object couponId;
	
	private String couponName;
	
	private String couponTitle;
	
	private String couponContent;
	
	private String coupondetail;
	
	private String icon;
	
	private String banner;
	
	private String tAndC;
	
	private String langCode;

	public Object getCouponId() {
		return couponId;
	}

	public void setCouponId(Object couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponTitle() {
		return couponTitle;
	}

	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}

	public String getCouponContent() {
		return couponContent;
	}

	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}

	public String getCoupondetail() {
		return coupondetail;
	}

	public void setCoupondetail(String coupondetail) {
		this.coupondetail = coupondetail;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
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
