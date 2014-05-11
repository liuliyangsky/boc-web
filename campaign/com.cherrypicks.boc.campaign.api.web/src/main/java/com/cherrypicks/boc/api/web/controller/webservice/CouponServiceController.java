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

import com.cherrypicks.boc.api.service.CouponService;
import com.cherrypicks.boc.api.util.ResultUtil;
import com.cherrypicks.boc.common.util.EncryptionUtil;
import com.cherrypicks.boc.common.util.I18nUtil;
import com.cherrypicks.boc.common.util.URLParameterUtil;
import com.cherrypicks.bos.api.model.CouponListModel;

@Controller
public class CouponServiceController {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private CouponService conponlist;
	


	@RequestMapping(value = "/getMyCouponList.do")
	public String getMyCouponList(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		
		
		return getMyCouponList1(request, model, userId, deviceId, device, deviceVerNum, lang);
	}
	

	@RequestMapping(value = "/getMyCouponList1.do")
     public String getMyCouponList1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang) throws Exception {
		
    	log.info("-------getMyCouponList start-------");
    	
    	log.info("getMyCouponList1.do?userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
    	
		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
	  
        String jsonStr=null;
		
		List<CouponListModel> couponListModel =conponlist.getCouponList(userId,lang); 
	
		jsonStr=ResultUtil.getResultJson(couponListModel);
		
		
		model.put("message", jsonStr);
    		
		log.info("-------getMyCouponList end-------");
		return "message.json";
	}


	

}
