package com.cherrypicks.boc.dao.paging;

import java.util.ArrayList;


public class ArrayPagingList<E> extends ArrayList<E> implements PagingList<E> {

	private static final long serialVersionUID = 2325387314116470195L;

	private int startRecord;
	
	private int maxRecords;
	
	private long totalRecords;
	
	private int page;

	private int totalPages;
	
	public int getStartRecord() {
		return startRecord;
	}

	public void setStartRecord(int startRecord) {
		this.startRecord = startRecord;
	}

	public int getMaxRecords() {
		return maxRecords;
	}

	public void setMaxRecords(int maxRecords) {
		this.maxRecords = maxRecords;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
