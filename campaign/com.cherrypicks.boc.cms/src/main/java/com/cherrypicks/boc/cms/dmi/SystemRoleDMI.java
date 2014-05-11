package com.cherrypicks.boc.cms.dmi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.constant.Const;
import com.cherrypicks.boc.cms.security.UserLoginHelper;
import com.cherrypicks.boc.cms.vo.Status;
import com.cherrypicks.boc.cms.vo.SystemRoleVo;
import com.cherrypicks.boc.dao.paging.PagingList;
import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.service.IAuthorizeService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;


public class SystemRoleDMI extends AbstractDMI<SystemRole> {
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private static final String EXISTS_ROLE_ERROR_MSG = "Role Name already exists.";
	
	@Autowired
	private IAuthorizeService authorizeService;
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request, HttpServletResponse response) {
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
        
        List<SystemRole> resultList = new ArrayList<SystemRole>();
        List<SystemRoleVo> roleVoList = new ArrayList<SystemRoleVo>();
        
        Map<String, Object> criteriaMap = getCriteriaMap(dsRequest);
        String searchType = (String) criteriaMap.get("searchType");
        
		if (Const.FILTER_TYPE.equalsIgnoreCase(searchType)) {
        	if (null != criteriaMap.get("userStatus")) {
        		String status = (String) criteriaMap.get("userStatus");
        		criteriaMap.put("status", Status.valueOf(status).getCode());
        	}
        	
        	resultList = authorizeService.findRoleByFilter(criteriaMap,sortBy,sortType,args);
		} else {
			resultList = authorizeService.findAllRole(args);
		}
		
		// convert systemRole to RoleVo
		if (null != resultList && !resultList.isEmpty()) {
			for (SystemRole role : resultList) {
				SystemRoleVo roleVo = convertToRoleVo(role);
				roleVoList.add(roleVo);
			}
		}
        
		DSResponse dsResponse = new DSResponse();
		if (args != null && resultList instanceof PagingList) {
			PagingList<SystemRole> pagingResultList = (PagingList<SystemRole>)resultList;
			dsResponse.setStartRow(startRow);
			dsResponse.setEndRow(startRow + pagingResultList.size());
			dsResponse.setTotalRows(pagingResultList.getTotalRecords());
		}
		dsResponse.setData(roleVoList);
		return dsResponse;
	}

	public DSResponse add(DSRequest dsRequest, SystemRoleVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			SystemRole role = convertToRole(record);
			if (authorizeService.findRoleByName(role.getRoleName()) != null) {
				throw new RuntimeException(EXISTS_ROLE_ERROR_MSG);
			}
			role.setCreatedBy(UserLoginHelper.getLoginUserName());
			role.setCreatedTime(new Date());
			role = authorizeService.addRole(role, record.getFunctions());
			dsResponse.setData(convertToRoleVo(role));
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}

	public DSResponse update(DSRequest dsRequest, SystemRoleVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			SystemRole role = convertToRole(record);
			SystemRole matchRole = authorizeService.findRoleByName(role.getRoleName());
			SystemRole curRole = authorizeService.findRoleById(role.getId());
			if (matchRole != null) {
				if (curRole == null || ((Long) curRole.getId()).longValue() != ((Long) matchRole.getId()).longValue()) {
					throw new RuntimeException(EXISTS_ROLE_ERROR_MSG);
				}
			}
			
			role.setUpdatedBy(UserLoginHelper.getLoginUserName());
			role.setUpdatedTime(new Date());
			
			BeanUtils.copyProperties(role, curRole);
			role = authorizeService.updateRole(curRole, record.getFunctions());
			dsResponse.setData(convertToRoleVo(role));
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
			
			Long count = authorizeService.findSystemUserCountByRoleIds(ids);
			if (null != count && count > 0) {
				dsResponse.setFailure();
				dsResponse.setData(Const.ERROR_MESSAGE);
			} else {
				List<SystemRole> removeData = authorizeService.findRoleByIds(ids);
				authorizeService.removeRole(ids, UserLoginHelper.getLoginUserName());
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
	
	private SystemRoleVo convertToRoleVo(SystemRole role) {
		if (role == null) {
			throw new IllegalArgumentException();
		}
		
		SystemRoleVo vo = new SystemRoleVo();
		BeanUtils.copyProperties(role, vo);
		List<SystemFunction> funcList = authorizeService.findFunctionByRoleId(role.getId());
		List<Object> funcIds = new ArrayList<Object>();
		for (SystemFunction func : funcList) {
			funcIds.add(func.getId());
		}
		vo.setFunctions(funcIds.toArray(new Long[]{}));
		return vo;
	}

	private SystemRole convertToRole(SystemRoleVo vo) {
		if (vo == null) {
			throw new IllegalArgumentException();
		}
		
		SystemRole role = new SystemRole();
		BeanUtils.copyProperties(vo, role);
		return role;
	}

}
