package com.cherrypicks.boc.cms.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.cherrypicks.boc.model.SystemUser;

public class UserLoginHelper {
	
	public static SystemUser getLoginUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			if (auth.getPrincipal() instanceof SystemUser)
				return (SystemUser) auth.getPrincipal();
		}
		return null;
	}
	
	public static Collection<? extends GrantedAuthority> getLoginUserAuthorities() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
	
	public static String[] getLoginUserFunctions() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getDetails() != null && auth.getDetails() instanceof String[]) {
			String[] functions = (String[]) auth.getDetails();
			if (null != functions)
				return (String[]) functions;
		}
		return null;
	}
	
	public static String getLoginUserName() {
		SystemUser user = getLoginUser();
		Assert.notNull(user, "Login user must not be null!");
		return user.getUserName();
	}
	
	public static Boolean hasAuthority(String authority) {
		return getLoginUserAuthorities().contains(new SimpleGrantedAuthority(authority));
	}

}
