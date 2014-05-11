package com.cherrypicks.boc.api.web.controller.webservice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cherrypicks.boc.api.service.APITestService;
import com.cherrypicks.boc.api.util.ResultUtil;

@Controller
public class TestServiceController {

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private APITestService testService;

	@RequestMapping(value = "/test.do")
	public String test(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
		log.info("-------test start-------");
		
		String jsonStr = null;
		
		jsonStr = ResultUtil.getResultJson(testService.count());
		
		model.put("message", jsonStr);

		log.info("-------test end-------");
		return "message.json";
	}
	
	@RequestMapping(value = "/testApi.do")
	public String testApi(HttpServletRequest request, ModelMap model, String p) throws Exception {
		
		log.info("-------test start-------");
		
		String jsonStr = null;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("zh_CN", testService.getAll());
		map.put("en_US", testService.getAll());
		jsonStr = ResultUtil.getResultJson(map);
		
		model.put("message", jsonStr);

		log.info("-------test end-------");
		return "message.json";
	}
}
