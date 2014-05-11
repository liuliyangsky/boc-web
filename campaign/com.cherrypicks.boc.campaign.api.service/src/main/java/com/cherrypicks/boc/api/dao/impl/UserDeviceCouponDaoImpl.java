package com.cherrypicks.boc.api.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.UserDeviceCouponDao;
import com.cherrypicks.boc.api.dao.db2.UserDeviceCouponDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.UserDeviceCoupon;

@Repository
public class UserDeviceCouponDaoImpl  extends Context implements UserDeviceCouponDao{

	@Autowired
	private UserDeviceCouponDaoDB2 userDeviceCouponDaoDB2;
	
	@Override
	public UserDeviceCoupon add(UserDeviceCoupon udc) throws SQLException {
		
		return userDeviceCouponDaoDB2.add(udc, DBSchemaEnum.BOC_CAMPAIGN.toString());
	}

	@Override
	public Integer getByCouponAndUser(Long couponId, String userId,Integer actionType)
			throws SQLException {
		return userDeviceCouponDaoDB2.getByCouponAndUser(couponId, userId,actionType);
	}

	@Override
	public UserDeviceCoupon getById(Long id) throws SQLException {

		return userDeviceCouponDaoDB2.get(id, DBSchemaEnum.BOC_CAMPAIGN.toString());
	}

	@Override
	public Boolean update(UserDeviceCoupon udc) throws SQLException {

		return userDeviceCouponDaoDB2.update(udc, DBSchemaEnum.BOC_CAMPAIGN.toString());
	}
	
	
}
