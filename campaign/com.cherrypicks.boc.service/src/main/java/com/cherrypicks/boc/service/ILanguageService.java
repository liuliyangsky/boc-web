package com.cherrypicks.boc.service;

import java.util.List;

import com.cherrypicks.boc.model.Language;

public interface ILanguageService extends IBaseService<Language>{
	
	
	public Language findDefaultLang();
	
	public List<Language> findDisplayLang();

}
