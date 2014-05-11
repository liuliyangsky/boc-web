package com.cherrypicks.boc.api.web.view;

import java.io.Serializable;
import org.springframework.beans.BeanUtils;

import com.cherrypicks.boc.model.ApiTest;

public class TestVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3905260491292596422L;

	private Long id;
    
    private String name;
    
  
	public TestVO(ApiTest test) {
		BeanUtils.copyProperties(test, this);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
