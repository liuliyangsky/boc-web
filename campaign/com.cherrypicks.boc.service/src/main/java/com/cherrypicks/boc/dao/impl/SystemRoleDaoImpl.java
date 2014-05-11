package com.cherrypicks.boc.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ISystemRoleDao;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.utils.DateUtil;

public class SystemRoleDaoImpl extends AbstractBaseDaoDB2<SystemRole> implements ISystemRoleDao {

	@Override
	public List<SystemRole> findAll() {
		String sql = "SELECT * FROM BOC_CAMPAIGN.SYSTEM_ROLE ORDER BY ROLE_NAME";
		return super.queryForList(sql.toString(), SystemRole.class);
	}

	@Override
	public SystemRole findByName(String roleName) {
		String sql = "SELECT * FROM BOC_CAMPAIGN.SYSTEM_ROLE WHERE ROLE_NAME = ?";
		StatementParameter param = new StatementParameter();
		param.setString(roleName);
		return super.query(sql, SystemRole.class, param);
	}

	@Override
	public List<SystemRole> findByIds(List<Object> roleIds) {
		return super.findByIds(roleIds);
	}

	@Override
	public List<SystemRole> findByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int... args) {
		String id = (String) criteriaMap.get("id");
		String roleName = (String) criteriaMap.get("roleName");
		String roleDesc = (String) criteriaMap.get("roleDesc");
		Date createdTimeFromDate = (Date) criteriaMap.get("createdTimeFromDate");
		Date createdTimeToDate = (Date) criteriaMap.get("createdTimeToDate");

		StringBuilder sqlCount = new StringBuilder("select count(*) from BOC_CAMPAIGN.SYSTEM_ROLE where 1 = 1 ");
		StringBuilder sql = new StringBuilder("select * from BOC_CAMPAIGN.SYSTEM_ROLE where 1 = 1 ");
		StatementParameter param = new StatementParameter();
		
		if (null != id) {
			sqlCount.append("and ID = ? ");
			sql.append("and ID = ? ");
			param.setLong(Long.parseLong(id));
		}
		
		if (null != roleName && !roleName.trim().isEmpty()) {
			sqlCount.append("and ROLE_NAME like ? ");
			sql.append("and ROLE_NAME like ? ");
			param.setString("%" + roleName + "%");
		}
		
		if (null != roleDesc && !roleDesc.trim().isEmpty()) {
			sqlCount.append("and ROLE_DESC like ? ");
			sql.append("and ROLE_DESC like ? ");
			param.setString("%" + roleDesc + "%");
		}
		
		if (null != createdTimeFromDate) {
			sqlCount.append("and CREATED_TIME >= ? ");
			sql.append("and CREATED_TIME >= ? ");
			param.setDate(DateUtil.getDateWithoutTime(createdTimeFromDate));
		}
		
		if (null != createdTimeToDate) {
			String toDate = String.format("%1$tY-%1$tm-%1$td", createdTimeToDate);
			sqlCount.append("AND (CREATED_TIME <= ? OR CREATED_TIME LIKE ? ) ");
			sql.append("AND (CREATED_TIME <= ? OR CREATED_TIME LIKE ?) ");
			param.setDate(DateUtil.getDateWithTime(createdTimeToDate));
			param.setString(toDate+"%");
		}

		return getPagingList(SystemRole.class, sqlCount, sql, param,sortField,sortType,null,args);
	}
}
