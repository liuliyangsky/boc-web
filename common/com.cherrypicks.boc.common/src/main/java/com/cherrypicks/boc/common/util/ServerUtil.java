package com.cherrypicks.boc.common.util;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServerUtil {
	private static Log logger = LogFactory.getLog(ServerUtil.class);

	public static void changeDate(Date toDate) throws IOException {
		// Operating system name
		String osName = System.getProperty("os.name");
		String cmd = "";
		try {
			if (osName.matches("^(?i)Windows.*$")) {// Window 系统
				// 格式：yyyy-MM-dd
				String date = DateUtil.format(toDate, "yyyy-MM-dd");
				cmd = " cmd /c date " + date;
				logger.info("changeDate:" + cmd);
				Runtime.getRuntime().exec(cmd);
			} else {// Linux 系统
				Date currentDate = DateUtil.getCurrentDate();
				// 格式：yyyyMMdd
				String date = DateUtil.format(toDate, "yyyy-MM-dd");
				// 格式 HH:mm:ss
				String time = DateUtil.format(currentDate, "HH:mm:ss");
				cmd = "  /bin/date --s \'" + date + " " + time + "\'";
				logger.info("changeDate:" + cmd);
				String[] comands = new String[] { "/bin/sh", "-c", cmd };
				Runtime.getRuntime().exec(comands);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void changeDateTime(Date toTime) throws IOException {
		// Operating system name
		String osName = System.getProperty("os.name");
		String cmd = "";
		try {
			if (osName.matches("^(?i)Windows.*$")) {// Window 系统
				// 格式 HH:mm:ss
				String time = DateUtil.format(toTime, "HH:mm:ss");
				cmd = "  cmd /c time " + time;
				Runtime.getRuntime().exec(cmd);
				// 格式：yyyy-MM-dd
				String date = DateUtil.format(toTime, "yyyy-MM-dd");
				cmd = " cmd /c date " + date;
				logger.info("changeDateTime:" + cmd);
				Runtime.getRuntime().exec(cmd);
			} else {// Linux 系统
				// 格式：yyyyMMdd HH:mm:ss
				String dateTime = DateUtil.format(toTime, "yyyy-MM-dd HH:mm:ss");
				cmd = "  /bin/date --s \'" + dateTime + "\'";
				logger.info("changeDateTime:" + cmd);
				String[] comands = new String[] { "/bin/sh", "-c", cmd };
				Runtime.getRuntime().exec(comands);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		Date nextDate = DateUtil.addDays(DateUtil.getCurrentDate(), -1);
		try {
			ServerUtil.changeDateTime(nextDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
