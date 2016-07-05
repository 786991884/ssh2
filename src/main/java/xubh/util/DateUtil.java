package xubh.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Calendar;

public class DateUtil {
	public static final String timeFormatStr = "yyyy-MM-dd HH:mm:ss";
	public static final String numFormatStr = "yyyyMMddHHmmss";
	private static final String dateFormatStr = "yyyy-MM-dd";
	private static final String mimuteFormatStr = "yyyy-MM-dd HH:mm";
	private static final String todayBeginFormatStr = "yyyy-MM-dd 00:00:00";
	private static final String todayEndFormatStr = "yyyy-MM-dd 23:59:59";
	/**
	 * 纯数字、带毫秒的日期格式串
	 */
	private static final String numMillFormatStr = "yyyyMMddHHmmssSSS";
	/**
	 * yyyy-mm-dd HH:mm:ss格式的日期格式化对象
	 */
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(timeFormatStr);
	/**
	 * yyyy-MM-dd的日期格式化对象
	 */
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
	/**
	 * yyyy-MM-dd HH:mm格式的日期格式化对象
	 */
	public static final SimpleDateFormat minuteFormat = new SimpleDateFormat(mimuteFormatStr);
	/**
	 * yyyy-MM-dd 00:00:00的日期格式化对象
	 */
	public static final SimpleDateFormat todayBegin = new SimpleDateFormat(todayBeginFormatStr);
	/**
	 * yyyy-MM-dd 23:59:59格式的日期格式化对象
	 */
	public static final SimpleDateFormat todayEnd = new SimpleDateFormat(todayEndFormatStr);
	/**
	 * yyyyMMddHHmmssSSS格式的日期格式化对象
	 */
	public static final SimpleDateFormat numMillFormat = new SimpleDateFormat(numMillFormatStr);
	static {
		TimeZone zone = TimeZone.getTimeZone("GMT+08:00");
		timeFormat.setTimeZone(zone);
		dateFormat.setTimeZone(zone);
		minuteFormat.setTimeZone(zone);
		todayBegin.setTimeZone(zone);
		todayEnd.setTimeZone(zone);
		numMillFormat.setTimeZone(zone);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 获得当前日期字符串。格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		return dateFormat.format(new Date());
	}

	/**
	 * 获得昨天的日期字符串。格式为yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String yesterDayDate() {
		return dateFormat.format(addDay(new Date(), -1));
	}

	/**
	 * 获得当前时间字符串。格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime() {
		return timeFormat.format(new Date());
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String dateToTimeString(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 获得当前时间字符串，精确到分钟。格式为：yyyy-mm-dd HH:mm
	 * 
	 * @return
	 */
	public static String currentMinute() {
		return minuteFormat.format(new Date());
	}

	/**
	 * 获得19位长度的时间字符串，格式为：yyyy-MM-dd 00:00:00
	 * 
	 * @return
	 */
	public static String todayBegin() {
		return todayBegin.format(new Date());
	}

	/**
	 * 获得19位长度的时间字符串，格式为：yyyy-MM-dd 23:59:59
	 * 
	 * @return
	 */
	public static String todayEnd() {
		return todayEnd.format(new Date());
	}

	/**
	 * 获得精确到毫秒的当前时间字符串。格式为：yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String currentNumMill() {
		return numMillFormat.format(new Date());
	}

	/**
	 * 获得时间字符串。格式为：yyyyMMdd
	 * 
	 * @return
	 */
	public static String Date2Num(String string, int length) {
		return numMillFormat.format(stringToDate(string)).substring(0, length - 1);
	}

