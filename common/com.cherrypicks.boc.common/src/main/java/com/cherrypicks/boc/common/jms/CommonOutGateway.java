package com.cherrypicks.boc.common.jms;

import org.springframework.integration.annotation.Gateway;

public interface CommonOutGateway<SENDTYPE> {

	@Gateway
	public void send(SENDTYPE obj);
}
