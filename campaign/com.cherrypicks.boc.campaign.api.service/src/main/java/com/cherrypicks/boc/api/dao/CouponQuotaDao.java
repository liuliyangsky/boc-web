package com.cherrypicks.boc.api.dao;

import java.sql.SQLException;

import com.cherrypicks.boc.model.CouponQuota;

public interface CouponQuotaDao {
	public CouponQuota getByCouponIdAndDate(Long couponId,String startDate)throws SQLException;
	/**
	 * update GN+1
	 */
	public boolean updateCouponGn(Long id);
	/**
	 * update RN+1
	 */
	public boolean updateCouponRn(Long id);
}
