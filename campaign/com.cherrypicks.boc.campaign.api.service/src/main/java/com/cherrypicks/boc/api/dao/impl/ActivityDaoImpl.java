package com.cherrypicks.boc.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.ActivityDao;
import com.cherrypicks.boc.api.dao.db2.ActivityDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

@Repository
public class ActivityDaoImpl extends Context implements ActivityDao{

	@Autowired
	private ActivityDaoDB2 activityDaoDB2;
	
	@Override
	public List<ActivityModel> getActivityList(String lang) {
		return activityDaoDB2.getActivityList(lang);
	}

	@Override
	public ActivityDetailModel getActivityDetail(String lang, Long activityId) {
		return activityDaoDB2.getActivityDetail(lang, activityId);
	}

	@Override
	public List<AddressModel> getAddress(Long activityId) {
		return activityDaoDB2.getAddress(activityId);
	}

}
