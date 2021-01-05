package com.aswishes.novel.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 格式化日期、时间的工具类
 * 部分功能可以使用commons-lang.jar的DateUtils类
 */
public class DateUtil {
	
	/**
	 * 精确到天
	 */
	public static final String PATTERN_DAY = "yyyy-MM-dd";
	
	/**
	 * 精确到时
	 */
	public static final String PATTERN_HOUR = "yyyy-MM-dd HH";
	
	/**
	 * 精确到分
	 */
	public static final String PATTERN_MINUTE = "yyyy-MM-dd HH:mm";
	
	/**
	 * 精确到秒
	 */
	public static final String PATTERN_SECOND = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 精确到毫秒
	 */
	public static final String PATTERN_MINI_SECOND = "yyyy-MM-dd HH:mm:ss.SSS";

	public static String getDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static String getDate(String pattern) {
		return getDate(new Date(), pattern);
	}
	
	public static Date parseDate(String date, String...patterns) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return DateUtils.parseDate(date, patterns);
		} catch (ParseException e) {
			log.error("Parse date string error", e);
		}
		return null;
	}
	
	public static String getTimeStamp() {
		return null;
	}
	
	/**
	 * 今天的开始时间 00:00:00.000。精确到毫秒。
	 * @return
	 */
	public static Date getTodayBegin() {
		return getDayBegin(new Date());
	}
	
	/**
	 * 今天的结束时间 23:59:59.999。精确到毫秒。
	 * @return
	 */
	public static Date getTodyEnd() {
		return getDayEnd(new Date());
	}
	
	/**
	 * 计算指定日期的一天开始时间 00:00:00.000。精确到毫秒。
	 * @param date
	 * @return
	 */
	public static Date getDayBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 计算指定日期的一天结束时间 23:59:59.999。精确到毫秒。
	 * @param date
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 比较 目标时间 是否在指定的时间范围之内。精确到毫秒。
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param compareDate 目标时间
	 * @return
	 */
	public static boolean inDate(Date beginDate, Date endDate, Date compareDate) {
		return (compareDate.after(beginDate) && compareDate.before(endDate));
	}
	
	/**
	 * 比较 目标时间 是否在指定的时间一天之内。精确到毫秒。
	 * @param date 指定时间。比较时，将计算此时间的一天开始和结束时间
	 * @param compareDate 目标时间
	 * @return
	 */
	public static boolean inDay(Date date, Date compareDate) {
		return inDate(getDayBegin(date), getDayEnd(date), compareDate);
	}
	
	/**
	 * 比较 目标时间 是否在今天一天之内。精确到毫秒。
	 * @param compareDate 目标时间
	 * @return
	 */
	public static boolean inToday(Date compareDate) {
		return inDay(new Date(), compareDate); 
	}
	
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
}
