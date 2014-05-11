package com.cherrypicks.boc.cms.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cherrypicks.boc.model.SystemUser;
import com.isomorphic.rpc.RPCManager;

public class AuthAgent extends HttpServlet {

	private static final long serialVersionUID = -3023302478975425132L;
	private static final Log logger = LogFactory.getLog(AuthAgent.class);
	
	private static Map<String, String> authPagesMap;
	private static Map<String, String> authMenusMap;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAuth(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doAuth(req, resp);
	}
	
	private void doAuth(HttpServletRequest req, HttpServletResponse resp) {
		RPCManager rpc = null;
		try {
			rpc = new RPCManager(req, resp, resp.getWriter());
			SystemUser user = UserLoginHelper.getLoginUser();
			if (user == null) {
		        rpc.send("null");
		    }
		    else {
		        StringBuilder auth = new StringBuilder("({userName: \"")
		           .append(user.getUserName()).append("\", sessionId:\"");
		        auth.append(req.getSession().getId() + "\", ");
		        auth.append("roles: [");
		        boolean flag = false;
		        for (GrantedAuthority ga : UserLoginHelper.getLoginUserAuthorities()) {
		        	if (flag) {
		        		auth.append(",");
		        	} else {
		        		flag = true;
		        	}
		        	auth.append("\"");
		        	auth.append(ga.getAuthority());
		        	auth.append("\"");
		        }
		        auth.append("]");
		        String[] functions = UserLoginHelper.getLoginUserFunctions();
		        flag = false;
		        if (functions != null && functions.length > 0) {
		        	StringBuffer pageBuf = new StringBuffer();
		        	StringBuffer menuBuf = new StringBuffer();
		        	String page = null;
		        	String menu = null;
		        	boolean pageFlag = false;
		        	boolean menuFlag = false;
		        	auth.append(", functions: [");
		        	for (String func : functions) {
		        		if (flag) {
			        		auth.append(",");
			        	} else {
			        		flag = true;
			        	}
			        	auth.append("\"");
			        	auth.append(func);
			        	auth.append("\"");
			        	
			        	page = getAuthPage(func);
		        		if (page != null) {
		        			if (pageFlag) {
				        		pageBuf.append(",");
				        	} else {
				        		pageFlag = true;
				        	}
		        			pageBuf.append(page);
		        		}
		        		
		        		menu = getAuthMenu(func);
		        		if (menu != null) {
		        			if (menuFlag) {
			        			menuBuf.append(",");
				        	} else {
				        		menuFlag = true;
				        	}
		        			menuBuf.append("\"");
		        			menuBuf.append(menu);
		        			menuBuf.append("\"");
		        		}
		        	}
		        	auth.append("], pages: [");
		        	auth.append(pageBuf.toString());
		        	auth.append("], menus: [");
		        	auth.append(menuBuf.toString());
		        	auth.append("]");
		        }
		        auth.append("})");
		        rpc.send(auth.toString());
		    }
		} catch (Exception e) {
			logger.error("auth failed!", e);
			if (rpc != null) {
				try {
					rpc.send("auth failed");
				} catch (Exception e1) {}
			}
		}
	}
	
	public String getAuthPage(String function) {
		Map<String, String> map = getAuthPagesMap();
		if (map != null) {
			return map.get(function);
		}
		return null;
	}
	
	public String getAuthMenu(String menu) {
		Map<String, String> map = getAuthMenusMap();
		if (map != null) {
			return map.get(menu);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Map<String, String> getAuthPagesMap() {
		if (authPagesMap == null) {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			authPagesMap = (Map<String, String>) ctx.getBean("authPagesMap");
		}
		return authPagesMap;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Map<String, String> getAuthMenusMap() {
		if (authMenusMap == null) {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			authMenusMap = (Map<String, String>) ctx.getBean("authMenusMap");
		}
		return authMenusMap;
	}

}
