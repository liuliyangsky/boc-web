package com.cherrypicks.boc.common.jms;

public interface ReceiveMessageHandler<RECEIVE> {
	
	public void processNoReturn(RECEIVE obj);
}
