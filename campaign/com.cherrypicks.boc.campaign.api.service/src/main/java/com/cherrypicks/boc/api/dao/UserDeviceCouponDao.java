package com.cherrypicks.boc.api.dao;

import java.sql.SQLException;

import com.cherrypicks.boc.model.UserDeviceCoupon;

public interface UserDeviceCouponDao {
	public UserDeviceCoupon add(UserDeviceCoupon udc)throws SQLException;
	
	public Integer getByCouponAndUser(Long couponId,String userId,Integer actionType) throws SQLException;
	
	public UserDeviceCoupon getById(Long id)throws SQLException;
	
	public Boolean update(UserDeviceCoupon udc) throws SQLException;
}
