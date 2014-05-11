package com.cherrypicks.boc.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ISystemUserDao;
import com.cherrypicks.boc.model.SystemUser;
import com.cherrypicks.boc.utils.DateUtil;

public class SystemUserDaoImpl extends AbstractBaseDaoDB2<SystemUser> implements ISystemUserDao{
	

	@Override
	public SystemUser findByUserName(String userName) {
		String sql = "select * from BOC_CAMPAIGN.SYSTEM_USER where USER_NAME = ?";
		StatementParameter param = new StatementParameter();
		param.setString(userName);
		return super.query(sql, SystemUser.class,param);
	}

	@Override
	public List<SystemUser> findByFilter(Map<String, Object> criteriaMap, 
			String sortField, String sortType,int...args) {
		String id = (String) criteriaMap.get("id");
		String userName = (String) criteriaMap.get("userName");
		String mobile = (String) criteriaMap.get("mobile");
		String email = (String) criteriaMap.get("email");
		Long status = (Long) criteriaMap.get("status");
		Date createdTimeFromDate = (Date) criteriaMap.get("createdTimeFromDate");
		Date createdTimeToDate = (Date) criteriaMap.get("createdTimeToDate");
		
		Long roleId = (Long) criteriaMap.get("roles");
		
		StringBuilder sqlCount = new StringBuilder("SELECT COUNT(1) FROM BOC_CAMPAIGN.SYSTEM_USER U INNER JOIN BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP R ON U.ID = R.USER_ID WHERE 1=1 ");
		StringBuilder sql = new StringBuilder("SELECT U.* FROM BOC_CAMPAIGN.SYSTEM_USER U INNER JOIN BOC_CAMPAIGN.SYSTEM_USER_ROLE_MAP R ON U.ID = R.USER_ID WHERE 1=1 ");
		StatementParameter param = new StatementParameter();
		
		if (null != id) {
			sqlCount.append("AND U.ID = ? ");
			sql.append("AND U.ID = ? ");
			param.setLong(Long.parseLong(id));
		}
		
		if (null != roleId) {
			sqlCount.append("AND R.ROLE_ID = ? ");
			sql.append("AND R.ROLE_ID = ? ");
			param.setLong(roleId);
		}
		
		if (null != userName) {
			sqlCount.append("AND U.USER_NAME LIKE ? ");
			sql.append("AND U.USER_NAME like ? ");
			param.setString("%" + userName + "%");
		}
		
		if (null != mobile) {
			sqlCount.append("AND U.MOBILE LIKE ? ");
			sql.append("AND U.MOBILE LIKE ? ");
			param.setString("%" + mobile + "%");
		}
		
		if (null != email) {
			sqlCount.append("AND U.EMAIL LIKE ? ");
			sql.append("AND U.EMAIL LIKE ? ");
			param.setString("%" + email + "%");
		}
		
		if (null != status) {
			sqlCount.append("AND U.STATUS = ? ");
			sql.append("AND U.STATUS = ? ");
			param.setInt(status.intValue());
		}
		
		if (null != createdTimeFromDate) {
			sqlCount.append("AND U.CREATED_TIME >= ? ");
			sql.append("AND U.CREATED_TIME >= ? ");
			param.setDate(createdTimeFromDate);
		}
		
		if (null != createdTimeToDate) {
			String toDate = String.format("%1$tY-%1$tm-%1$td", createdTimeToDate);
			sqlCount.append("AND (U.CREATED_TIME <= ? OR U.CREATED_TIME LIKE ? ) ");
			sql.append("AND (U.CREATED_TIME <= ? OR U.CREATED_TIME LIKE ?) ");
			param.setDate(DateUtil.getDateWithTime(createdTimeToDate));
			param.setString(toDate+"%");
		}
		
		String tableAlias = "U";
		if (StringUtils.isNotEmpty(sortField)) {
			if(sortField.equals("roles")){
				tableAlias = "R";
                sortField = "roleId";
			}
		}
		
		return getPagingList(SystemUser.class, sqlCount, sql, param, sortField,sortType,tableAlias, args);
	}
}
