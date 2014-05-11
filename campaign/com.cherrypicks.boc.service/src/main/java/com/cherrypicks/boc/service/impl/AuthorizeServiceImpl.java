package com.cherrypicks.boc.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cherrypicks.boc.dao.ISystemFunctionDao;
import com.cherrypicks.boc.dao.ISystemRoleDao;
import com.cherrypicks.boc.dao.ISystemRoleFunctionMapDao;
import com.cherrypicks.boc.dao.ISystemUserDao;
import com.cherrypicks.boc.dao.ISystemUserRoleMapDao;
import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.model.SystemRole;
import com.cherrypicks.boc.model.SystemRoleFunctionMap;
import com.cherrypicks.boc.model.SystemUser;
import com.cherrypicks.boc.model.SystemUserRoleMap;
import com.cherrypicks.boc.service.IAuthorizeService;

public class AuthorizeServiceImpl implements IAuthorizeService {

	@Autowired
	private ISystemUserDao systemUserDao;
	
	@Autowired
	private ISystemUserRoleMapDao userRoleMapDao;
	
	@Autowired
	private ISystemRoleDao systemRoleDao;
	
	@Autowired
	private ISystemFunctionDao functionDao;
	
	@Autowired
	private ISystemRoleFunctionMapDao roleFunctionMapDao;
	

	@Override
	public SystemUser findSystemUser(String userName) {
		if (null == userName) {
			throw new IllegalArgumentException();
		}
		return systemUserDao.findByUserName(userName);
	}
	

