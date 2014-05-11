package com.cherrypicks.boc.cms.dmi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cherrypicks.boc.cms.constant.Const;
import com.cherrypicks.boc.cms.security.UserLoginHelper;
import com.cherrypicks.boc.common.model.AbstractEntity;
import com.cherrypicks.boc.dao.paging.PagingList;
import com.cherrypicks.boc.service.IBaseService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;


public abstract class AbstractDMI<T extends AbstractEntity> {
	protected Logger log = Logger.getLogger(AbstractDMI.class.getName());
	
	protected IBaseService<T> baseService;
	
	/**
	 * fetch data
	 * @param dsRequest
	 * @param request
	 * @param response
	 * @return
	 */
	protected DSResponse doFetch(DSRequest dsRequest, HttpServletRequest request, HttpServletResponse response) {
		log.debug("procesing fetch operation...");
		
		DSResponse dsResponse = new DSResponse();
		try{
			int startRow = (int)dsRequest.getStartRow();
			int endRow = (int)dsRequest.getEndRow();
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
			
	        List<T> resultList = new ArrayList<T>();
	        
	        Map<String, Object> criteriaMap = getCriteriaMap(dsRequest);
	        String searchType = (String) criteriaMap.get("searchType");
	        
			if (Const.FILTER_TYPE.equalsIgnoreCase(searchType)) {
	        	resultList = baseService.findByFilter(criteriaMap,sortBy,sortType, args);
			} else {
				resultList = baseService.findAll(sortBy,sortType,args);
			}
			
			dsResponse.setData(resultList);
			dsResponse.setSuccess();
			if (args != null && resultList instanceof PagingList) {
				PagingList<T> pagingResultList = (PagingList<T>)resultList;
				dsResponse.setStartRow(startRow);
				dsResponse.setEndRow(startRow + pagingResultList.size());
				dsResponse.setTotalRows(pagingResultList.getTotalRecords());
			}
        } catch(Exception e) {
        	dsResponse.setData(e.getMessage());
			dsResponse.setFailure();
        }
		return dsResponse;
	}
	
	/**
	 * save data
	 * @param dsRequest
	 * @param record
	 * @return
	 */
	protected DSResponse doAdd(T record) {
		log.debug("procesing add operation...");
		
		DSResponse dsResponse = new DSResponse();
		try {
			record.setCreatedBy(UserLoginHelper.getLoginUserName());
			record.setCreatedTime(new Date());
			record = baseService.add(record);
			dsResponse.setData(record);
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	/**
	 * update data
	 * @param dsRequest
	 * @param record
	 * @return
	 */
	public DSResponse doUpdate(T record) {
		log.debug("procesing update operation...");
		
		DSResponse dsResponse = new DSResponse();
		try {
			
			record.setUpdatedBy(UserLoginHelper.getLoginUserName());
			record.setUpdatedTime(new Date());
			record = baseService.update(record);
			dsResponse.setData(record);
			dsResponse.setSuccess();
		} catch (Exception err) {
			log.error(err.getMessage(), err);
			dsResponse.setFailure();
			dsResponse.setData(err.getMessage());
		}
		return dsResponse;
	}
	
	/**
	 * remove data by ids
	 * @param dsRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DSResponse doRemove(DSRequest dsRequest) {
		log.debug("procesing remove operation...");
		
		DSResponse dsResponse = new DSResponse();
		try {
			Map<String, List<Object>> criteria = dsRequest.getOldValues();
			List<Object> ids = (List<Object>) criteria.get("ids");
			if (null != ids) {
				List<T> removeData = baseService.findByIds(ids);
				baseService.remove(ids, UserLoginHelper.getLoginUserName());
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
	
	/**
	 * Get CriteriaMap for dateTime type
	 * @param criteriaMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getCriteriaMap(DSRequest dsRequest) {
		String searchType = (String) dsRequest.getValues().get("searchType");
        Map<String, Object> criteriaMap = dsRequest.getCriteria();
		if (null != criteriaMap && criteriaMap.size() > 0) {
			if (null != criteriaMap.get("criteria")) {
				if (null == searchType) {
					searchType = Const.FILTER_TYPE;
					criteriaMap.put("searchType", searchType);
				}
			}
		}
		
		if (Const.FILTER_TYPE.equalsIgnoreCase(searchType)) {
    		if (null != criteriaMap && criteriaMap.size() > 0) {
    			if (null != criteriaMap.get("criteria")) {
    				List<Map<String, Object>> criteriaList = (List<Map<String, Object>>) criteriaMap.get("criteria");
    				for (Map<String, Object> obj : criteriaList) {
    					if (null != obj.get("criteria")) {
    						List<Map<String, Object>> timeCriteriaList = (List<Map<String, Object>>) obj.get("criteria");
    						for (Map<String, Object> timeObj : timeCriteriaList) {
    							String fieldName = (String) timeObj.get("fieldName");
        						String operator = (String) timeObj.get("operator");
        						Object value = timeObj.get("value");
        						if (value instanceof Date) {
        							if ("greaterOrEqual".equals(operator)) {
        								criteriaMap.put(fieldName + "FromDate", value);
        							} else if ("lessOrEqual".equals(operator)) {
        								criteriaMap.put(fieldName + "ToDate", value);
        							} else {
        								criteriaMap.put(fieldName, value);
        							}
        						} else {
        							criteriaMap.put(fieldName, value);
        						}
    						}
    					} else {
    						String fieldName = (String) obj.get("fieldName");
    						String operator = (String) obj.get("operator");
    						Object value = obj.get("value");
    						if (value instanceof Date) {
    							if ("greaterOrEqual".equals(operator)) {
    								criteriaMap.put(fieldName + "FromDate", value);
    							} else if ("lessOrEqual".equals(operator)) {
    								criteriaMap.put(fieldName + "ToDate", value);
    							} else {
    								criteriaMap.put(fieldName, value);
    							}
    						} else {
    							criteriaMap.put(fieldName, value);
    						}
    					}
    				}
    			} 
    		}
		}
    		
		return criteriaMap;
	}

	public IBaseService<T> getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService<T> baseService) {
		this.baseService = baseService;
	}
}
