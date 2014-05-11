package com.cherrypicks.boc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * Parse a string and return the date value in the specified format
	 * 
	 * @param strFormat
	 * @param dateValue
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Date parseDate(String strFormat, String dateValue) {
		if (dateValue == null)
			return null;

		if (strFormat == null)
			strFormat = TIME_PATTERN_DEFAULT;

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}
		return newDate;
	}
	
	/**
	 * Parse a strign and return a datetime value
	 * 
	 * @param dateValue
	 * @return format(yyyy-MM-dd HH:mm:ss)
	 */
	public static Date parseDateTime(String dateValue) {
		return parseDate(TIME_PATTERN_DEFAULT, dateValue);
	}

	
	public static Date getDateWithoutTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}
	
	public static Date getDateWithTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    return cal.getTime();
	}
	
	public static String getDateTimeWithoutSeconds(Date date) {
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM", date);
	}
	
	public static String getDateStart(Date date) {
		String dateStr = parseDate2(date, "yyyy-MM-dd");
		dateStr = dateStr + " 00:00:00";
		return dateStr;
	}
	
	public static String parseDate2(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}
	
	public static String getMonthDay(Date date) {
		return String.format("%1$tm-%1$td", date);
	}
	
	public static String getHHMM(Date date){
		return String.format("%1$tH:%1$tM", date);
		
	}
	
	public static Date getBeforeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return calendar.getTime();
	}

}
