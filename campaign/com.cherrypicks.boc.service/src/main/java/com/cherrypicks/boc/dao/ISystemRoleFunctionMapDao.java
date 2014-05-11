package com.cherrypicks.boc.dao;

import java.util.List;
import java.util.Set;

import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRoleFunctionMap;


public interface ISystemRoleFunctionMapDao extends IBaseDao<SystemRoleFunctionMap> {

	List<SystemFunction> findFunctionByRoleId(Object roleId);

	List<SystemFunction> findFunctionByRoleIds(Set<Object> roleIds);
	
	List<SystemRoleFunctionMap> findByRoleId(Object roleId);
	
	List<SystemRoleFunctionMap> findByRoleIds(List<Object> roleIds);
	
	Integer deleteByRoleIds(List<Object> roleIds);

	Integer deleteByRoleId(Object roleId);

//	Integer removeByRoleIds(List<Object> roleIds, String updatedBy);
}
