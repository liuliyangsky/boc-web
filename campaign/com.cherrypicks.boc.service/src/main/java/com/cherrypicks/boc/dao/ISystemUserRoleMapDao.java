package com.cherrypicks.boc.dao;

import java.util.List;
import java.util.Set;

import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemUserRoleMap;

public interface ISystemUserRoleMapDao extends IBaseDao<SystemUserRoleMap> {

	Long findSystemUserCountByRoleIds(List<Object> roleIds);
	
	List<SystemRole> findRoleByUserName(String userName);
	
	List<SystemRole> findRoleByUserId(Object userId);

	List<SystemUserRoleMap> findUserRoleMapByUserId(Object userId);

	List<SystemUserRoleMap> findUserRoleMapByUserIds(Set<Object> userIds);

	Integer deleteByUserIds(List<Object> userIds);

	Integer deleteByUserId(Object id);

//	Integer removeByUserIds(List<Object> userIds, String updatedBy);

}
