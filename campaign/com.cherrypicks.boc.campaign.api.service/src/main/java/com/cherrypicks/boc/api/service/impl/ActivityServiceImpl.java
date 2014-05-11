package com.cherrypicks.boc.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.ActivityDao;
import com.cherrypicks.boc.api.service.ActivityService;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

@Repository
public class ActivityServiceImpl extends Context implements ActivityService {
	
	@Autowired
	private ActivityDao activityDao;

	@Override
	public List<ActivityModel> getActivityList(String lang) {
		return activityDao.getActivityList(lang);
	}

	@Override
	public ActivityDetailModel getAcitvityDetail(String lang, Long activityId) {
		return activityDao.getActivityDetail(lang, activityId);
	}

	@Override
	public List<AddressModel> getAddress(Long activityId) {
		return activityDao.getAddress(activityId);
	}

}
