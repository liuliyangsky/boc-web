package com.cherrypicks.boc.api.inteceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Inteceptor implements HandlerInterceptor {

	private Log responseLog = LogFactory.getLog("RESPONSE_RESULT_LOG");
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String message=modelAndView.getModel().get("message")==null?"":(String)modelAndView.getModel().get("message");
		responseLog.debug(request.getServletPath()+"?reponese="+message);


	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
