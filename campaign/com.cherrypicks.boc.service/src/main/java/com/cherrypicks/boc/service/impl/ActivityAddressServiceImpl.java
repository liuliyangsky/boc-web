package com.cherrypicks.boc.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IActivityAddressDao;
import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.Activity;
import com.cherrypicks.boc.model.ActivityAddress;
import com.cherrypicks.boc.service.IActivityAddressService;

public class ActivityAddressServiceImpl extends AbstractBaseService<ActivityAddress> implements IActivityAddressService {

	@Autowired
	private IActivityAddressDao activityAddressDao;
	
	@Resource(name="activityAddressDao")
	public void setBaseDao(IBaseDao<ActivityAddress> baseDao) {
		this.baseDao = baseDao;
	}

	public List<ActivityAddress> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {

		return activityAddressDao.findAll();
	}

	public void delete(ActivityAddress object) {
		if (null == object) {
			throw new IllegalArgumentException();
		}
		try {
			activityAddressDao.del(object.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ActivityAddress add(ActivityAddress object) {
		object.setCreatedTime(new Date());
		return activityAddressDao.add(object,
				DBSchemaEnum.BOC_CAMPAIGN.toString());
	}

	/**
	 * find Activity Address List By Activity
	 * @param Activity
	 * @return  List<ActivityAddress>
	 */
	public List<ActivityAddress> findActivityAddressListByActivity(Activity activity) {
		return activityAddressDao.findAll();
	}

}
