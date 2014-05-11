package com.cherrypicks.boc.dao.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ISystemUserRoleMapDao;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemUserRoleMap;

public class SystemUserRoleMapDaoImpl extends AbstractBaseDaoDB2<SystemUserRoleMap> implements ISystemUserRoleMapDao {


	@Override
	public Long findSystemUserCountByRoleIds(List<Object> roleIds) {
		String sql = "select count(ID) from BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP where ROLE_ID in (:roleIds)";
		Map<String, List<Object>> paramMap = Collections.singletonMap("roleIds", roleIds);
		return super.queryForLongWithNamedParam(sql, paramMap); 
	}
	
	@Override
	public List<SystemRole> findRoleByUserName(String userName) {
		StringBuilder sql = new StringBuilder("select r.* from BOC_CAMPAIGN.SYSTEM_ROLE r, BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP map, BOC_CAMPAIGN.SYSTEM_USER u ");
		sql.append("where map.ROLE_ID = r.ID and map.USER_ID = u.ID and u.USER_NAME = ?");
		StatementParameter param = new StatementParameter();
		param.setString(userName);
		return super.queryForList(sql.toString(), SystemRole.class, param);
	}
	
	@Override
	public List<SystemRole> findRoleByUserId(Object userId) {
		StringBuilder sql = new StringBuilder("select r.* from BOC_CAMPAIGN.SYSTEM_ROLE r, BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP map");
		sql.append("where map.ROLE_ID = r.ID and map.USER_ID = ?");
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.queryForList(sql.toString(), SystemRole.class, param);
	}

	@Override
	public List<SystemUserRoleMap> findUserRoleMapByUserId(Object userId) {
		String sql = "select * from BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP where USER_ID = ?";
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.queryForList(sql, SystemUserRoleMap.class, param);
	}
	
	@Override
	public List<SystemUserRoleMap> findUserRoleMapByUserIds(Set<Object> userIds) {
		String sql = "select * from BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP where USER_ID in (:userIds)";
		Map<String, Set<Object>> paramMap = Collections.singletonMap("userIds", userIds);
		return super.queryForListWithNamedParam(sql, SystemUserRoleMap.class, paramMap);
	}

	@Override
	public Integer deleteByUserIds(List<Object> userIds) {
		String sql = "delete from BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP where USER_ID in (:userIds)";
		Map<String, List<Object>> paramMap = Collections.singletonMap("userIds", userIds);
		return super.updateForRecordWithNamedParam(sql, paramMap);
	}
	
	@Override
	public Integer deleteByUserId(Object userId) {
		String sql = "delete from BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP where USER_ID = ?";
		StatementParameter param = new StatementParameter();
		try {
			param.setObject(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.updateForRecord(sql, param);
	}

	@Override
	public List<SystemUserRoleMap> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType,int... args) {
		return null;
	}

}
