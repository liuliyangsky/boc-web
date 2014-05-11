package com.cherrypicks.boc.api.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统功能不存在控制器
 * 
 * @author yuanwen
 */
@Controller
public class PageNotFoundController {
	private static final Log LOGGER = LogFactory.getLog(PageNotFoundController.class);

	@RequestMapping(value = "/pageNotFound.do")
	public String pageNotFound(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		//String uri = RequestUtil.getRequestUri(request);
		String uri = "";
		LOGGER.info("uri:" + uri);

		return "html/pageNotFound";
	}

}
