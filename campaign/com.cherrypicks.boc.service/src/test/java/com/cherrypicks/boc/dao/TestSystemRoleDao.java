package com.cherrypicks.boc.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cherrypicks.boc.dao.ISystemRoleDao;
import com.cherrypicks.boc.model.SystemRole;


@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSystemRoleDao {

	@Autowired
	private ISystemRoleDao systemRoleDao;
	
	@Test
	public void testFindAll() {
		List<SystemRole> list = systemRoleDao.findAll(0, 10);
		System.out.println(list.size());
	}
}
