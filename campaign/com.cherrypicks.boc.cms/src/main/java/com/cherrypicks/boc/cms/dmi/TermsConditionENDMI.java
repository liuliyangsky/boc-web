package com.cherrypicks.boc.cms.dmi;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.cherrypicks.boc.utils.FileUtil;
import com.cherrypicks.boc.utils.PropertiesUtil;
import com.isomorphic.datasource.BasicDataSource;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.servlet.ISCFileItem;

public class TermsConditionENDMI extends BasicDataSource {
	private static final long serialVersionUID = 8981193072447870139L;
	
	private static final String TERMS_CONDITION_EN = "terms.condition.file.en";

	private byte[] fileData;
    
	@SuppressWarnings("unchecked")
	@Override
    public DSResponse execute(DSRequest dsRequest) {
        DSResponse dsResponse = new DSResponse(this);
        try {
	        byte[] bytes = null;
	        ISCFileItem item = null;
	        
	        Map<String, Object> result = dsRequest.getCriteria();
	        String operationType = dsRequest.getOperationType();
	        if (operationType.equals("fetch")) {
	            dsResponse = this.executeFetch(dsRequest);
	        } else if (operationType.equals("viewFile") || operationType.equals("downloadFile")) {
	        		final String binaryResponse = this.getBinaryResponse(TERMS_CONDITION_EN);
	        		bytes = binaryResponse != null ? binaryResponse.getBytes() : null;
	        		result.put("file_en", bytes);
	        		result.put("file_en_filesize", (bytes == null) ? 0 : bytes.length);
	        	    dsResponse.setData(result);
	        } else if (operationType.equals("add") || operationType.equals("update")) {
	            
	            item = dsRequest.getUploadedFile("newFile_en");
	           
	            if (null != item) {
	            	fileData = item.get();
	            } 
	            
	            dsResponse = this.executeUpdate(dsRequest);
	        }
        } catch(Exception e) {
        	dsResponse.setData(e.getMessage());
			dsResponse.setFailure();
        }
        return dsResponse;
    }
    
    @Override
    public DSResponse executeFetch(DSRequest dsRequest) throws Exception {
        DSResponse dsResponse = new DSResponse(this);
        // read content from server URL if exists
        Map<String, Object> mapRecord = new HashMap<String, Object>();
        mapRecord.put("id", 1);   
        
        // read  file
        String desc = getBinaryResponse(TERMS_CONDITION_EN);
        if (null != desc) {
        	final String file = PropertiesUtil.getProperty(TERMS_CONDITION_EN);
	        if (StringUtils.isEmpty(file)) {
	        	throw new IllegalArgumentException("Can not find \"terms.condition.file\" in config file.");
	        }
	        mapRecord.put("file_en", desc.getBytes());
	        mapRecord.put("file_en_filename", file);
	        mapRecord.put("file_en_filesize", (desc.getBytes()).length);
	        mapRecord.put("file_en_date_created", new Date());
	        mapRecord.put("file_en_description", desc);
        }
        dsResponse.setData(mapRecord);
        dsResponse.setSuccess();
        return dsResponse;  
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public DSResponse executeUpdate(DSRequest dsRequest) throws Exception {
        DSResponse dsResponse = new DSResponse(this);
        // save content to server file
        final String folderPath = PropertiesUtil.getProperty("document.path");
        if (StringUtils.isEmpty(folderPath)) {
        	throw new IllegalArgumentException("Can not find \"document.path\" in config file.");
        }
        File docFolderPath = new File(folderPath);
        if (!docFolderPath.exists()) {
        	docFolderPath.mkdirs();
		}
        
        Map<String, Object> updateValues = dsRequest.getValues();
        updateValues.put("id", 1); 
        
        if (null != fileData) {
	        final String fileName = PropertiesUtil.getProperty(TERMS_CONDITION_EN);
	        if (StringUtils.isEmpty(fileName)) {
	        	throw new IllegalArgumentException("Can not find \"terms.condition.file\" in config file.");
	        }
	        
	        FileUtil.delete(folderPath, fileName);
	        File filePath = new File(folderPath + fileName);
	        FileUtils.writeByteArrayToFile(filePath, fileData);
	        
	        // set content to file field
	        updateValues.put("file_en", fileData);
	        updateValues.put("file_en_filename", fileName);
	        updateValues.put("file_en_filesize", fileData.length);
	        updateValues.put("file_en_date_created", new Date());
        }
        dsResponse.setData(updateValues);
        dsResponse.setSuccess();
        
        return dsResponse;
    }
    
    
    private String getBinaryResponse(String fileName) throws Exception {
        String filePath = PropertiesUtil.getProperty("document.path");
        if (StringUtils.isEmpty(filePath)) {
        	throw new IllegalArgumentException("Can not find \"document.path\" in config file.");
        }
        
        final String file = PropertiesUtil.getProperty(fileName);
        if (StringUtils.isEmpty(file)) {
        	throw new IllegalArgumentException("Can not find \"document.path\" in config file.");
        }
        
        return FileUtil.read(filePath, file);
    }
}
