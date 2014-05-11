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

public class APITestServiceImplTest extends SpringTest {

	@Autowired
	private APITestService apiTestService;
	

	/**
	 * 
	 * @throws SQLException 
	 * @see com.cherrypicks.cep.api.service.impl.UserServiceImpl#add(int,String)
	 */
	@Test
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void count() throws SQLException {
		apiTestService.count();
	}

}
