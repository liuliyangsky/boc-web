package com.cherrypicks.boc.api.service;

import java.util.List;

import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

public interface ActivityService {

	List<ActivityModel> getActivityList(String lang);
     
	ActivityDetailModel getAcitvityDetail(String lang,Long activityId); 
	
	List<AddressModel> getAddress(Long activityId);
}
