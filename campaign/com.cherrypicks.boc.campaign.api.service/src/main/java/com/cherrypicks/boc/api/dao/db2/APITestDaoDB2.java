package com.cherrypicks.boc.api.dao.db2;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.db.schema.DBSchemaEnum;
import com.cherrypicks.boc.model.ApiTest;

/**
 * 
 * @author kelvin.tie
 */
@Repository
public class APITestDaoDB2 extends AbstractBaseDaoDB2<ApiTest> {
	
	public Integer getTest() throws SQLException{
		StatementParameter param=new StatementParameter();
		param.setObject(new Long(1));
		List<ApiTest> a=this.queryForList("select * from "+DBSchemaEnum.BOC_CAMPAIGN+".api_test where id=?", ApiTest.class,param );
		System.out.println("success a="+a);
		
		return null;
	}
}
