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

import com.cherrypicks.boc.api.service.ActivityService;
import com.cherrypicks.boc.api.util.ResultUtil;
import com.cherrypicks.boc.common.util.EncryptionUtil;
import com.cherrypicks.boc.common.util.I18nUtil;
import com.cherrypicks.boc.common.util.URLParameterUtil;
import com.cherrypicks.bos.api.model.ActivityDetailModel;
import com.cherrypicks.bos.api.model.ActivityModel;
import com.cherrypicks.bos.api.model.AddressModel;

@Controller
public class ActivitySeviceController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ActivityService acitivityService;
	
	@RequestMapping(value = "/getActivityList.do")
	public String getActivityList(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
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
		
		return getActivityList1(request, model, userId, deviceId, device, deviceVerNum, lang);
	}
	
	@RequestMapping(value = "/getActivityList1.do")
     public String getActivityList1(HttpServletRequest request, ModelMap model,String userId,String deviceId,String device,String deviceVerNum,String lang) throws Exception {
		
    	 log.info("-------getActivityList start-------");
    	 log.info("getActivityList1.do?"+"userId="+userId+"&deviceId="+deviceId+"&device="+device+"&deviceVerNum="+deviceVerNum+"&lang="+lang);
    	 
    		Assert.hasText(lang, "lang "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(device, "device "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(deviceVerNum, "deviceVerNum "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(userId, "userId "+I18nUtil.getMessage(-2, null, lang));
    		Assert.hasText(deviceId, "deviceId "+I18nUtil.getMessage(-2, null, lang));
    		
		 String jsonStr = null;
		
	   List<ActivityModel> activityList = acitivityService.getActivityList(lang);
	    
	    jsonStr =ResultUtil.getResultJson(activityList);
		model.put("message", jsonStr);

		log.info("-------getActivityList end-------");
		return "message.json";
	}
	@RequestMapping(value ="/getActivityDetail.do")
	 public String getActivityDetail(HttpServletRequest request,ModelMap model ,String p) throws Exception{
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
		String activityId=urlAnalysis.getParam("activityId");
		
		 return getActivityDetail1(request,model,userId,device,deviceId,deviceVerNum,activityId,lang);
	 }
    
	@RequestMapping(value ="/getActivityDetail1.do")
	public String getActivityDetail1(HttpServletRequest request,ModelMap model, String userId, String device, String deviceId,String deviceVerNum, String activityId, String lang) throws Exception {
		
		log.info("-------getActivityDetail start-------");
		
		Assert.hasText(lang, "lang"+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(device, "device"+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceId, "deviceId"+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(deviceVerNum, "deviceVerNum"+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(activityId, "activityId"+I18nUtil.getMessage(-2, null, lang));
		Assert.hasText(userId, "userId"+I18nUtil.getMessage(-2, null, lang));
		
		String jsonstr=null;
		List<AddressModel> addressmodel=acitivityService.getAddress(new Long(activityId)); 
		ActivityDetailModel activitydetail=acitivityService.getAcitvityDetail(lang, new Long(activityId));
		activitydetail.setAddress(addressmodel);
		
		jsonstr= ResultUtil.getResultJson(activitydetail);
		
		model.put("message", jsonstr);
		log.info("-------getActivityDetail end-------");
		return "message.json";
	}
}
