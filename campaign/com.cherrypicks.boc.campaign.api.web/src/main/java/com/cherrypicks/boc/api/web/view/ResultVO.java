package com.cherrypicks.boc.api.web.view;

import com.cherrypicks.boc.common.util.CodeStatus;

public class ResultVO<T> {

	private int errorCode = CodeStatus.SUCCESS;
	private String errorMessage = "";
	
	private T result;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
