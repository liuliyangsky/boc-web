package com.cherrypicks.boc.dao.impl;

import java.util.List;
import java.util.Map;

import com.cherrypicks.boc.common.jdbc.StatementParameter;
import com.cherrypicks.boc.dao.ILanguageDao;
import com.cherrypicks.boc.model.Language;

public class LanguageDaoImpl extends AbstractBaseDaoDB2<Language> implements ILanguageDao{
	
	
	public List<Language> findByFilter(Map<String, Object> criteriaMap,
			String sortField, String sortType, int... args) {
		// TODO Auto-generated method stub
		return null;
	}

	public Language findDefaultLang() {
		String sql ="SELECT * FROM BOC_CAMPAIGN.LANGUAGE WHERE IS_DEFAULT = ?";
		StatementParameter param = new  StatementParameter();
		param.setInt(1);
		return query(sql, Language.class, param);
	}
}
