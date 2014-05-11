package com.cherrypicks.boc.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemUser;
import com.cherrypicks.boc.model.SystemUserRoleMap;

public interface IAuthorizeService {
	
	SystemUser findSystemUser(String userName);
	SystemUser findSystemUserById(Object id);
	List<SystemUser> findSystemUserByIds(List<Object> ids);
	List<SystemUser> findAllSystemUser(int...args);
	List<SystemUser> findSystemUserByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int...args);
	Long findSystemUserCountByRoleIds(List<Object> roleIds);
	SystemUser addSystemUser(SystemUser user, Object[] roles);
	SystemUser updateSystemUser(SystemUser user, Object[] roles);
	SystemUser updateSystemUser(SystemUser user);
	SystemUser changePassword(SystemUser user);
	void removeSystemUser(SystemUser user);
	Integer removeSystemUser(List<Object> userIds, String updatedBy);
	
	
	List<SystemRole> findAllRole();
	List<SystemRole> findAllRole(int... args);
	List<SystemRole> findRoleByUserName(String userName);
	List<SystemRole> findRoleByUserId(Object userId);
	SystemRole findRoleById(Object roleId);
	List<SystemRole> findRoleByIds(List<Object> roleIds);
	SystemRole findRoleByName(String roleName);
	List<SystemRole> findRoleByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int... args);
	SystemRole addRole(SystemRole role, Object[] functions);
	SystemRole updateRole(SystemRole role, Object[] functions);
	void removeRole(SystemRole role);
	Integer removeRole(List<Object> roleIds, String updatedBy);

	List<SystemFunction> findAllFunction();
	List<SystemFunction> findFunctionByUserName(String userName);
	List<SystemFunction> findFunctionByUserNameAndRoles(String userName, List<SystemRole> roleList);
	List<SystemFunction> findFunctionByRoleIds(Set<Object> roleIds);
	List<SystemFunction> findFunctionByRoleId(Object roleId);
	
	List<SystemUserRoleMap> findUserRoleMapByUserId(Object userId);
	List<SystemUserRoleMap> findUserRoleMapByUserIds(Set<Object> userIds);
}
