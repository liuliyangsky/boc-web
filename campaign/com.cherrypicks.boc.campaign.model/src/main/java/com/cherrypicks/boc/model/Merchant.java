package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;
/**
 * 
 * @author more.li
 *
 */
public class Merchant extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8693059799808932280L;
	
	private String merchantPhone;
	
	private String merchantWebpage;

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getMerchantWebpage() {
		return merchantWebpage;
	}

	public void setMerchantWebpage(String merchantWebpage) {
		this.merchantWebpage = merchantWebpage;
	}
	
	
}
