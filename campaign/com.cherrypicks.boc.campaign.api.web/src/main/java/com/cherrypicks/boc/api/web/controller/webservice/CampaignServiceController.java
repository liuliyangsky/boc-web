package com.cherrypicks.boc.api.web.controller.webservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cherrypicks.boc.api.service.CampaignService;
import com.cherrypicks.boc.api.util.ResultUtil;
import com.cherrypicks.boc.common.util.EncryptionUtil;
import com.cherrypicks.boc.common.util.I18nUtil;
import com.cherrypicks.boc.common.util.URLParameterUtil;
import com.cherrypicks.bos.api.model.CampaignCouponModel;
import com.cherrypicks.bos.api.model.CampaignModel;
import com.cherrypicks.bos.api.model.CouponDetailModel;
import com.cherrypicks.bos.api.model.MerchantDetailModel;

@Controller
public class CampaignServiceController {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private CampaignService campaignService;

	@RequestMapping(value = "/getAllCampaign.do")
	public String getAllCampaign(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		
		return getAllCampaign1(request, model, userId, deviceId, device, deviceVerNum, lang);
	}
	
	@RequestMapping(value = "/getAllCampaign1.do")
     public String getAllCampaign1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang) throws Exception {
		
    	 log.info("-------getAllCampaign start-------");
    	 log.info("getAllCampaign1.do?"+"userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
    	 
    		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
    		
		 String jsonStr = null;
		
	   List<CampaignModel> capaignList = campaignService.getAllCampaign(lang);
	
	    jsonStr =ResultUtil.getResultJson(capaignList);
		model.put("message", jsonStr);

		log.info("-------getAllCampaign end-------");
		return "message.json";
	}
	
	@RequestMapping(value = "/getCouponList.do")
	public String getCouponList(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		String campaignId= urlAnalysis.getParam("campaignId");
		return getCouponList1(request, model, userId, deviceId, device, deviceVerNum, lang, campaignId);
	}
	
	@RequestMapping(value = "/getCouponList1.do")
	public String getCouponList1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang,String campaignId) throws Exception {
		
		log.info("-------getCouponList start-------");
		
		log.info("getCouponList1.do?"+"&campaignId="+campaignId+"userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
		
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(campaignId, "campaignId "+I18nUtil.getMessage(-2, null, lang));
		
		String jsonStr = null;
		
		List<CampaignCouponModel> campaignCouponModel = campaignService.getCouponList(lang,campaignId);
		
		jsonStr = ResultUtil.getResultJson(campaignCouponModel);
		
		model.put("message", jsonStr);

		log.info("-------getCouponList end-------");
		return "message.json";
	}
	
	@RequestMapping(value = "/getCouponDetail.do")
	public String getCouponDetail(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		Integer isKeew = Integer.parseInt(urlAnalysis.getParam("isKeew"));
		String couponId = urlAnalysis.getParam("couponId");
		return getCouponDetail1(request, model, userId, deviceId, device, deviceVerNum, lang, isKeew, couponId);
	}
	
	@RequestMapping(value = "/getCouponDetail1.do")
	public String getCouponDetail1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang,Integer isKeew,String couponId) throws Exception {
		
		log.info("-------getCouponDetail start-------");
		
		log.info("getCouponDetail1.do?"+"&couponId="+couponId+"&isKeew="+isKeew+"userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
		
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(couponId, "couponId "+I18nUtil.getMessage(-2, null, lang));
		Assert.notNull(isKeew, "isKeew"  + I18nUtil.getMessage(-2, null, lang));
		Assert.isTrue(isKeew == CouponDetailModel.IS_KEWEEW || isKeew == CouponDetailModel.NO_KEWEEW, "isKeew"  + I18nUtil.getMessage(-2, null, lang));
		
		String jsonStr = null;
		
	  CouponDetailModel couponDetailModel = campaignService.getCouponDetail(lang,couponId);
	  couponDetailModel.setIsKeew(isKeew);
	  
		jsonStr = ResultUtil.getResultJson(couponDetailModel);
		
		model.put("message", jsonStr);

		log.info("-------getCouponDetail end-------");
		return "message.json";
	}
	
	@RequestMapping(value = "/getMerchantDetail.do")
	public String getMerchantDetail(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		String merchantId = urlAnalysis.getParam("merchantId");
		return getMerchantDetail1(request, model, userId, deviceId, device, deviceVerNum, lang, merchantId);
	}
	
	@RequestMapping(value = "/getMerchantDetail1.do")
	public String getMerchantDetail1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang,String merchantId) throws Exception {
		
		log.info("-------getMerchantDetail start-------");
		
		log.info("getCouponDetail1.do?"+"&merchantId="+merchantId+"userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
		
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(merchantId, "merchantId "+I18nUtil.getMessage(-2, null, lang));
		
		String jsonStr = null;
		
	  MerchantDetailModel merchantDetailModel = campaignService.getMerchantDetail(lang,merchantId);
	  
		jsonStr = ResultUtil.getResultJson(merchantDetailModel);
		
		model.put("message", jsonStr);

		log.info("-------getMerchantDetail end-------");
		return "message.json";
	}
	

	
}
