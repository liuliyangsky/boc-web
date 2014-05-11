package com.cherrypicks.boc.dao;

import com.cherrypicks.boc.model.Language;

public interface ILanguageDao extends IBaseDao<Language>{
	
	
	public Language findDefaultLang();
	
}
