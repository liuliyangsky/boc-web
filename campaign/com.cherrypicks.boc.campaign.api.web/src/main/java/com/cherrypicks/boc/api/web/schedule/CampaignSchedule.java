package com.cherrypicks.boc.api.web.schedule;

import java.sql.SQLException;

import com.cherrypicks.boc.common.util.Constant;
import com.cherrypicks.boc.common.util.PropertiesUtil;

public class CampaignSchedule {
	
	private static final String EXECUTE_SCHEDULE = PropertiesUtil.getProperty(Constant.EXECUTE_SCHEDULE);
	
	/**
	 * 開始運行
	 * 根據服務器時間 update task status;
	 * @throws SQLException 
	 */
	public void updateTaskStatus() throws SQLException{
		if(!EXECUTE_SCHEDULE.equals("true")){
			return;
		}

	}
}
