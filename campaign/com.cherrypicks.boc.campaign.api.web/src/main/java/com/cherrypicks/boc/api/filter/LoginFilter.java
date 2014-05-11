package com.cherrypicks.boc.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.cherrypicks.boc.api.exception.FilterException;
import com.cherrypicks.boc.api.util.RequestUtil;
import com.cherrypicks.boc.common.util.EncryptionUtil;
import com.cherrypicks.boc.common.util.URLParameterUtil;

/**
 * 
 * 
 * @author edwin.zhou
 * 
 */
@Component
public class LoginFilter implements Filter {

	private Log logger = LogFactory.getLog(this.getClass());
	private Log requestParameterLog = LogFactory.getLog("REQUEST_PARAMETER_LOG");
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.logger.info("init");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		String p = request.getParameter("p");
		// get userId, session, sn
		/*String userId = request.getParameter("userId");
		String authToken = request.getParameter("authToken");*/
		
		String uri = RequestUtil.getRequestUri(request);
		if (StringUtils.isEmpty(uri)) {
			throw new FilterException("Get request URI is null.");
		}else if(uri.indexOf("/bindSnsInApp.do")!=-1 ){
			
			Assert.hasText(p, "p parameter is require!");
			try {
				/*decrypt P and set map*/
				String decryptp = EncryptionUtil.decrypt(p, EncryptionUtil.DECRYPT_KEY);
				requestParameterLog.debug(decryptp);
				URLParameterUtil urlAnalysis = new URLParameterUtil();
			    urlAnalysis.analysis(decryptp);
			   /* userId=urlAnalysis.getParam("userId");
			    authToken=urlAnalysis.getParam("authToken");*/
			    
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(request.getCharacterEncoding() == null){
		  request.setCharacterEncoding("UTF-8"); 
		}
		  
		chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {

		this.logger.info("destory");
	}

}
