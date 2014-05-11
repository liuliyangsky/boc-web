package com.cherrypicks.boc.api.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

/**
 * 功能描述：输出请求日志
 * 
 * @author edwin.zhou
 * 
 */
@Component
public class RequestParameterLogFilter implements Filter {
	private Log log = LogFactory.getLog(this.getClass());
	private Log requestParameterLog = LogFactory.getLog("REQUEST_PARAMETER_LOG");
	private static int PARAM_OUTPUT_LEN = 50;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.log.info("init");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		Enumeration<?> names = request.getParameterNames();

		String uri = request.getRequestURI();
		StringBuilder logInfo = new StringBuilder();
		logInfo.append(uri).append("?");

		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if(name.equals("password")){
				
				logInfo.append(name).append("=").append("******").append("&");
			}else{
				String[] values = request.getParameterValues(name);
				
				logInfo.append(name).append("=").append(ararryToString(values)).append("&");
			}
		}

		requestParameterLog.info(logInfo);

		chain.doFilter(req, resp);
	}

	/**
	 * 功能描述：输出数组,枨式为[value1 ; value2 ; .......]
	 * 
	 * @param values
	 * @return
	 */
	public String ararryToString(String[] values) {
		if (values == null) {
			return null;
		}

		StringBuilder result = new StringBuilder();

		if (values.length > 1) {
			result.append("[");
		}

		for (String value : values) {

			//String shortValue = subStringParamter(value);
			String shortValue = value;
			if (result.length() > 0) {
				result.append(" ; ").append(shortValue);
			} else {
				result.append(shortValue);
			}
		}

		if (values.length > 1) {
			result.append("]");
		}

		return result.toString();
	}

	/**
	 * 功能描述:截取超长字符
	 * 
	 * @param value
	 * @return
	 */
	public String subStringParamter(String value) {

		if (value == null) {
			return null;
		}

		if (value.length() <= PARAM_OUTPUT_LEN) {
			return value;
		}

		return value.substring(0, PARAM_OUTPUT_LEN);
	}

	@Override
	public void destroy() {

		this.log.info("destroy");
	}

}
