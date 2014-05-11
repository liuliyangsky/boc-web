package com.cherrypicks.boc.dao.paging;

import java.util.List;

public interface PagingList<E> extends List<E> {

	public int getStartRecord();
	
	public void setStartRecord(int startRecord);
	
	public int getMaxRecords();
	
	public void setMaxRecords(int maxRecords);
	
	public long getTotalRecords();
	
	public void setTotalRecords(long totalRecords);
	
	public int getPage();
	
	public void setPage(int page);

	public int getTotalPages();
	
	public void setTotalPages(int totalPages);
}
