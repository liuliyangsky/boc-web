package com.cherrypicks.boc.service.impl;

import javax.annotation.Resource;

import com.cherrypicks.boc.dao.IActivityDao;
import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.model.Activity;
import com.cherrypicks.boc.service.IActivityService;

public class ActivityServiceImpl extends AbstractBaseService<Activity> implements IActivityService{
	
	
	private IActivityDao activityDao;
	
	@Resource(name="activityDao")
	public void setBaseDao(IBaseDao<Activity> baseDao) {
		this.baseDao = baseDao;
	}

}
