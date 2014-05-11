package com.cherrypicks.boc.api.web.view;

import java.io.Serializable;
import java.util.Set;

public class SensitiveWordsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7837280143826451014L;
	/* 敏感詞  */
	private Set<String> sensitiveWords;
	/* 過濾有的字符串 */
	private String filterText;
	public Set<String> getSensitiveWords() {
		return sensitiveWords;
	}
	public void setSensitiveWords(Set<String> sensitiveWords) {
		this.sensitiveWords = sensitiveWords;
	}
	public String getFilterText() {
		return filterText;
	}
	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}
	
}
