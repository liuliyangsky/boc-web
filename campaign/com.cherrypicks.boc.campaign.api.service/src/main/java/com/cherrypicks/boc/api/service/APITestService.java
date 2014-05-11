package com.cherrypicks.boc.api.service;

import java.sql.SQLException;
import java.util.List;

import com.cherrypicks.boc.model.ApiTest;

/**
 * 
 * @author kelvin.tie
 */
public interface APITestService {

	public boolean add(String Name) throws SQLException;

	public ApiTest get(Long id) throws SQLException;
	
	public boolean update(ApiTest test) throws SQLException;
	
	public boolean del(Long id) throws SQLException;
	
	public boolean delData(Long id) throws SQLException;
	
	public Integer count()throws SQLException;

	public List<ApiTest> getAll()throws SQLException;
}
