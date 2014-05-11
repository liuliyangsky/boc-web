package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class MerchantLangMap extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825043254718133427L;
	
	private Object merchantId;
	
	private String merchantName;
	
	private String merchantContent;
	
	private String merchantDetail;
	
	private String icon;
	
	private String banner;
	
	private String langCode;

	public Object getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Object merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantContent() {
		return merchantContent;
	}

	public void setMerchantContent(String merchantContent) {
		this.merchantContent = merchantContent;
	}

	public String getMerchantDetail() {
		return merchantDetail;
	}

	public void setMerchantDetail(String merchantDetail) {
		this.merchantDetail = merchantDetail;
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

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	
}
