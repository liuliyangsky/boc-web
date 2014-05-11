package com.cherrypicks.boc.cms.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemUser;
import com.cherrypicks.boc.service.IAuthorizeService;

public class CmsAuthenticationProvider implements AuthenticationProvider {
	
	private static final Log logger = LogFactory.getLog(CmsAuthenticationProvider.class);
	
	@Autowired
	private IAuthorizeService authorizeService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)authentication;

        String userName = userToken.getName();

        if (!StringUtils.hasLength(userName)) {
            throw new BadCredentialsException("Empty Username");
        }

        String password = (String) authentication.getCredentials();
        Assert.notNull(password, "Null password was supplied in authentication token");

        if (password.length() == 0) {
            logger.debug("Rejecting empty password for user " + userName);
            throw new BadCredentialsException("Empty Password");
        }
        
        SystemUser user = authorizeService.findSystemUser(userName);
        
        if (user == null) {
        	logger.debug("Invalid User or Password");
        	throw new BadCredentialsException("Invalid User or Password");
        }
        
        if (user.getStatus() == null || user.getStatus() == 0) {
        	logger.debug("Inactive User");
        	throw new BadCredentialsException("Inactive User");
        }
        
        if (!password.equalsIgnoreCase(user.getPassword())) {
        	logger.debug("Invalid Password");
        	throw new BadCredentialsException("Invalid User or Password");
        }
        
        return createSuccessfulAuthentication(user, authentication);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
	
	protected Authentication createSuccessfulAuthentication(Object principal, Authentication authentication) {
		List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>();
		List<SystemRole> roleList = authorizeService.findRoleByUserName(authentication.getName());
		Set<Object> roleIds = new HashSet<Object>();
		if (roleList.size() > 0) {
			for (SystemRole role : roleList) {
				roleIds.add(role.getId());
				gaList.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
			gaList.add(new SimpleGrantedAuthority("ROLE_AUTHENTICATED"));
		} else {
			gaList.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		}
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal,
				authentication.getCredentials(), gaList);
		
		// get functions
		List<String> functions = new ArrayList<String>();
		List<SystemFunction> functionList = authorizeService.findFunctionByRoleIds(roleIds);
		for (SystemFunction function : functionList) {
			functions.add(function.getFuncCode());
		}
		
		// set details
		auth.setDetails(functions.toArray(new String[]{}));
		return auth;
    }

}
