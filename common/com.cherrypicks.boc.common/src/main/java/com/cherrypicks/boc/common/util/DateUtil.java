package com.cherrypicks.boc.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 日期转换工具
 */
public class DateUtil {
	public static final String DATE_DIVISION = "-";
	public static final String TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
	public static final String DATE_PATTERN_YYYYMMDD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String DATA_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";

	public static final int ONE_SECOND = 1000;
	public static final int ONE_MINUTE = 60 * ONE_SECOND;
	public static final int ONE_HOUR = 60 * ONE_MINUTE;
	public static final long ONE_DAY = 24 * ONE_HOUR;

	/**
	 * Return the current date
	 * 
	 * @return － DATE<br>
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return currDate;
	}

	/**
	 * Return the current date string
	 * 
	 * @return － 产生的日期字符串<br>
	 */
	public static String getCurrentDateStr() {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return format(currDate);
	}

	/**
	 * Return the current date in the specified format
	 * 
	 * @param strFormat
	 * @return
	 */
	public static String getCurrentDateStr(String strFormat) {
		Calendar cal = Calendar.getInstance();
		Date currDate = cal.getTime();

		return format(currDate, strFormat);
	}

	public static synchronized Date getTime(final long millis) {
		Date date = new Date();
		if (millis > 0) {
			date.setTime(millis);
		}
		return date;
	}

	/**
	 * Parse a string and return a date value
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static Date parseDate(String dateValue) {
		return parseDate(DATE_PATTERN_DEFAULT, dateValue);
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
	 * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 *            需要转换的日期。
	 * @return 转换后符合给定格式的日期字符串
	 */
	public static String format(Date aTs_Datetime) {
		return format(aTs_Datetime, DATE_PATTERN_DEFAULT);
	}

	/**
	 * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 *            需要转换的日期。
	 * @return 转换后符合给定格式的日期字符串
	 */
	public static String formatTime(Date aTs_Datetime) {
		return format(aTs_Datetime, TIME_PATTERN_DEFAULT);
	}

	/**
	 * 将Date类型的日期转换为系统参数定义的格式的字符串。
	 * 
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 */
	public static String format(Date aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);

