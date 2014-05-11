package com.cherrypicks.boc.model;

import com.cherrypicks.boc.common.model.AbstractEntity;


public class ApiTest extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2730828688412270731L;
	private String Name;

	public ApiTest(){

	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	

}
