package com.cherrypicks.boc.dao.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ISystemRoleFunctionMapDao;
import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRoleFunctionMap;

public class SystemRoleFunctionMapDaoImpl extends AbstractBaseDaoDB2<SystemRoleFunctionMap>
		implements ISystemRoleFunctionMapDao {

	@Override
	public List<SystemFunction> findFunctionByRoleId(Object roleId) {
		String sql = "select sf.* from BOC_CAMPAIGN.SYSTEM_FUNCTION sf, BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP rfm where sf.ID = rfm.FUNC_ID and rfm.ROLE_ID = ?";
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.queryForList(sql, SystemFunction.class, param);
	}
	
	@Override
	public List<SystemFunction> findFunctionByRoleIds(Set<Object> roleIds) {
		String sql = "select sf.* from BOC_CAMPAIGN.SYSTEM_FUNCTION sf, BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP rfm where sf.ID = rfm.FUNC_ID and rfm.ROLE_ID in (:roleIds) ";
		Map<String, Set<Object>> paramMap = Collections.singletonMap("roleIds", roleIds);
		return super.queryForListWithNamedParam(sql, SystemFunction.class, paramMap);
	}

	@Override
	public List<SystemRoleFunctionMap> findByRoleId(Object roleId) {
		String sql = "select * from BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP where ROLE_ID = ?";
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.queryForList(sql, SystemRoleFunctionMap.class, param);
	}

	@Override
	public List<SystemRoleFunctionMap> findByRoleIds(List<Object> roleIds) {
		String sql = "select * from BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP where ROLE_ID in (:roleIds)";
		Map<String, List<Object>> paramMap = Collections.singletonMap("roleIds", roleIds);
		return super.queryForListWithNamedParam(sql, SystemRoleFunctionMap.class, paramMap);
	}
	
	@Override
	public Integer deleteByRoleId(Object roleId) {
		String sql = "delete from BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP where ROLE_ID = ?";
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.updateForRecord(sql.toString(), param);
	}
	
	@Override
	public Integer deleteByRoleIds(List<Object> roleIds) {
		String sql = "delete from BOC_CAMPAIGN.SYSTEM_ROLE_FUNCTION_MAP where ROLE_ID in (:roleIds)";
		Map<String, List<Object>> paramMap = Collections.singletonMap("roleIds", roleIds);
		return super.updateForRecordWithNamedParam(sql.toString(), paramMap);
	}

	@Override
	public List<SystemRoleFunctionMap> findByFilter(Map<String, Object> criteriaMap, 
			String sortField, String sortType,int... args) {
		// TODO Auto-generated method stub
		return null;
	}
}
