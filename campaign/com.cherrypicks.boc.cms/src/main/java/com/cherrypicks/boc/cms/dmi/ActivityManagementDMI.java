package com.cherrypicks.boc.cms.dmi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.vo.ActivityVo;
import com.cherrypicks.boc.model.Activity;
import com.cherrypicks.boc.model.ActivityAddress;
import com.cherrypicks.boc.service.IActivityAddressService;
import com.cherrypicks.boc.service.IActivityService;
import com.cherrypicks.boc.service.IBaseService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class ActivityManagementDMI extends AbstractDMI<Activity>{

	@Autowired
	private IActivityService activityService; 
	
	@Autowired
	private IActivityAddressService activityAddressService;
	
	@Resource(name="activityService")
	public void setBaseService(IBaseService<Activity> baseService){
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
		List<Activity> activityList = activityService.findAll();	
		List<ActivityVo> activityVoList =  convertToActivityVo(activityList);
		dsResponse.setData(activityVoList);
		return dsResponse;
	}
	public DSResponse add(DSRequest dsRequest, Activity record) {
		DSResponse dsResponse = new DSResponse();
		try{
			record.setCreatedTime(new Date());
			activityService.add(record);
			dsResponse.setData(record);
			dsResponse.setSuccess();
		}catch (Exception err){
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	public DSResponse update(DSRequest dsRequest, Activity record) {
		DSResponse dsResponse = new DSResponse();
		try {
			record.setUpdatedTime(new Date());
			record = activityService.update(record);
			ActivityVo activityVo = convertToActivityVo(record);
			dsResponse.setData(activityVo);
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
				Activity removeData = new Activity();
				removeData.setId(ids.get(0));
				activityService.delete(removeData);
				dsResponse.setData(removeData);
				dsResponse.setSuccess();
			}
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}

	/**
	 * 
	 * Not a good way to realize the need to improve
	 * 
	 * @param activityList
	 * 
	 * @return List<ActivityVo>
	 */
	private List<ActivityVo> convertToActivityVo(List<Activity> activityList){
		List<ActivityVo> activityVoList =new ArrayList<ActivityVo>();
		if(null ==activityList || activityList.isEmpty()){
			return activityVoList;
		}
		Set<Object> setId = new HashSet<Object>();
		Set<Object> setAddress = new HashSet<Object>();
		for(Activity activity : activityList){
			ActivityVo activiVo = new ActivityVo();
			BeanUtils.copyProperties(activity, activiVo);
			List<ActivityAddress> activityAddressList = activityAddressService.findActivityAddressListByActivity(activity);
			for(ActivityAddress activityAddress : activityAddressList){
				setId.add(activityAddress.getId());
				setAddress.add(activityAddress.getAddress());
			}
			activiVo.setActivityAddressIds(setId.toArray(new Long []{}));
			activiVo.setActivityAddresses(setAddress.toArray(new String [] {}));
			activityVoList.add(activiVo);
		}
		return activityVoList;
	}
	/**
	 * 
	 * Not a good way to realize the need to improve
	 * @param activity
	 * @return ActivityVo
	 */
	private ActivityVo convertToActivityVo(Activity activity){
		ActivityVo activityVo = new ActivityVo();
		if(activity ==null){
			return activityVo;
		}
		Set<Object> setId = new HashSet<Object>();
		Set<Object> setAddress = new HashSet<Object>();
		BeanUtils.copyProperties(activity, activityVo);
		List<ActivityAddress> activityAddressList = activityAddressService.findActivityAddressListByActivity(activity);
		for(ActivityAddress activityAddress : activityAddressList){
			setId.add(activityAddress.getId());
			setAddress.add(activityAddress.getAddress());
		}
		activityVo.setActivityAddressIds(setId.toArray(new Long []{}));
		activityVo.setActivityAddresses(setAddress.toArray(new String [] {}));
		
		return activityVo;
	}

}