	/**
	 * 获得当前时间，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getNow() {
		return currentTime();
	}

	/**
	 * 把传入的时间增加指定的月份后返回。
	 * 
	 * @return
	 */
	public static String addMonth(String dateTime, int months, String formatStr) {
		if (formatStr == null)
			formatStr = dateFormatStr;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		try {
			Date d = addMonth(sdf.parse(dateTime), months);
			return sdf.format(d);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把传入的时间增加指定的小时后返回。
	 * 
	 * @return
	 */
	public static String addHour(String dateTime, int hours, String formatStr) {
		if (formatStr == null)
			formatStr = dateFormatStr;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			Date d = addHour(sdf.parse(dateTime), hours);
			return sdf.format(d);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 把传入的时间增加指定的分钟后返回。
	 * 
	 * @return
	 */
	public static String addMinute(String dateTime, int min, String formatStr) {
		if (formatStr == null)
			formatStr = dateFormatStr;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			Date d = addMinute(sdf.parse(dateTime), min);
			return sdf.format(d);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日期增加小时
	 * 
	 * @param d
	 * @param months
	 * @return
	 */
	public static Date addHour(Date d, int hours) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(d);
		cal.add(Calendar.HOUR, hours);
		return cal.getTime();
	}

	public static Date addMinute(Date d, int min) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(d);
		cal.add(Calendar.MINUTE, min);
		return cal.getTime();
	}

	/**
	 * 日期增加月份
	 * 
	 * @param d
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date d, int months) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(d);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 日期增加天数
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static Date addDay(Date d, int days) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(d);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 获取的当前时间的Date
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		return cal.getTime();
	}

	/**
	 * 把传入的时间字符串增加指定天数之后，返回原格式的字符串
	 * 
	 * @param dateTime
	 * @param days
	 * @param formatStr
	 * @return
	 */
	public static String addDay(String dateTime, int days, String formatStr) {
		if (formatStr == null)
			formatStr = dateFormatStr;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			return sdf.format(addDay(sdf.parse(dateTime), days));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取这周的星期日期 add by HYL since 2009-08-07
	 * 
	 * @param i
	 *            星期几? 星期日用0表示
	 * @return
	 */
	public static String getThisWeekDay(int i) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(new Date());
		cal.add(Calendar.DATE, i + 1 - cal.get(Calendar.DAY_OF_WEEK));
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 获取上周的星期日期 add by HYL since 2009-08-07
	 * 
	 * @param i
	 *            星期几? 星期日用0表示
	 * @return
	 */
	public static String getLastWeekDay(int i) {
		Calendar cal = Calendar.getInstance(new Locale("zh-CN"));
		cal.setTime(new Date());
		cal.add(Calendar.DATE, i - 6 - cal.get(Calendar.DAY_OF_WEEK));
		return dateFormat.format(cal.getTime());
	}

	/**
	 * 将字符串型的时间转化成Date类型
	 * 
	 * @param timeStr
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date stringToDate(String timeStr) {
		TimeZone zone = TimeZone.getTimeZone("GMT+08:00");
		Calendar cal = Calendar.getInstance(zone);
		try {
			timeStr = timeStr.trim();
			int year = Integer.parseInt(timeStr.substring(0, 4));
			int month = Integer.parseInt(timeStr.substring(5, 7)) - 1;
			int date = Integer.parseInt(timeStr.substring(8, 10));
			int hour = Integer.parseInt(timeStr.substring(11, 13));
			int min = Integer.parseInt(timeStr.substring(14, 16));
			int sec = Integer.parseInt(timeStr.substring(17, 18));
			cal.set(year, month, date, hour, min, sec);
			Date d = cal.getTime();
			d.setHours(cal.get(Calendar.HOUR_OF_DAY));
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return cal.getTime();
		}
	}

	/**
	 * 计算 系统当前时间-输入时间 的时间间隔 单位：小时
	 * 
	 * @param date
	 * @return
	 */
	public static double disTime(String timeStr) {
		double d = (stringToDate(getNow()).getTime()) - (stringToDate(timeStr).getTime());
		double dd = d / 3600000;
		return dd;
	}

	/** 把时间字符串从一种形式转换成另一种形式 */
	public static String convertTimeFormat(String timeStr, String srcFormatStr, String distFormatStr) {
		SimpleDateFormat srcFormat = new SimpleDateFormat(srcFormatStr);
		SimpleDateFormat distFormat = new SimpleDateFormat(distFormatStr);
		srcFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		distFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		return convertTimeFormat(timeStr, srcFormatStr, distFormatStr);
	}

	/** 把时间字符串从一种格式转换成另一种格式 */
	public static String convertTimeFormat(String timeStr, SimpleDateFormat srcFormat, SimpleDateFormat distFormat) {
		if (timeStr == null || timeStr.length() == 0)
			return null;
		try {
			return distFormat.format(srcFormat.parse(timeStr));
		} catch (ParseException e) {
			return null;
		}
	}

	/** 把时间字符串从一种格式转换成另一种格式 */
	public static String timeFormat(String timeStr, String srcFormat, String distFormat) {
		if (timeStr == null || timeStr.length() == 0)
			return null;
		DateFormat srcFormat_01 = new SimpleDateFormat(srcFormat);
		DateFormat disFormat_01 = new SimpleDateFormat(distFormat);
		try {
			Date date = srcFormat_01.parse(timeStr);
			return disFormat_01.format(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 解析一个日期之间的所有月份
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getMonthList(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
		// 返回的月份列表
		String sRet = "";
		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;
		GregorianCalendar beginGC = null;
		GregorianCalendar endGC = null;
		ArrayList list = new ArrayList();
		try {
			// 将字符串parse成日期
			beginDate = f.parse(beginDateStr);
			endDate = f.parse(endDateStr);
			// 设置日历
			beginGC = new GregorianCalendar();
			beginGC.setTime(beginDate);
			endGC = new GregorianCalendar();
			endGC.setTime(endDate);
			// 直到两个时间相同
			while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
				sRet = beginGC.get(Calendar.YEAR) + "-" + (beginGC.get(Calendar.MONTH) + 1);
				list.add(sRet);
				// 以月为单位，增加时间
				beginGC.add(Calendar.MONTH, 1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解析一个日期段之间的所有日期
	 * 
	 * @param beginDateStr
	 *            开始日期
	 * @param endDateStr
	 *            结束日期
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ArrayList getDayList(String beginDateStr, String endDateStr) {
		// 指定要解析的时间格式
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		// 定义一些变量
		Date beginDate = null;
		Date endDate = null;
		Calendar beginGC = null;
		Calendar endGC = null;
		ArrayList list = new ArrayList();
		try {
			// 将字符串parse成日期
			beginDate = f.parse(beginDateStr);
			endDate = f.parse(endDateStr);
			// 设置日历
			beginGC = Calendar.getInstance();
			beginGC.setTime(beginDate);
			endGC = Calendar.getInstance();
			endGC.setTime(endDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// 直到两个时间相同
			while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
				list.add(sdf.format(beginGC.getTime()));
				// 以日为单位，增加时间
				beginGC.add(Calendar.DAY_OF_MONTH, 1);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getCurrYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap getWeekList(int year) {
		LinkedHashMap map = new LinkedHashMap();
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekOfYear(c.getTime());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dayOfWeekStart = "";
		String dayOfWeekEnd = "";
		for (int i = 1; i <= count; i++) {
			dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
			dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
			map.put(new Integer(i), "第" + i + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
		}
		return map;

	}

	/**
	 * 得到一年的总周数
	 * 
	 * @param year
	 * @return
	 */
	public static int getWeekCountInYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int count = getWeekOfYear(c.getTime());
		return count;
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getFirstDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);

		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);

		return getLastDayOfWeek(cal.getTime());
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getFirestDayOfMonth(int year, int month) {
		month = month - 1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);

		int day = c.getActualMinimum(c.DAY_OF_MONTH);

		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();

	}

	/**
	 * 提到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getLastDayOfMonth(int year, int month) {
		month = month - 1;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		int day = c.getActualMaximum(c.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	public static void main(String[] args) {
		double d = (stringToDate(getNow()).getTime()) - (stringToDate("2009-08-27 16:00:00").getTime());
		double dd = d / 3600000;
		System.out.println(d + "  " + dd + " " + disTime("2009-08-27 16:00:00"));
	}
}