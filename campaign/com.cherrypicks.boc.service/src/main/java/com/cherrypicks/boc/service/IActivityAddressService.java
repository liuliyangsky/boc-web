package com.cherrypicks.boc.service;

import java.util.List;

import com.cherrypicks.boc.model.Activity;
import com.cherrypicks.boc.model.ActivityAddress;

public interface IActivityAddressService extends IBaseService<ActivityAddress>{
	
	/**
	 * find Activity Address List By Activity
	 * @param activity
	 * @return
	 */
	public List<ActivityAddress> findActivityAddressListByActivity(Activity activity);

}
