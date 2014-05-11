package com.cherrypicks.boc.api.dao;

import com.cherrypicks.bos.api.model.MerchantDetailModel;

public interface MerchantDao {

	MerchantDetailModel getMerchantDetail(String lang, String merchantId);

}
