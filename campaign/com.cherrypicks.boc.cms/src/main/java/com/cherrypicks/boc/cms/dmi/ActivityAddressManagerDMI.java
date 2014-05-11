package com.cherrypicks.boc.cms.dmi;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.vo.SystemUserVo;
import com.cherrypicks.boc.model.ActivityAddress;
import com.cherrypicks.boc.service.IActivityAddressService;
import com.cherrypicks.boc.service.IBaseService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class ActivityAddressManagerDMI extends AbstractDMI<ActivityAddress>{
	@Autowired
	private  IActivityAddressService activityAddressService;
	
	@Resource(name="activityAddressService")
	public void setBaseService(IBaseService<ActivityAddress> baseService){
		this.baseService=baseService;
	}
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response) {
		DSResponse dsResponse = new DSResponse();
		int startRow = (int) dsRequest.getStartRow();
        int endRow = (int) dsRequest.getEndRow();
        int[] args = null;
		if (startRow >= 0 && endRow - startRow > 0) {
			args = new int[] {startRow, endRow - startRow};
		}
		String sortBy = dsRequest.getSortBy();
		String sortType = "ASC";
		if (StringUtils.isNotEmpty(sortBy)) {
			if (sortBy.startsWith("-")) {
				sortBy = sortBy.substring(1);
				sortType = "DESC";
			}
		}
		Map<String, Object> criteriaMap = getCriteriaMap(dsRequest);
		List<ActivityAddress> activityAddressList = activityAddressService.findByFilter(criteriaMap, null, sortType, args);
		dsResponse.setData(activityAddressList);
		return dsResponse;
	}
	public DSResponse add(DSRequest dsRequest, ActivityAddress record) {
		DSResponse dsResponse = new DSResponse();
		try{
			record = activityAddressService.add(record);
			dsResponse.setData(record);
			dsResponse.setSuccess();
		}catch (Exception err){
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	public DSResponse update(DSRequest dsRequest, SystemUserVo record) {
		DSResponse dsResponse = new DSResponse();
		try {
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	@SuppressWarnings("unchecked")
	public DSResponse remove(DSRequest dsRequest) {
		DSResponse dsResponse = new DSResponse();
		try {
			Map<String, List<Object>> criteria = dsRequest.getOldValues();
			List<Object> ids = (List<Object>) criteria.get("ids");
			if (null != ids) {
				ActivityAddress removeData = new ActivityAddress();
				removeData.setId(ids.get(0));
				activityAddressService.delete(removeData);
				dsResponse.setSuccess();
				dsResponse.setData(removeData);
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
}
