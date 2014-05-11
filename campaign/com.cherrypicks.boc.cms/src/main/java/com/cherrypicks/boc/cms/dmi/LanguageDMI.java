package com.cherrypicks.boc.cms.dmi;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.cms.vo.LanguageVo;
import com.cherrypicks.boc.model.Language;
import com.cherrypicks.boc.service.IBaseService;
import com.cherrypicks.boc.service.ILanguageService;
import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;

public class LanguageDMI extends AbstractDMI<Language>{
	private Logger log = Logger.getLogger(LanguageDMI.class.getName());
	
	@Autowired
	private ILanguageService languageService;
	
	@Resource(name="languageService")
	public void setBaseSerivce(IBaseService<Language> baseService){
		this.baseService = baseService;
	}
	
	public DSResponse fetch(DSRequest dsRequest, HttpServletRequest request,
			HttpServletResponse response, LanguageVo record) {
		log.info("procesing DMI fetch operation");
		long startRow = dsRequest.getStartRow();
        long endRow = dsRequest.getEndRow();
        
        List<Language> languageList = new ArrayList<Language>();
        List<LanguageVo> languageVoList = new ArrayList<LanguageVo>();
        String searchType = (String) dsRequest.getValues().get("searchType");

        if ("defaultLanguage".equals(searchType)) {
        	Language defaultLang = languageService.findDefaultLang();
        	languageList.add(defaultLang);
        } else {
	        languageList = languageService.findDisplayLang();
        }
        
        // convert language to languageVo
        if (!languageList.isEmpty()) {
        	for (Language language : languageList) {
        		LanguageVo languageVo = createLanguageVo(language);
        		if (languageVo != null)
        			languageVoList.add(languageVo);
        	}
        }
        
        long totalRows = languageVoList.size();
		DSResponse dsResponse = new DSResponse();
		dsResponse.setTotalRows(totalRows);
		dsResponse.setStartRow(startRow);
        endRow = Math.min(endRow, totalRows);
        dsResponse.setEndRow(endRow);
		dsResponse.setData(languageVoList);
		return dsResponse;
	}
	
	/**
	 * Convert Language to LanguageVo
	 * @param language
	 * @return
	 */
	private LanguageVo createLanguageVo(Language language) {
		LanguageVo vo = null;
		if (language != null) {
			vo = new LanguageVo();
			BeanUtils.copyProperties(language, vo);
			vo.setImg(getIconPath(language.getCode()));
		}
		return vo;
	}
	private String getIconPath(String code){
		if("en-US".equals(code)){
			return "US.png";
		}else if("zh-CN".equals(code)){
			return "CH.png";
		}else if("zh-HK".equals(code)){
			return "HK.png";
		}else{
			return "US.png";
		}
	}
}
