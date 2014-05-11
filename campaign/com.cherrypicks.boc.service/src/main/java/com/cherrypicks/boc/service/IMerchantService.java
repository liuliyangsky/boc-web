package com.cherrypicks.boc.service;

import com.cherrypicks.boc.model.Merchant;

public interface IMerchantService extends IBaseService<Merchant>{
	/**
	 * update Merchant
	 * @param merchant
	 * @return
	 */
	public Merchant updateMerchant(Merchant merchant);

}
