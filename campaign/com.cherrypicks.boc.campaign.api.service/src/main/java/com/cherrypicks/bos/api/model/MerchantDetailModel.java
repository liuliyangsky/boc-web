package com.cherrypicks.bos.api.model;

import java.io.Serializable;

public class MerchantDetailModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5931267220712922270L;

	private String merchantName;
	
	private String merchantDetail;
	
	private String merchantContent;
	
	private String merchantPhone;

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantDetail() {
		return merchantDetail;
	}

	public void setMerchantDetail(String merchantDetail) {
		this.merchantDetail = merchantDetail;
	}

	public String getMerchantContent() {
		return merchantContent;
	}

	public void setMerchantContent(String merchantContent) {
		this.merchantContent = merchantContent;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}
	
}
