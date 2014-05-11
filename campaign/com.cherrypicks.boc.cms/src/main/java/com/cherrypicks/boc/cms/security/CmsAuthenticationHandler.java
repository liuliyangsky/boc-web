package com.cherrypicks.boc.cms.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;

public class CmsAuthenticationHandler implements AuthenticationSuccessHandler,
		AuthenticationFailureHandler, InvalidSessionStrategy {

	private static final String SUCCESS_MARKER = "/isomorphic/login/loginSuccessMarker.html";
	private static final String LOGIN_REQUIRED_MARKER = "/isomorphic/login/loginRequiredMarker.html";
	//private static final String MAX_LOGIN_ATTEMPTS_MARKER = "/isomorphic/login/maxLoginAttemptsExceededMarker.html";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_REQUIRED_MARKER);
		dispatcher.forward(request, response);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth) throws IOException,
			ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(SUCCESS_MARKER);
		dispatcher.forward(request, response);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_REQUIRED_MARKER);
		dispatcher.forward(request, response);
	}

}
