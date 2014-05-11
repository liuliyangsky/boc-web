package com.cherrypicks.boc.api.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cherrypicks.boc.api.dao.APITestDao;
import com.cherrypicks.boc.api.service.APITestService;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.model.ApiTest;

/**
 * 
 * @author kelvin.tie
 */
@Service
public class APITestServiceImpl extends Context implements APITestService {

	@Autowired
	private APITestDao apiTestDao;
	

	@Override
	public boolean add(String name) throws SQLException {

		ApiTest test = new ApiTest();
		test.setCreatedBy("TEST create");
		test.setCreatedTime(new Date());
		test.setIsDeleted(0);
		test.setName(name);

		return apiTestDao.add(test);
	}
	
	@Override
	public ApiTest get(Long id) throws SQLException {
		return apiTestDao.get(id);
	}
	
	@Override
	public boolean del(Long id) throws SQLException {
		
		return apiTestDao.del(id);
	}
	
	@Override
	public boolean delData(Long id) throws SQLException {
		
		return apiTestDao.delData(id);
	}

	@Override
	public Integer count() throws SQLException {

		return apiTestDao.count();
	}

	@Override
	public boolean update(ApiTest test) throws SQLException {
	
		return apiTestDao.update(test);
	}

	@Override
	public List<ApiTest> getAll() throws SQLException {

		return apiTestDao.getAll();
	}
}
