package com.cherrypicks.boc.api.web.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherrypicks.boc.api.service.OfferService;
import com.cherrypicks.boc.api.util.HttpClientUtils;
import com.cherrypicks.boc.common.exception.BOCAPIException;
import com.cherrypicks.boc.common.util.CodeStatus;
import com.cherrypicks.boc.common.util.Json;

@Service
public class OfferServiceHandler {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OfferService offerService;
	
	public boolean redeemOffer(String userId,String deviceId,String device,String deviceVerNum,String lang,String userCouponId,String isKeew,String couponCode) throws SQLException, Exception{
		//isKeew=0 is local coupon
		if("0".equals(isKeew)){
			return offerService.redeemOffer(userId, deviceId, device, deviceVerNum, lang, new Long(userCouponId), couponCode);
		}else{
			//call redeem keewApi
			String parma="";
			String result=null;
			Boolean success=false;

			log.info("p=" + parma);
			Map<String, String> map = new HashMap<String, String>();
			//map.put("p", EncryptionUtil.encrypt(parma, EncryptionUtil.DECRYPT_KEY));
			map.put("p",  parma);
			//**call Keew API redeem.do **//*
			result = HttpClientUtils.postMethodRequest("http://keew" + "/redeem.do", map);
	
			// API return
			@SuppressWarnings("unchecked")
			Map<String, Object> jsonMap = Json.toObject(result, Map.class);
			Integer errorCode = (Integer) jsonMap.get("errorCode");
	
			switch (errorCode) {

				case CodeStatus.SUCCESS_STATUS:
					
					success=true;
					
					break;
		
				default:
					throw new BOCAPIException((String) jsonMap.get("errorMessage"));
			}
			return success;
		}
	}
}
