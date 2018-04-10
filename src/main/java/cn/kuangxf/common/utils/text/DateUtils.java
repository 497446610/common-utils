package cn.kuangxf.common.utils.text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期操作工具类
 * 
 * @history
 * 
 */
public class DateUtils {
	/**
	 * 将yyyyMMddHHmmss格式 格式化为yyyy-MM-dd
	 * 
	 * @param date
	 *            yyyyMMddHHmmss格式
	 * @return yyyy-MM-dd
	 */
	public static String parseToDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 给定指定模式，指定日期字符串
	 * 
	 * @param pattern
	 *            模式如(yyyyMMddHHmmss...)
	 * @param date
	 *            日期
	 * @return 解释后返回的日期对象
	 */
	public static Date parseDate(String pattern, String date) {
		if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date))
			return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			Logger.getLogger("DateUtil").warn(e.getMessage(), e);
			return new Date();
		}
	}

	/**
	 * 给定指定模式，指定日期字符串
	 * 
	 * @param pattern
	 *            模式如(yyyyMMddHHmmss...)
	 * @param date
	 *            日期
	 * @return 解释后返回的日期对象
	 */
	public static String parseDate(String pattern, Date date) {
		if (null == date || StringUtils.isEmpty(pattern))
			return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 将yyyyMMddHHmmss格式 格式化为HH:mm:ss
	 * 
	 * @param date
	 *            yyyyMMddHHmmss格式的日期
	 * @return HH:mm:ss
	 */
	public static String parseToTimeHHmmss(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat fo = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 将yyyyMMddHHmm格式 格式化为HH:mm
	 * 
	 * @param date
	 *            yyyyMMddHHmm格式
	 * @return HH:mm
	 */
	public static String parseToTimeHHMM(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat fo = new SimpleDateFormat("HH:mm");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 将yyyyMMddHHmmss格式 格式化为yyyy/MM/dd HH:mm:ss
	 * 
	 * @param date
	 *            yyyyMMddHHmmss格式
	 * @return yyyy/MM/dd HH:mm:ss
	 */
	public static String parseToDateYYYYMMDDHHMMSS(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat fo = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 返回默认时间格式 14位:yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * 返回默认时间格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getCurrentFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * 取当前日期之前几天的日期
	 * 
	 * @param day
	 * @param parrten
	 * @return
	 */
	public static String getBeforeDay(int day, String parrten) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -day);
		SimpleDateFormat format = new SimpleDateFormat(parrten);
		return format.format(cal.getTime());
	}

	/**
	 * 取当前日期之后几天的日期
	 * 
	 * @param day
	 * @param parrten
	 * @return
	 */
	public static String getAfterDay(int day, String parrten) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, day);
		SimpleDateFormat format = new SimpleDateFormat(parrten);
		return format.format(cal.getTime());
	}

	/**
	 * 返回默认时间格式 14位:yyyyMMdd
	 * 
	 * @return
	 */
	public static String getCurrentDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	/**
	 * 返回指定的时间格式
	 * 
	 * @return
	 */
	public static String getCurrentDate(String parrten) {
		if (StringUtils.isEmpty(parrten)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(parrten);
		return format.format(new Date());
	}

	public static String getNextDay(String baseDate, String parrten) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parrten);
		try {
			Date base = simpleDateFormat.parse(baseDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(base);
			cal.add(Calendar.DATE, 1);
			return simpleDateFormat.format(cal.getTime());
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 取得当前日期的相对时间
	 * 
	 * @param parrten
	 * @param days
	 * @return
	 */
	public static String getRelativeDate(String parrten, int days) {
		if (StringUtils.isEmpty(parrten)) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		SimpleDateFormat format = new SimpleDateFormat(parrten);
		return format.format(cal.getTime());
	}

	/** 日期格式yyyyMMddHHmmss **/
	public static String FORMAT_TIME = "yyyyMMddHHmmss";
	public static String FORMAT_DATE = "yyyyMMdd";
	/** 日期格式 yyyy-MM-dd HH:mm:ss **/
	public static final String FORMAT_TIME_SHOW = "yyyy-MM-dd HH:mm:ss";
	public static final Integer YEAR = 1;
	public static final Integer MONTH = 2;
	public static final Integer DAY = 3;

	public static final Integer dayTime = 1000 * 60 * 60 * 24;// 一天的毫秒数
	public static final Integer hourTime = 1000 * 60 * 60;// 一小时的毫秒数

	/**
	 * 日期比较，大于baseDate，返回false
	 * 
	 * @param format
	 *            日期字符串格式
	 * @param baseDate
	 *            被比较的日期
	 * @param compareDate
	 *            比较日期
	 * @return
	 */
	public static boolean before(String format, String baseDate, String compareDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date base = simpleDateFormat.parse(baseDate);
			Date compare = simpleDateFormat.parse(compareDate);

			return compare.before(base);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 根据格式得到 step时间间隔以后的时间字符串。
	 * 
	 * @param format
	 * @param baseTime
	 *            基准时间
	 * @param step
	 *            以分钟为单位
	 * @return 返回的格式 为 format
	 */
	public static String getNextTime(String format, String baseTime, long step) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date base = simpleDateFormat.parse(baseTime);
			long nextTime = base.getTime() + step * 60 * 1000;

			Date newTime = new Date();
			newTime.setTime(nextTime);

			return simpleDateFormat.format(newTime);

		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 将yyyy-MM-dd格式 格式化为yyyyMMdd
	 * 
	 * @param date
	 *            yyyy-MM-dd格式
	 * @return yyyyMMdd
	 */
	public static String parseToDateYYYYMMdd(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 将yyyyMMdd格式 格式化为yyyy-MM-dd
	 * 
	 * @param date
	 *            yyyy-MM-dd格式
	 * @return yyyy-MM-dd
	 */
	public static String parseToDate2(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 日期字符串格式转换通用方法
	 * 
	 * @param date
	 *            要转换的值
	 * @param sourceFormate
	 *            源日期格式
	 * @param objectiveFormate
	 *            目标日期格式
	 * @return
	 */
	public static String parseToDate(String date, String sourceFormate, String objectiveFormate) {
		SimpleDateFormat format = new SimpleDateFormat(sourceFormate);
		SimpleDateFormat fo = new SimpleDateFormat(objectiveFormate);
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 得到前两个月的最后一天的日期和 前一个月的最后一天的日期(如果为<=0取默认的) 参数可选
	 * 
	 * @param startMonth
	 *            (默认2)
	 * @param endMonth
	 *            (默认1)
	 * @param datePattern
	 *            日期格式(默认yyyy-MM-dd)
	 * @return key[startDate],key[endDate]
	 */
	public static Map<String, String> getDateForSet(Integer startMonth, Integer endMonth, String datePattern) {
		if (startMonth == null) {
			startMonth = -2;
		} else {
			startMonth = Integer.parseInt("-" + startMonth);
		}
		if (endMonth == null) {
			endMonth = -1;
		} else {
			endMonth = Integer.parseInt("-" + startMonth);
		}
		if (StringUtils.isEmpty(datePattern)) {
			datePattern = "yyyy-MM-dd";
		}

		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		Calendar start = Calendar.getInstance();
		start.add(Calendar.MONTH, startMonth);
		start.set(Calendar.DAY_OF_MONTH, start.getActualMaximum(Calendar.DAY_OF_MONTH));
		String startDate = format.format(start.getTime());

		Calendar end = Calendar.getInstance();
		end.add(Calendar.MONTH, endMonth);
		end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
		String endDate = format.format(end.getTime());
		Map<String, String> rs = new HashMap<String, String>(1);
		rs.put("startDate", startDate);
		rs.put("endDate", endDate);
		return rs;
	}

	/**
	 * 将yyyyMMddHHmmss格式 格式化为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            yyyyMMddHHmmss格式
	 * @return yyyy/MM/dd HH:mm:ss
	 */
	public static String parseToDateAD(String date) {
		if (StringUtils.isEmpty(date)) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = format.parse(date);
			return fo.format(date1);
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 取当前时间前一天和当前时间
	 * 
	 */
	public static Map<String, String> getLastCurrDay() {
		String datePattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.add(Calendar.DATE, -1); // 得到前一天
		String startDate = format.format(calendarStart.getTime());
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.add(Calendar.DATE, 0); // 得到前一天
		String endDate = format.format(calendarEnd.getTime());
		Map<String, String> rs = new HashMap<String, String>(1);
		rs.put("startDate", startDate);
		rs.put("endDate", endDate);
		return rs;
	}

	/**
	 * 取当月第一天和当前时间
	 * 
	 */
	public static Map<String, String> getFirstDayInMonth() {
		String datePattern = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		Calendar calendarStart = Calendar.getInstance();
		calendarStart.set(Calendar.DAY_OF_MONTH, 1); // 得到当月第一天
		String startDate = format.format(calendarStart.getTime());
		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.add(Calendar.DATE, 0); // 得到当天日期
		String endDate = format.format(calendarEnd.getTime());
		Map<String, String> rs = new HashMap<String, String>(1);
		rs.put("startDate", startDate);
		rs.put("endDate", endDate);
		return rs;
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getFirstDayInMonth(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date tmpdate = null;
		try {
			tmpdate = format.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tmpdate);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			tmpdate = calendar.getTime();
			return format.format(tmpdate);
		} catch (ParseException e) {
			return "";
		}

	}

	/**
	 * 计算倒计时
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String countDown(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date tmpdate = null;
		try {
			tmpdate = format.parse(date);
			long toTime = tmpdate.getTime();
			long nowTime = (new Date()).getTime();

			long diff = toTime - nowTime;
			if (diff < 0) {
				return "";
			}

			long day = diff / dayTime;

			long hour = (diff % dayTime) / hourTime;

			if (day <= 0 && hour <= 0) {
				return "";
			}
			String result = "";

			if (day > 0) {
				result = result + day + "天";
			}
			if (hour > 0) {
				result = result + hour + "小时";
			}
			return result;
		} catch (ParseException e) {
			return "";
		}
	}

	public static void main(String[] args) throws Exception {

	}

	public static String dateAdd(String dateStr, String pattern, Integer part, int i) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = parseDate(pattern, dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(part, i);
		return format.format(calendar.getTime());
	}

	/**
	 * 取得当前的年份
	 * 
	 * @return
	 */
	public static Integer getCurrYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.YEAR);

	}

	public static Integer getCurrMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.MONTH) + 1;
	}
}
