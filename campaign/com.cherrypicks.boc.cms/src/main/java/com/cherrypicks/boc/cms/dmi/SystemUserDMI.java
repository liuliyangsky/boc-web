package com.cherrypicks.boc.cms.dmi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.constant.Const;
import com.cherrypicks.boc.cms.security.UserLoginHelper;
import com.cherrypicks.boc.cms.vo.SystemUserVo;
import com.cherrypicks.boc.dao.paging.PagingList;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemUser;
import com.cherrypicks.boc.model.SystemUserRoleMap;
import com.cherrypicks.boc.service.IAuthorizeService;
import com.cherrypicks.boc.utils.EncryptMD5Util;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class SystemUserDMI extends AbstractDMI<SystemUser> {
	
	private Logger log = Logger.getLogger(SystemUserDMI.class.getName());
	
	private static final String EXISTS_USER_ERROR_MSG = "User Name already exists!";
	
	@Autowired
	private IAuthorizeService authorizeService;
	
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response, SystemUserVo record) {
		int startRow = (int)dsRequest.getStartRow();
		int endRow = (int)dsRequest.getEndRow();
		
		int[] args = null;
		if (startRow >= 0 && endRow - startRow > 0) {
			args = new int[] {startRow, endRow - startRow};
		}
		
		String sortBy = dsRequest.getSortBy();
		String sortType = "ASC";
		if (StringUtils.isNotEmpty(sortBy)) {
			if (sortBy.startsWith("-")) {
				sortBy = sortBy.substring(1);
				sortType = "DESC";
			}
		}
        
        List<SystemUser> resultList = new ArrayList<SystemUser>();
        List<SystemUserVo> userVoList = new ArrayList<SystemUserVo>();
        
        Map<String, Object> criteriaMap = getCriteriaMap(dsRequest);
        String searchType = (String) criteriaMap.get("searchType");
		
		if (Const.FILTER_TYPE.equalsIgnoreCase(searchType)) {
			resultList = authorizeService.findSystemUserByFilter(criteriaMap,sortBy,sortType,args);
		} else {
			resultList = authorizeService.findAllSystemUser(args);
		}
		
		// convert systemUser to systemUserVo
		if (null != resultList && !resultList.isEmpty()) {
			userVoList = convertToUserVo(resultList);
		}
        
		DSResponse dsResponse = new DSResponse();
		if (args != null && resultList instanceof PagingList) {
			PagingList<SystemUser> pagingResultList = (PagingList<SystemUser>)resultList;
			dsResponse.setStartRow(startRow);
			dsResponse.setEndRow(startRow + pagingResultList.size());
			dsResponse.setTotalRows(pagingResultList.getTotalRecords());
		}
		dsResponse.setData(userVoList);
		return dsResponse;
	}

	public DSResponse add(DSRequest dsRequest, SystemUserVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			SystemUser user = convertToUser(record);
			if (authorizeService.findSystemUser(user.getUserName()) != null) {
				dsResponse.setFailure();
				dsResponse.setData(EXISTS_USER_ERROR_MSG);
			} else {
				user.setPassword(EncryptMD5Util.getMD5(user.getPassword()));
		        user.setCreatedBy(UserLoginHelper.getLoginUserName());
				user.setCreatedTime(new Date());
				user = authorizeService.addSystemUser(user, record.getRoles());
				dsResponse.setData(convertToUserVo(user));
				dsResponse.setSuccess();
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}

	public DSResponse update(DSRequest dsRequest, SystemUserVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			SystemUser user = convertToUser(record);
			SystemUser matchUser = authorizeService.findSystemUser(user.getUserName());
			SystemUser curUser = authorizeService.findSystemUserById(user.getId());
			if (matchUser != null) {
				if (curUser == null || ((Long) curUser.getId()).longValue() != ((Long) matchUser.getId()).longValue()) {
					throw new RuntimeException(EXISTS_USER_ERROR_MSG);
				}
			}
			
			if (null != user.getPassword() && !user.getPassword().equals(curUser.getPassword())) {
				user.setPassword(EncryptMD5Util.getMD5(user.getPassword()));
			}
			user.setUpdatedBy(UserLoginHelper.getLoginUserName());
			user.setUpdatedTime(new Date());
			
			BeanUtils.copyProperties(user, curUser);
			user = authorizeService.updateSystemUser(curUser, record.getRoles());
			dsResponse.setData(convertToUserVo(user));
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	@SuppressWarnings("unchecked")
	public DSResponse remove(DSRequest dsRequest) {
		DSResponse dsResponse = new DSResponse();
		try {
			Map<String, List<Object>> criteria = dsRequest.getOldValues();
			List<Object> ids = (List<Object>) criteria.get("ids");
			if (null != ids) {
				List<SystemUser> removeData = authorizeService.findSystemUserByIds(ids);
				authorizeService.removeSystemUser(ids, UserLoginHelper.getLoginUserName());
				dsResponse.setSuccess();
				dsResponse.setData(removeData);
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}

	private SystemUserVo convertToUserVo(SystemUser user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		
		SystemUserVo vo = new SystemUserVo();
		BeanUtils.copyProperties(user, vo);
		List<SystemRole> roleList = authorizeService.findRoleByUserName(user.getUserName());
		List<Object> roleIds = new ArrayList<Object>();
		for (SystemRole role : roleList) {
			roleIds.add(role.getId());
		}
		vo.setRoles(roleIds.toArray(new Long[]{}));
		return vo;
	}
	
	private List<SystemUserVo> convertToUserVo(List<SystemUser> userList) {
		if (userList == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Object> userIdSet = new HashSet<Object>();
		for (SystemUser user : userList) {
			userIdSet.add(user.getId());
		}
		
		List<SystemUserRoleMap> userRoleList = authorizeService.findUserRoleMapByUserIds(userIdSet);
		Map<Object, Set<Object>> userRoleMap = new HashMap<Object, Set<Object>>();
		//Map<Long, Set<String>> userRoleNameMap = new HashMap<Long, Set<String>>();
		for (SystemUserRoleMap userRole : userRoleList) {
			if (userRoleMap.containsKey(userRole.getUserId())) {
				userRoleMap.get(userRole.getUserId()).add(userRole.getRoleId());
				//userRoleNameMap.get(userRole.getUserId()).add(userRole.getRole().getRoleName());
			} else {
				Set<Object> roleSet = new HashSet<Object>();
				roleSet.add(userRole.getRoleId());
				userRoleMap.put(userRole.getUserId(), roleSet);
				
				//Set<String> roleNameSet = new HashSet<String>();
				//roleNameSet.add(userRole.getRole().getRoleName());
				//userRoleNameMap.put(userRole.getUserId(), roleNameSet);
			}
		}
		
		List<SystemUserVo> voList = new ArrayList<SystemUserVo>();
		for (SystemUser user : userList) {
			SystemUserVo vo = new SystemUserVo();
			BeanUtils.copyProperties(user, vo);
			
			if (null != userRoleMap.get(user.getId())) {
				vo.setRoles(userRoleMap.get(user.getId()).toArray((new Long[]{})));
				//vo.setRoleNames(userRoleNameMap.get(user.getId()).toArray((new String[]{})));
			}
			voList.add(vo);
		}
		return voList;
	}

	private SystemUser convertToUser(SystemUserVo vo) {
		if (vo == null) {
			throw new IllegalArgumentException();
		}
		
		SystemUser user = new SystemUser();
		BeanUtils.copyProperties(vo, user);
		return user;
	}
}