		return dateFromat.format(aTs_Datetime);
	}

	/**
	 * @param aTs_Datetime
	 * @param as_Format
	 * @return
	 */
	public static String formatTime(Date aTs_Datetime, String as_Format) {
		if (aTs_Datetime == null || as_Format == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Format);

		return dateFromat.format(aTs_Datetime);
	}

	public static String getFormatTime(Date dateTime) {
		return formatTime(dateTime, TIME_PATTERN_HHMMSS);
	}

	/**
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 */
	public static String format(Timestamp aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;
		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);

		return dateFromat.format(aTs_Datetime);
	}

	/**
	 * 取得指定日期N天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, days);

		return cal.getTime();
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 计算当前日期相对于"1977-12-01"的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long getRelativeDays(Date date) {
		Date relativeDate = DateUtil.parseDate("yyyy-MM-dd", "1977-12-01");

		return DateUtil.daysBetween(relativeDate, date);
	}

	public static Date getDateBeforTwelveMonth() {
		String date = "";
		Calendar cla = Calendar.getInstance();
		cla.setTime(getCurrentDate());
		int year = cla.get(Calendar.YEAR) - 1;
		int month = cla.get(Calendar.MONTH) + 1;
		if (month > 9) {
			date = String.valueOf(year) + DATE_DIVISION + String.valueOf(month) + DATE_DIVISION + "01";
		} else {
			date = String.valueOf(year) + DATE_DIVISION + "0" + String.valueOf(month) + DATE_DIVISION + "01";
		}

		Date dateBefore = parseDate(date);
		return dateBefore;
	}

	/**
	 * 传入时间字符串,加一天后返回Date
	 * 
	 * @param date
	 *            时间 格式 YYYY-MM-DD
	 * @return
	 */
	public static Date addDate(String date) {
		if (date == null) {
			return null;
		}
		Date tempDate = parseDate(DATE_PATTERN_DEFAULT, date);
		String year = format(tempDate, "yyyy");
		String month = format(tempDate, "MM");
		String day = format(tempDate, "dd");

		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

		calendar.add(GregorianCalendar.DATE, 1);
		return calendar.getTime();
	}

	public static Date getDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, num); // 设置为前/后一天

		return calendar.getTime();
	}

	public static String getDateToString(Date date, int num, String pattern) {
		Date d = getDate(date, num);
		return parseDateToString(d, pattern);
	}

	public static String getDateByMonthToString(Date date, int num, String pattern) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(Calendar.MONTH, num); // 设置为前/后一个月
		return parseDateToString(calendar.getTime(), pattern);
	}

	public static Date getDateByHour(Date date, int num) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(date);// 把当前时间赋给日历
		calendar.add(Calendar.HOUR, num); // 设置为前/后小时

		return calendar.getTime();
	}

	public static Date parseDate(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		String dateStr = df.format(date);
		return parseDate(pattern, dateStr);
	}

	public static String parseDateToString(Date date, String pattern) {
		Date d = parseDate(date, pattern);
		DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);

		return df.format(d);
	}

	public static String parseDateToString(Date date, String pattern, Locale locale) {
		Date d = parseDate(date, pattern);
		DateFormat df = new SimpleDateFormat(pattern, locale);

		return df.format(d);
	}

	public static String getAMNNPNStr(Date date, Locale locale) {

		Date date1 = parseDate(date, "HH:mm");
		Date date2 = parseDate("HH:mm", "12:00");
		Date date3 = parseDate("HH:mm", "12:59");

		if (date2.getTime() <= date1.getTime() && date1.getTime() <= date3.getTime()) {
			if (StringUtils.equals(locale.getLanguage(), Locale.ENGLISH.getLanguage())) {
				return "NN";
			}
			if (StringUtils.equals(locale.getLanguage(), Locale.CHINESE.getLanguage())) {
				return "中午";
			}
		}

		return parseDateToString(date, "a", locale);
	}

	public static Date getDate() {
		return new Date();
	}

	/**
	 * 获取某日期是几号
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		return today;
	}

	/**
	 * 获取某日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekString(Date date) {
		int dayOfWeek = getDayOfWeek(date);
		switch (dayOfWeek) {
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		}
		return null;
	}

	/**
	 * 获取某日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekShortString(Date date) {
		int dayOfWeek = getDayOfWeek(date);
		switch (dayOfWeek) {
		case 1:
			return "Sun";
		case 2:
			return "Mon";
		case 3:
			return "Tue";
		case 4:
			return "Wed";
		case 5:
			return "Thu";
		case 6:
			return "Fri";
		case 7:
			return "Sat";
		}
		return null;
	}

	public static Date addWeeks(Date date, int num) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.WEEK_OF_MONTH, num);

		return cal.getTime();
	}

	public static Date addHours(Date date, int num) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.HOUR_OF_DAY, num);

		return cal.getTime();
	}

	public static Date addMonths(Date date, int num) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.MONTH, num);

		return cal.getTime();
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static int getMins(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 
	 * @param date
	 * @return k->yyyy-MM-dd,v->MMM
	 */
	public static Map<String, String> getMonthOfYearMap(Date date) {
		Map<String, String> dateMap = new LinkedHashMap<String, String>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);

		Date d = calendar.getTime(); // first month of year

		for (int i = 0; i < 12; i++) {
			Date resultDate = DateUtil.addMonths(d, i);

			String key = DateUtil.format(resultDate, "yyyy-MM");

			DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);
			String value = df.format(resultDate);

			dateMap.put(key, value);
		}

		return dateMap;
	}

	public static String parseDate2(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static Date getDateStart(Date date) {
		String dateStr = parseDate2(date, "yyyy-MM-dd");
		dateStr = dateStr + " 00:00:00";
		return parseDateTime(dateStr);
	}

	public static Date getDateEnd(Date date) {
		String dateStr = parseDate2(date, "yyyy-MM-dd");
		dateStr = dateStr + " 23:59:59";
		return parseDateTime(dateStr);
	}

	public static int get24Hours(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.HOUR_OF_DAY);
	}

	public static int get12Hours(Date date) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		return c.get(Calendar.HOUR);
	}
	
	public static Date getAfterDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
		return calendar.getTime();
	}

	public static Date getBeforeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
		return calendar.getTime();
	}
	
	public static Date getAfterHour(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);
		return calendar.getTime();
	}
	
	public static Date getAfterMin(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + min);
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
		// System.out.println(getAMNNPNStr(DateUtil.parseDate("HH:mm", "12:01"),
		// Locale.CHINESE));
//		System.out.println(DateUtil.parseDateToString(addDays(DateUtil.parseDate("yyyy-MM-dd", "2013-06-27"), 75), "yyyy-MM-dd"));
		System.out.println(getAfterMin(new Date(), 5));	
		System.out.println(getAfterHour(new Date(), 1));
	}
	
	
}
