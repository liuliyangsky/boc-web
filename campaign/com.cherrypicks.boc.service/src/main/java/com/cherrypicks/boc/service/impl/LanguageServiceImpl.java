package com.cherrypicks.boc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.cherrypicks.boc.dao.IBaseDao;
import com.cherrypicks.boc.dao.ILanguageDao;
import com.cherrypicks.boc.model.Language;
import com.cherrypicks.boc.service.ILanguageService;

public class LanguageServiceImpl extends AbstractBaseService<Language> implements ILanguageService{
	
	@Autowired
	private ILanguageDao languageDao;
	
	@Resource(name="languageDao")
	public void setBaseDao(IBaseDao<Language> baseDao) {
		this.baseDao = baseDao;
	}

	public Language findDefaultLang() {
		
		return languageDao.findDefaultLang();
	}

	public List<Language> findDisplayLang() {
		
		return languageDao.findAll();
	}
	
}
