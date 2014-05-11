package com.cherrypicks.boc.api.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.api.dao.APITestDao;
import com.cherrypicks.boc.api.dao.db2.APITestDaoDB2;
import com.cherrypicks.boc.common.Context;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.ApiTest;

/**
 * 
 * @author kelvin.tie
 */
@Repository
public class APITestDaoImpl extends Context implements APITestDao  {

	@Autowired
	private APITestDaoDB2 apiTestDaoDB2;
/*	@Autowired
	private APITestDaoRedis apiTestDaoRedis;*/
	
	@Override
	public boolean add(ApiTest test) throws SQLException{

		test = apiTestDaoDB2.add(test,DBSchemaEnum.BOC_CAMPAIGN.toString());
/*		if (test != null) {
			apiTestDaoRedis.add(test);
		}*/

		return true;
	}

	@Override
	public ApiTest get(Long id) throws SQLException {

/*		ApiTest test = apiTestDaoRedis.get(id);
		if (test != null) {
			return test;
		}*/

		ApiTest test = apiTestDaoDB2.get(id,DBSchemaEnum.BOC_CAMPAIGN.toString());
/*		if (test != null) {
			apiTestDaoRedis.add(test);
		}*/

		return test;
	}

	@Override
	public boolean del(Long id) throws SQLException {
		
		boolean del = apiTestDaoDB2.del(id,DBSchemaEnum.BOC_CAMPAIGN.toString());
/*		if (del) {
			apiTestDaoRedis.del(id);
		}*/

		return del;
	}

	@Override
	public boolean delData(Long id) throws SQLException {
		boolean del = apiTestDaoDB2.delData(id,DBSchemaEnum.BOC_CAMPAIGN.toString());
	/*	if (del) {
			
			apiTestDaoRedis.del(id);
		}*/

		return del;
	}

	@Override
	public boolean update(ApiTest test) throws SQLException {
		boolean update = apiTestDaoDB2.update(test,DBSchemaEnum.BOC_CAMPAIGN.toString());
/*		if (update) {
			apiTestDaoRedis.update(test);
		}*/

		return update;
	}

	@Override
	public Integer count() throws SQLException {
		
		return apiTestDaoDB2.getTest();
	}

	@Override
	public List<ApiTest> getAll() throws SQLException {

		return apiTestDaoDB2.findAll(DBSchemaEnum.BOC_CAMPAIGN.toString());
	}

}
