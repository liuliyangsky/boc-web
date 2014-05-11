/**
 * This class has been generated by Fast Code Eclipse Plugin 
 * For more information please go to http://fast-code.sourceforge.net/
 * @author : lenovo
 * Created : 11/14/2013
 */

package com.cherrypicks.boc.api.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import com.cherrypicks.boc.api.service.APITestService;
import com.cherrypicks.boc.common.util.SpringTest;
import com.cherrypicks.boc.model.ApiTest;

public class APITestServiceImplTest extends SpringTest {

	@Autowired
	private APITestService apiTestService;
	
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void count() throws SQLException {
		apiTestService.count();
	}
    @Test
    public void add() throws SQLException{
    	apiTestService.add("kelvin.tie");
    }
    
    @Test
    public void get() throws SQLException{
    	apiTestService.get(new Long(1));
    }
    
    @Test
    public void update() throws SQLException{
    	ApiTest test = new ApiTest();
    	test.setId(1);
		test.setName("kelvin update");
		apiTestService.update(test);
    }
}
