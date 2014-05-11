package com.cherrypicks.boc.cms.vo;


public enum Status {
	Inactive(0), Active(1);


	private int code;

	private Status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Status get(int code) {
		for (Status status : values()) {
			if (status.code == code) return status;
		}
		throw new IllegalArgumentException("invalidate code value");
	}
}