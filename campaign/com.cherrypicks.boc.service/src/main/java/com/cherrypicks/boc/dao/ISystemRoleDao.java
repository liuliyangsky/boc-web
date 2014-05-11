package com.cherrypicks.boc.dao;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.model.SystemRole;


public interface ISystemRoleDao extends IBaseDao<SystemRole> {

	List<SystemRole> findAll();
	
	SystemRole findByName(String roleName);

	List<SystemRole> findByIds(List<Object> roleIds);

	List<SystemRole> findByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int... args);
}
