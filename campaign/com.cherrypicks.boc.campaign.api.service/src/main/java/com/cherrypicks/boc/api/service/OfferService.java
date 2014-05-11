package com.cherrypicks.boc.api.service;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Transactional;



/**
 * 
 * @author kelvin.tie
 */
@Transactional
public interface OfferService {
	
	public Long downLoadOff(String userId,String deviceId,Long couponId,String lang) throws SQLException, Exception;
	
	public boolean redeemOffer(String userId,String deviceId,String device,String deviceVerNum,String lang,Long userCouponId,String couponCode) throws SQLException, Exception;
}
