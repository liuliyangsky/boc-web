package com.cherrypicks.boc.service;

import java.util.HashMap;
import java.util.Map;

import com.cherrypicks.boc.utils.HttpClientUtils;

public class TestPushNotification {

	public static void main(String[] args) {
		try {
//			// device registration
//			Map<String, String> params1 = new HashMap<String, String>();
//			params1.put("deviceID", "APA91bHUE3J-HCtm-_yZDzTPggD2HYGNgZOwL1tGu6M5pYIpC0_qMUjAV96rBy2Y1t3wjb26b5i6H-iP_4Fd6HGOfwjDdGExVhcJ6X9MvvOWdTiABcHHIveyk4kiD5szu4dbVdSN-ohrh2669na9IixQkEb1NkaH9g");
//			params1.put("deviceType", "android");
//			params1.put("projectAlias", "swsapp_upgrade_test");
//			params1.put("lang", "zh_HK");
//			params1.put("opt", "in");
//			System.out.println("registration result:" + HttpClientUtils.postMethodRequest("http://office2.cherrypicks.com:8080/pushadmin/deviceReg", params1));
//			
//			Thread.sleep(2 * 1000);
	
			// push message
			StringBuilder xml = new StringBuilder();
			xml.append("<PUSH>");
			xml.append("<authentification><username>jane</username><password>aabb1122</password></authentification>");
			xml.append("<projectID>swsapp_upgrade_test</projectID>");
			xml.append("<scheduletime><starttime>2014-03-04 15:00</starttime><endtime>2014-03-04 15:20</endtime></scheduletime>");
			xml.append("<recipients>");
			xml.append("<device>");
			xml.append("<deviceID>APA91bHyCZD6bg2gRJdM1-eoZ3q1wl0hrQfbf4yF67ect-3OBHr5NjvFZMClHEr0dd3O960AFe3ipqQW5jbZmNdGaXsHGHQuakABQKW7C9fDDMP1X_zBS49fkHrkfS3NL1J4yus9YjfSIalT9X7gOHrBVRmK6hMPmQ</deviceID>");
			xml.append("<alert><Body-en-us>message2</Body-en-us><Body-zh-hk>message2</Body-zh-hk><Body-zh-cn>message2</Body-zh-cn></alert>");
			xml.append("<support_device>2</support_device>");
			xml.append("<parameter>");
			xml.append("<item><key>type</key><value>1</value><lang>1</lang></item>");
			xml.append("<item><key>messageId</key><value>100001</value><lang>1</lang></item>");
			xml.append("</parameter>");
			xml.append("</device>");
			xml.append("</recipients>");
			xml.append("</PUSH>");
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("type", "xml");
			params.put("xml", xml.toString());
			String result = HttpClientUtils.postMethodRequest("http://office2.cherrypicks.com:8080/pushadmin/pushService.do?method=PushIndiv", params);
			System.out.println("push result:" + result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
