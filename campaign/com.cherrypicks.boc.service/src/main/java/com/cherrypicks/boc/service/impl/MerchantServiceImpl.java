package com.cherrypicks.boc.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.IMerchantDao;
import com.cherrypicks.boc.model.Merchant;
import com.cherrypicks.boc.service.IMerchantService;

public class MerchantServiceImpl extends AbstractBaseService<Merchant> implements IMerchantService{
	
	@Autowired
	private IMerchantDao merchantDao;
	
	@Resource(name="merchantDao")
	private void setbaseDao(IBaseDao<Merchant> baseDao){
		this.baseDao = baseDao;
	}

	/**
	 * update Merchant
	 * @param merchant
	 * @return
	 */
	public Merchant updateMerchant(Merchant merchant) {
		if(merchant==null){
			throw new IllegalArgumentException();
		}
		Merchant merchantModel = findById(merchant.getId());
		if(null != merchantModel){
			merchantModel.setUpdatedTime(new Date());
			merchantModel.setMerchantPhone(merchant.getMerchantPhone()==null ? merchantModel.getMerchantPhone() : merchant.getMerchantPhone());
			merchantModel.setMerchantWebpage(merchant.getMerchantWebpage()==null ? merchantModel.getMerchantWebpage() : merchant.getMerchantWebpage());
			update(merchantModel);
		}
		return merchantModel;
	}
}
