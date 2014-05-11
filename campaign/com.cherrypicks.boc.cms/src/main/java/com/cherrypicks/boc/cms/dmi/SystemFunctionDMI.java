package com.cherrypicks.boc.cms.dmi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.model.SystemFunction;
import com.cherrypicks.boc.service.IAuthorizeService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class SystemFunctionDMI {
	@Autowired
	private IAuthorizeService authorizeService;
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response) {
		long startRow = dsRequest.getStartRow();
        long endRow = dsRequest.getEndRow();
        
        List<SystemFunction> lst = authorizeService.findAllFunction();
        long totalRows = lst.size();
		DSResponse dsResponse = new DSResponse();
		dsResponse.setTotalRows(totalRows);
		dsResponse.setStartRow(startRow);
        endRow = Math.min(endRow, totalRows);
        dsResponse.setEndRow(endRow);
		dsResponse.setData(lst);
		return dsResponse;
	}

}
