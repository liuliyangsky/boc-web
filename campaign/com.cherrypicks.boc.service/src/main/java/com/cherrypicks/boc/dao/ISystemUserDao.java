package com.cherrypicks.boc.dao;

import com.cherrypicks.boc.model.SystemUser;


public interface ISystemUserDao extends IBaseDao<SystemUser> {
	
	SystemUser findByUserName(String userName);
}
