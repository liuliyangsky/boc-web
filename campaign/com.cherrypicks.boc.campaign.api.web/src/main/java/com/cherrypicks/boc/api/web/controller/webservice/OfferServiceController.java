package com.cherrypicks.boc.api.web.controller.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cherrypicks.boc.api.service.OfferService;
import com.cherrypicks.boc.api.util.ResultUtil;
import com.cherrypicks.boc.api.web.handler.OfferServiceHandler;
import com.cherrypicks.boc.common.util.CodeStatus;
import com.cherrypicks.boc.common.util.EncryptionUtil;
import com.cherrypicks.boc.common.util.I18nUtil;
import com.cherrypicks.boc.common.util.URLParameterUtil;

@Controller
public class OfferServiceController {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private OfferService offerService;
	
	@Autowired
	private OfferServiceHandler offerServiceHandler;

	@RequestMapping(value = "/downLoadOffer.do")
	public String downLoadOffer(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
		/* decrypt P and set map */
//		p = URLDecoder.decode(p, "UTF-8");
		String decryptp = EncryptionUtil.decrypt(p,EncryptionUtil.DECRYPT_KEY);
		URLParameterUtil urlAnalysis = new URLParameterUtil();
		urlAnalysis.analysis(decryptp);
		
		//Base parameter
		String userId = urlAnalysis.getParam("userId");
		String deviceId = urlAnalysis.getParam("deviceId");
		String device = urlAnalysis.getParam("device");
		String deviceVerNum = urlAnalysis.getParam("deviceVerNum");
		String lang = urlAnalysis.getParam("lang");
		String couponId = urlAnalysis.getParam("couponId");
		
		
		return downLoadOffer1(request, model, userId, deviceId, device, deviceVerNum, lang, couponId);
	}
	
	/**
	 * <p>
	 * 下載優惠劵操作user_device_coupon表 插入記錄actionType=1
	 * 1.檢查quota是否足夠 檢查campaign_coupon表的gn_sum<=max_sitribution_quota字段,然後檢查coupon_quota表今天是否有dibution and gn<=quota字段
	 * 2.如果quota條件滿足,檢查campaign_coupon表max_distribution_pre_device
	 *    (1)max_distribution_pre_device=0 不檢查
	 *    (2)max_distribution_pre_device>0 檢查 user_device_coupon表記錄數<=max_distribution_pre_device
	 * 3.條件都滿足.insert表 user_device_coupon表一條記錄,campaign_coupon表的gn_sum+1,然後檢查coupon_quota表gn+1
	 * </p>
	 * @param request
	 * @param model
	 * @param userId
	 * @param deviceId
	 * @param device
	 * @param deviceVerNum
	 * @param lang
	 * @param couponId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downLoadOffer1.do")
     public String downLoadOffer1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang,String couponId) throws Exception {
		
    	log.info("-------downLoadOffer start-------");
    	 
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(couponId, "couponId "+I18nUtil.getMessage(-2, null, lang));
    		
		String jsonStr = null;
		
		Long userCouponId = offerService.downLoadOff(userId, deviceId, new Long(couponId),lang);
	
		Map<String,Long> map=new HashMap<String,Long>();
		map.put("userCouponId", userCouponId);
	    jsonStr =ResultUtil.getResultJson(map);
		model.put("message", jsonStr);

		log.info("-------downLoadOffer end-------");
		return "message.json";
	}

	@RequestMapping(value = "/redeemOffer.do")
	public String redeemOffer(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
		/* decrypt P and set map */
//		p = URLDecoder.decode(p, "UTF-8");
		String decryptp = EncryptionUtil.decrypt(p,EncryptionUtil.DECRYPT_KEY);
		URLParameterUtil urlAnalysis = new URLParameterUtil();
		urlAnalysis.analysis(decryptp);
		
		//Base parameter
		String userId = urlAnalysis.getParam("userId");
		String deviceId = urlAnalysis.getParam("deviceId");
		String device = urlAnalysis.getParam("device");
		String deviceVerNum = urlAnalysis.getParam("deviceVerNum");
		String lang = urlAnalysis.getParam("lang");
		String userCouponId = urlAnalysis.getParam("userCouponId");
		String isKeew = urlAnalysis.getParam("isKeew");
		String couponCode=urlAnalysis.getParam("couponCode");
		
		return redeemOffer1(request, model, userId, deviceId, device, deviceVerNum, lang, userCouponId,isKeew,couponCode);
	}
	
	/**
	 * <p>兌換優惠劵操作user_device_coupon表 update記錄actionType=2
	 *  1.檢查isKeew=0 or 1,0調用本地redeem ,1調用遠程Keew redeem api
	 *  2.根據userCouponId檢查是否用戶擁有這個優惠劵
	 *  3.根據couponId檢查Campaign_Coupon表couponCode and couponType and status
	 *    (1)檢查quota是否足夠 檢查campaign_coupon表的rn_sum<=max_redeem_quota字段
	 * 	  (2)如果quota條件滿足,檢查campaign_coupon表max_redeem_pre_device
	 *    (3)max_redeem_pre_device=0 不檢查
	 *    (4)max_redeem_pre_device>0 檢查 user_device_coupon表actionType=2的記錄數<=max_redeem_pre_device
	 *  4.條件都滿足.根據userCouponId update表 user_device_coupon表記錄,campaign_coupon表的rn_sum+1,然後檢查coupon_quota表rn+1
	 * </p>
	 * @param request
	 * @param model
	 * @param userId
	 * @param deviceId
	 * @param device
	 * @param deviceVerNum
	 * @param lang
	 * @param userCouponId
	 * @param isKeew
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/redeemOffer1.do")
     public String redeemOffer1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang,String userCouponId,String isKeew,String couponCode) throws Exception {
		
    	log.info("-------redeemOffer start-------");
    	 
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userCouponId, "userCouponId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(isKeew, "isKeew "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(couponCode, "couponCode "+I18nUtil.getMessage(-2, null, lang));
			
		String jsonStr = null;
		
	    jsonStr =ResultUtil.getResultJson(offerServiceHandler.redeemOffer(userId, deviceId, device, deviceVerNum, lang, userCouponId, isKeew,couponCode)?CodeStatus.SUCCESS_STATUS:CodeStatus.FAILED_STATUS);
		model.put("message", jsonStr);

		log.info("-------redeemOffer end-------");
		return "message.json";
	}
	

}