	@Override
	public SystemUser findSystemUserById(Object id) {
		if (null == id) {
			throw new IllegalArgumentException();
		}
		try {
			return systemUserDao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    return null;
	}
	
	@Override
	public List<SystemUser> findSystemUserByIds(List<Object> userIds) {
		if (null == userIds) {
			throw new IllegalArgumentException();
		}
		return systemUserDao.findByIds(userIds);
	}
	
	@Override
	public Long findSystemUserCountByRoleIds(List<Object> roleIds) {
		if (null == roleIds) {
			throw new IllegalArgumentException();
		}
		
		return userRoleMapDao.findSystemUserCountByRoleIds(roleIds);
	}
	
	@Override
	public List<SystemUser> findAllSystemUser(int...args) {
		return systemUserDao.findAll("createdTime", "DESC", args);
	}

	@Override
	public List<SystemUser> findSystemUserByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int...args) {
		if (null == criteriaMap) {
			throw new IllegalArgumentException();
		}
		return systemUserDao.findByFilter(criteriaMap,sortField,sortType,args);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemUser addSystemUser(SystemUser user, Object[] roles){
		if (null == user || null == roles) {
			throw new IllegalArgumentException();
		}
		try {
		user = systemUserDao.add(user);
		if (roles != null && roles.length > 0) {
			for (Object id : roles) {
				SystemUserRoleMap map = new SystemUserRoleMap();
				map.setUserId(user.getId());
				map.setRoleId(systemRoleDao.get(id).getId());
				map.setCreatedBy(user.getCreatedBy());
				map.setCreatedTime(user.getCreatedTime());
				userRoleMapDao.add(map);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemUser updateSystemUser(SystemUser user, Object[] roles) {
		if (null == user || null == roles) {
			throw new IllegalArgumentException();
		}
		try {
			user = systemUserDao.update(user);
			if (roles != null && roles.length > 0) {
				// delete old userRoleMap
				userRoleMapDao.deleteByUserId(user.getId());

				for (Object id : roles) {
					SystemUserRoleMap map = new SystemUserRoleMap();
					map.setUserId(user.getId());
					map.setRoleId(systemRoleDao.get(id).getId());
					map.setCreatedBy(user.getCreatedBy());
					map.setCreatedTime(user.getCreatedTime());
					map.setUpdatedBy(user.getUpdatedBy());
					map.setUpdatedTime(user.getUpdatedTime());
					userRoleMapDao.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemUser updateSystemUser(SystemUser user) {
		if (null == user) {
			throw new IllegalArgumentException();
		}
		try {
			return systemUserDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		    return null;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemUser changePassword(SystemUser user) {
		if (null == user) {
			throw new IllegalArgumentException();
		}
		try {
			return systemUserDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   return null;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeSystemUser(SystemUser user) {
		if (null == user) {
			throw new IllegalArgumentException();
		}
		try {
		List<SystemUserRoleMap> maps = userRoleMapDao.findUserRoleMapByUserId(user.getId());
		for (SystemUserRoleMap map : maps) {
			  userRoleMapDao.del(map.getId());
		}
		systemUserDao.del(user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer removeSystemUser(List<Object> userIds, String updatedBy) {
		if (null == userIds) {
			throw new IllegalArgumentException();
		}
		
//		userRoleMapDao.removeByUserIds(userIds, updatedBy);
//		return systemUserDao.removeByIds(userIds, updatedBy);
		
		userRoleMapDao.deleteByUserIds(userIds);
		return systemUserDao.deleteByIds(userIds);
	}
	
	@Override
	public List<SystemRole> findAllRole() {
		return systemRoleDao.findAll();
	}

	@Override
	public List<SystemRole> findAllRole(int...args) {
		return systemRoleDao.findAll("createdTime", "DESC", args);
	}
	
	@Override
	public List<SystemRole> findRoleByUserName(String userName) {
		if (null == userName) {
			throw new IllegalArgumentException();
		}
		return userRoleMapDao.findRoleByUserName(userName);
	}

	@Override
	public List<SystemRole> findRoleByUserId(Object userId) {
		if (null == userId) {
			throw new IllegalArgumentException();
		}
		return userRoleMapDao.findRoleByUserId(userId);
	}
	
	@Override
	public SystemRole findRoleById(Object roleId) {
		if (null == roleId) {
			throw new IllegalArgumentException();
		}
		try {
			return systemRoleDao.get(roleId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	@Override
	public List<SystemRole> findRoleByIds(List<Object> roleIds) {
		if (null == roleIds) {
			throw new IllegalArgumentException();
		}
		return systemRoleDao.findByIds(roleIds);
	}
	
	@Override
	public SystemRole findRoleByName(String roleName) {
		if (null == roleName) {
			throw new IllegalArgumentException();
		}
		return systemRoleDao.findByName(roleName);
	}

	@Override
	public List<SystemRole> findRoleByFilter(Map<String, Object> criteriaMap,String sortField, String sortType, int... args) {
		if (null == criteriaMap) {
			throw new IllegalArgumentException();
		}
		return systemRoleDao.findByFilter(criteriaMap,sortField,sortType, args);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemRole addRole(SystemRole role, Object[] functions) {
		if (null == role || null == functions) {
			throw new IllegalArgumentException();
		}
		
		role = systemRoleDao.add(role);
		if (functions != null && functions.length > 0) {
			for (Object funcId : functions) {
				SystemRoleFunctionMap map = new SystemRoleFunctionMap();
				map.setRoleId(role.getId());
				map.setFuncId(funcId);
				map.setCreatedBy(role.getCreatedBy());
				map.setCreatedTime(role.getCreatedTime());
				roleFunctionMapDao.add(map);
			}
		}
		return role;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SystemRole updateRole(SystemRole role, Object[] functions) {
		if (null == role || null == functions) {
			throw new IllegalArgumentException();
		}
		
		try {
			role = systemRoleDao.update(role);
		if (functions != null && functions.length > 0) {
			// delete old roleFunctionMap
			roleFunctionMapDao.deleteByRoleId(role.getId());
			
			for (Object funcId : functions) {
				SystemRoleFunctionMap map = new SystemRoleFunctionMap();
				map.setRoleId(role.getId());
				map.setFuncId(funcId);
				map.setCreatedBy(role.getCreatedBy());
				map.setCreatedTime(role.getCreatedTime());
				map.setUpdatedBy(role.getUpdatedBy());
				map.setUpdatedTime(role.getUpdatedTime());
				roleFunctionMapDao.add(map);
			}
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void removeRole(SystemRole role) {
		if (null == role) {
			throw new IllegalArgumentException();
		}
		try {
		List<SystemRoleFunctionMap> maps = roleFunctionMapDao.findByRoleId(role.getId());
		for (SystemRoleFunctionMap map : maps) {
			roleFunctionMapDao.del(map.getId());
		}
			systemRoleDao.del(role.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Integer removeRole(List<Object> roleIds, String updatedBy) {
		if (null == roleIds || roleIds.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
//		roleFunctionMapDao.removeByRoleIds(roleIds, updatedBy);
//		return systemRoleDao.removeByIds(roleIds, updatedBy);
		
		roleFunctionMapDao.deleteByRoleIds(roleIds);
		return systemRoleDao.deleteByIds(roleIds);
	}

	@Override
	public List<SystemFunction> findAllFunction() {
		return functionDao.findAll();
	}

	@Override
	public List<SystemFunction> findFunctionByUserName(String userName) {
		if (null == userName) {
			throw new IllegalArgumentException();
		}
		
		List<SystemFunction> funcList = new ArrayList<SystemFunction>();
		List<SystemRole> roleList = findRoleByUserName(userName);
		if (null != roleList) {
			for(SystemRole role : roleList) {
				funcList.addAll(roleFunctionMapDao.findFunctionByRoleId(role.getId()));
			}
		}
		return funcList;
	}
	
	@Override
	public List<SystemFunction> findFunctionByUserNameAndRoles(String userName, List<SystemRole> roleList) {
		if (null == userName || null == roleList) {
			throw new IllegalArgumentException();
		}
		
		List<SystemFunction> funcList = new ArrayList<SystemFunction>();
		if (null != roleList) {
			for(SystemRole role : roleList) {
				funcList.addAll(roleFunctionMapDao.findFunctionByRoleId(role.getId()));
			}
		}
		return funcList;
	}
	
	@Override
	public List<SystemFunction> findFunctionByRoleId(Object roleId) {
		if (null == roleId) {
			throw new IllegalArgumentException();
		}
		
		return roleFunctionMapDao.findFunctionByRoleId(roleId);
	}
	
	@Override
	public List<SystemFunction> findFunctionByRoleIds(Set<Object> roleIds) {
		if (null == roleIds) {
			throw new IllegalArgumentException();
		}
		
		return roleFunctionMapDao.findFunctionByRoleIds(roleIds);
	}
	
	@Override
	public List<SystemUserRoleMap> findUserRoleMapByUserId(Object userId) {
		if (null == userId) {
			throw new IllegalArgumentException();
		}
		
		return userRoleMapDao.findUserRoleMapByUserId(userId);
	}
	
	@Override
	public List<SystemUserRoleMap> findUserRoleMapByUserIds(Set<Object> userIds) {
		if (null == userIds) {
			throw new IllegalArgumentException();
		}
		
		return userRoleMapDao.findUserRoleMapByUserIds(userIds);
	}

	
	
}
