package com.cherrypicks.boc.api.dao.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.CouponQuotaDao;
import com.cherrypicks.boc.api.dao.db2.CouponQuotaDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.model.CouponQuota;

@Repository
public class CouponQuotaDaoImpl  extends Context implements CouponQuotaDao{

	@Autowired
	private CouponQuotaDaoDB2 couponQuotaDaoDB2;

	@Override
	public CouponQuota getByCouponIdAndDate(Long couponId,String startDate) throws SQLException {

		return couponQuotaDaoDB2.getByCouponIdAndDate(couponId, startDate);
	}

	@Override
	public boolean updateCouponGn(Long id) {
		return couponQuotaDaoDB2.updateCouponGn(id);
	}

	@Override
	public boolean updateCouponRn(Long id) {
		return couponQuotaDaoDB2.updateCouponRn(id);
	}
	
	
}
