package com.cherrypicks.boc.api.dao;

import java.util.List;

import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

public interface ActivityDao {

	List<ActivityModel> getActivityList(String lang);
	
	
    ActivityDetailModel getActivityDetail(String lang,Long activityId);
    
    List<AddressModel> getAddress(Long activityId);
}
