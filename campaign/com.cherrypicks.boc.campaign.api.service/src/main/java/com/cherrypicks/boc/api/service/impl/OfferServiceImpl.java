package com.cherrypicks.boc.api.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cherrypicks.boc.api.dao.CampaignCouponDao;
import com.cherrypicks.boc.api.dao.CouponQuotaDao;
import com.cherrypicks.boc.api.dao.UserDeviceCouponDao;
import com.cherrypicks.boc.api.service.OfferService;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.common.util.Constant;
import com.cherrypicks.boc.common.util.DateUtil;
import com.cherrypicks.boc.model.CampaignCoupon;
import com.cherrypicks.boc.model.CouponQuota;
import com.cherrypicks.boc.model.UserDeviceCoupon;

/**
 * 
 * @author kelvin.tie
 */
@Service
public class OfferServiceImpl extends Context implements OfferService {

	@Autowired
	private CampaignCouponDao campaignCouponDao;
	@Autowired
	private CouponQuotaDao couponQuotaDao;
	@Autowired
	private UserDeviceCouponDao userDeviceCouponDao;

	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Long downLoadOff(String userId, String deviceId, Long couponId,String lang) throws Exception {
		
		//檢查quota是否足夠 檢查campaign_coupon表的gn_sum<=max_sitribution_quota字段
		CampaignCoupon ca=campaignCouponDao.getById(couponId);
		if(ca==null){
			throw new RuntimeException("CampaignCoupon 不存在 CampaignCouponId["+couponId+"]");
		}
		if(CampaignCoupon.ACTIVE!=ca.getStatus()){
			throw new RuntimeException("CampaignCoupon status["+ca.getStatus()+"] is not Active");
		}
		if(ca.getMaxDistributionQuota()==null 
				|| ca.getGnSum()>=ca.getMaxDistributionQuota()){
			throw new RuntimeException("CampaignCoupon GN_SUM["+ca.getGnSum()+"] >= MaxDistributionQuota["+ca.getMaxDistributionQuota()+"]");
		}
		
		//然後檢查coupon_quota表今天是否有dibution and gn<=quota字段
		String startDate=DateUtil.getCurrentDateStr(DateUtil.DATE_PATTERN_DEFAULT);
		CouponQuota cq=couponQuotaDao.getByCouponIdAndDate(couponId,startDate);
		if(cq==null){
			throw new RuntimeException("CouponQuota couponId["+couponId+"]這個時間["+startDate+"]沒有發放");
		}
		if(cq.getQuota()==null
				||cq.getGn()>=cq.getQuota()){
			throw new RuntimeException("CouponQuota GN >= MaxDistributionQuota");
		}
		
		//如果quota條件滿足max_distribution_pre_device>0 ,檢查campaign_coupon表max_distribution_pre_device
		//檢查 user_device_coupon表記錄數<=max_distribution_pre_device
		if(ca.getMaxDistributionPreDevice()!=null && ca.getMaxDistributionPreDevice()>0){
			int userCount=userDeviceCouponDao.getByCouponAndUser(couponId, userId,null);
			if(userCount>=ca.getMaxDistributionPreDevice()){
				throw new RuntimeException("這個用戶已經拿到userCount["+userCount+"]這個coupon定義只能獲得拿到["+ca.getMaxDistributionPreDevice()+"]個電子優惠");
			}
		}
		//插入一條記錄
		Long id=insertUserDeviceCoupon(userId, deviceId, couponId);
		//update GN_SUM+1
		campaignCouponDao.updateCouponGnSum((Long)ca.getId());
		//update GN+1
		couponQuotaDao.updateCouponGn((Long)cq.getId());
		
		return id;
	}
	
	@Override
	public boolean redeemOffer(String userId, String deviceId, String device,
			String deviceVerNum, String lang, Long userCouponId,String couponCode)
			throws SQLException, Exception {
        //根據userCouponId檢查用戶是否有優惠劵
		UserDeviceCoupon udc=userDeviceCouponDao.getById(userCouponId);
		if(udc==null){
			throw new RuntimeException("你的優惠劵不存在userCouponId["+userCouponId+"]");
		}
		//檢查campaignCoupon status ,couponCode and redeemQuota 
		Long campaignCouponId=new Long(udc.getCouponId()+"");
		CampaignCoupon ca=campaignCouponDao.getById(campaignCouponId);
		if(ca==null){
			throw new RuntimeException("CampaignCoupon 不存在 CampaignCouponId["+campaignCouponId+"]");
		}
		if(CampaignCoupon.ACTIVE!=ca.getStatus()){
			throw new RuntimeException("CampaignCoupon status["+ca.getStatus()+"] is not Active");
		}
		if(CampaignCoupon.couponType_0!=ca.getCouponType()
				&&!(couponCode.equals(ca.getCouponCode()))){
			throw new RuntimeException("輸入的couponCode["+couponCode+"]不正確,正確的是["+ca.getCouponCode()+"](供調試模式查看)");
		}
		if(ca.getMaxRedeemQuota()==null 
				|| ca.getRnSum()>=ca.getMaxRedeemQuota()){
			throw new RuntimeException("CampaignCoupon RN_SUM["+ca.getRnSum()+"] >= MaxRedeemQuota["+ca.getMaxRedeemQuota()+"]");
		}
	
		//如果redeemQuota條件滿足max_redeem_pre_device>0 ,檢查campaign_coupon表max_redeem_pre_device
		//檢查 user_device_coupon表記錄數<=max_redeem_pre_device
		if(ca.getMaxRedeemPreDevice()!=null && ca.getMaxRedeemPreDevice()>0){
			int userRnCount=userDeviceCouponDao.getByCouponAndUser(campaignCouponId, userId,UserDeviceCoupon.ACTION_REDEEM);
			if(userRnCount>=ca.getMaxRedeemPreDevice()){
				throw new RuntimeException("每個用戶已經拿到userRnCount["+userRnCount+"]這個coupon定義只能兌換["+ca.getMaxRedeemPreDevice()+"]個電子優惠");
			}
		}
		//update actionType=2
		udc.setActionType(UserDeviceCoupon.ACTION_REDEEM);
		userDeviceCouponDao.update(udc);
		//update RN_SUM+1
		campaignCouponDao.updateCouponRnSum(campaignCouponId);
		//update RN+1
		couponQuotaDao.updateCouponRn((Long)udc.getCouponQuotaId());
		
		return true;
	}
	

	private Long insertUserDeviceCoupon(String userId, String deviceId, Long couponId) throws SQLException{
		UserDeviceCoupon udc=new UserDeviceCoupon();
		udc.setCouponId(couponId);
		udc.setUserId(userId);
		udc.setDeviceId(deviceId);
		udc.setCreatedBy(Constant.CREATE_BY_API);
		udc.setActionType(UserDeviceCoupon.ACTION_GET);
		udc.setCreatedTime(DateUtil.getCurrentDate());
		udc=userDeviceCouponDao.add(udc);
		return (Long)udc.getId();
	}


}
