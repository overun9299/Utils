package overun.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: DateUtil
 * @Description: 时间处理类
 * @author: 壹米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/9/9 10:24
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
public class DateUtil {


    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String PATTERN_DATE_POINT = "MM.dd";

    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String PATTERN_DATE_HOUR = "HH";

    /**
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
     */
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 将指定时间对象按照指定格式转换成字符串，如果指定格式为null，则按照默认的格式转换
     * @param  date 指定时间
     * @param  pattern 指定格式
     * @return String
     * */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = PATTERN_TIME;
        }
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        return fastDateFormat.format(date);
    }

    /**
     * 将指定时间对象按照指定格式转换成字符串，如果指定格式为null，则按照默认的格式转换
     * @return String
     * */
    public static Date stringToDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        Date date = null;
        try {
            date = FastDateFormat.getInstance().parse(dateStr);
        } catch (ParseException e) {
            logger.error("转换时间异常" + e.getMessage());
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 描述：日期格式化
     *
     * @param date
     *            日期
     * @return 输出格式为 yyyy-MM-dd 的字串
     */
    public static String formatDate(Date date) {
        return formatDate(date, PATTERN_TIME);
    }

    /**
     * 输出格式为 yyyy-MM-dd 的字串
     * @param date
     * @return
     */
    public static String formatDateDay(Date date) {
        return formatDate(date, PATTERN_DATE);
    }

    /**
     * 输出格式为 MM.dd 的字串
     * @param date
     * @return
     */
    public static String formatDateDayPoint(Date date) {
        return formatDate(date, PATTERN_DATE_POINT);
    }

    /**
     * 输出格式为 HH 的字串
     * @param date
     * @return
     */
    public static String formatDateDayHour(Date date) {
        return formatDate(date, PATTERN_DATE_HOUR);
    }


    /**
     * 描述：日期格式化
     *
     * @param date
     *            日期
     * @param pattern
     *            格式化类型
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 描述：解析日期字串
     *
     * @param dateStr
     *            日期字串
     * @return 按 yyyy-MM-dd HH:mm:ss 格式解析
     */
    public static Date parseString(String dateStr) {
        return parseString(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 描述：解析日期字串
     *
     * @param dateStr
     *            日期字串
     * @param pattern
     *            字串日期格式
     * @return 对应日期类型数据
     */
    public static Date parseString(String dateStr, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            if (!"".equals(dateStr)) {
                return dateFormat.parse(dateStr);
            }
        } catch (ParseException ex) {
            System.err.println(dateStr + "转换成日期失败，可能是不符合格式：" + pattern);
            logger.error("获取时差失败" + ex.getMessage());
        }
        return null;
    }

    /**
     * 描述：获取指定日期的中文星期数
     *
     * @param date
     *            指定日期
     * @return 星期数，如：星期一
     */
    public static String getWeekStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(7);
        --week;
        String weekStr = "";
        switch (week) {
            case 0:
                weekStr = "星期日";
                break;
            case 1:
                weekStr = "星期一";
                break;
            case 2:
                weekStr = "星期二";
                break;
            case 3:
                weekStr = "星期三";
                break;
            case 4:
                weekStr = "星期四";
                break;
            case 5:
                weekStr = "星期五";
                break;
            case 6:
                weekStr = "星期六";
        }
        return weekStr;
    }

    /**
     * 描述：间隔时间
     *
     * @param date1
     * @param date2
     * @return 毫秒数
     */
    public static long getDateMiliDispersion(Date date1, Date date2) {
        if ((date1 == null) || (date2 == null)) {
            return 0L;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return time1 - time2;
    }

    /**
     * 描述：间隔天数
     *
     * @param date1
     * @param date2
     * @return 天数
     */
    public static int getDateDiff(Date date1, Date date2) {
        if ((date1 == null) || (date2 == null)) {
            return 0;
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long diff = time1 - time2;

        Long longValue = new Long(diff / 86400000L);
        return longValue.intValue();
    }

    /**
     * 描述：获取指定日期之前多少天的日期
     *
     * @param date
     *            指定日期
     * @param day
     *            天数
     * @return 日期
     */
    public static Date getDataDiff(Date date, int day) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        time -= 86400000L * day;
        return new Date(time);
    }

    /**
     * 描述：获取当前周
     *
     * @return
     */
    public static int getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(7);
        --week;
        if (week == 0) {
            week = 7;
        }
        return week;
    }

    /**
     * 描述：获取中文当前周
     *
     * @return
     */
    public static String getCurrentWeekStr() {
        return getWeekStr(new Date());
    }

    /**
     * 描述：获取本年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1);
    }

    /**
     * 描述：获取本月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(2) + 1;
    }

    /**
     * 描述：获取本月的当前日期数
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(5);
    }

    /**
     * 描述：当前时间与指定时间的差
     *
     * @param str
     *            秒数
     * @return 时间差，单位：秒
     */
    public static int getUnixTime(String str) {
        if ((str == null) || ("".equals(str))) {
            return 0;
        }
        try {
            long utime = Long.parseLong(str) * 1000L;
            Date date1 = new Date(utime);

            Date date = new Date();

            long nowtime = (date.getTime() - date1.getTime()) / 1000L;
            return (int) nowtime;
        } catch (Exception e) {
            logger.error("获取时差失败" + e.getMessage());
        }
        return 0;
    }

    /**
     * 描述：去除日期字串中原“-”和“:”
     * @return
     */
    public static String formatString(String dateTime) {
        if ((dateTime != null) && (dateTime.length() >= 8)) {
            String formatDateTime = dateTime.replaceAll("-", "");
            formatDateTime = formatDateTime.replaceAll(":", "");
            String date = formatDateTime.substring(0, 8);
            return date;
        }

        return "";
    }

    /**
     * 描述：当前时间与指定时间的差
     *
     * @param str
     *            yyyy-MM-dd HH:mm:ss 格式的日期
     * @return 时间差，单位：秒
     */
    public static int getTimesper(String str) {
        if ((str == null) || ("".equals(str))) {
            return 0;
        }
        try {
            Date date1 = new Date(Long.parseLong(str));
            Date date = new Date();
            long nowtime = (date.getTime() - date1.getTime()) / 1000L;
            return (int) nowtime;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("日期转换出错" + e.getMessage());
        }
        return 0;
    }

    /**
     * 描述：获取16位日期时间，yyyyMMddHHmmss
     *
     * @param dateTime
     *            字串日期
     * @return
     */
    public static String formatDateTime(String dateTime) {
        if ((dateTime != null) && (dateTime.length() >= 8)) {
            String formatDateTime = dateTime.replaceAll("-", "");
            formatDateTime = formatDateTime.replaceAll(":", "");
            String date = formatDateTime.substring(0, 8);
            String time = formatDateTime.substring(8).trim();
            for (int i = time.length(); i < 6; ++i) {
                time = time + "0";
            }
            return date + time;
        }

        return "";
    }

    /**
     * 描述：获取16位日期时间，yyyyMMddHHmmss
     *
     * @param date
     *            日期
     * @return
     */
    public static String formatDateTime(Date date) {
        String dateTime = formatDate(date);
        return formatDateTime(dateTime);
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 功能描述：返回小
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * converter ZT to UTC
     * @param zt
     * @return
     */
    public static String getUTCTime(String zt) {
        zt = zt.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        Date d = null;
        try {
            d = format.parse(zt);
        } catch (Exception e) {
            logger.error("Z日期转换出错UTC" + e.getMessage());
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    /**
     * converter ZT to UTC
     * @param zt
     * @return
     */
    public static String getUTCTime(String zt, String pattern) {
        zt = zt.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        Date d = null;
        try {
            d = format.parse(zt);
        } catch (Exception e) {
            logger.error("Z日期转换出错UTC" + e.getMessage());
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }


    /**
     * @MethodName transHuaweiDate_1
     * @Description 日期转换
     * @param date
     *            20180625093058
     * @return Thu Jul 05 09:00:08 CST 2018
     */
    public static Date transHuaweiDate_1(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 6));
        int day = Integer.parseInt(date.substring(7, 8));
        int hours = Integer.parseInt(date.substring(9, 10));
        int minutes = Integer.parseInt(date.substring(11, 12));
        int seconds = Integer.parseInt(date.substring(13, 14));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hours, minutes, seconds);
        return calendar.getTime();
    }

    /**
     * @MethodName transHuaweiDate_2
     * @Description 日期转换
     * @param date
     *            20180625000000
     * @return Thu Jul 05 00:00:00 CST 2018
     */
    public static Date transHuaweiDate_2(String date) {

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 6));
        int day = Integer.parseInt(date.substring(7, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     *
     * @MethodName getDateList
     * @Description 获取传入时间与当相差的每天list，时间格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static void main(String[] args) {
        System.out.println(getDateList(new Date()));
    }
    public static List<Date> getDateList(Date date) {
        List<Date> dateList = new ArrayList<Date>();
        long nd = 1000 * 24 * 60 * 60L;
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        if (null != date) {
            long one = date.getTime();
            long two = now.getTime();
            /** 相差的天数 */
            long day = (two - one) / nd;
            for (int i = 0; i <= day; i++) {
                calendar.setTime(date);
                /** 获取当天0点 */
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                calendar.add(Calendar.DATE, +i);
                Date dates = calendar.getTime();
                dateList.add(dates);
            }
        } else {
            calendar.setTime(now);
            /** 获取当天0点 */
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            now = calendar.getTime();
            dateList.add(now);
        }

        return dateList;
    }

    /**
     * 获取当前月第一天和最后一天的日期
     *
     * @return
     */
    public static Map<String, Date> getMonthBeginAndEnd() {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c1 = Calendar.getInstance();
        int firastDay = c1.getActualMinimum(Calendar.DAY_OF_MONTH);
        c1.set(Calendar.DAY_OF_MONTH, firastDay);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        map.put("monthBegin", c1.getTime());
        int lastDay = c1.getActualMaximum(Calendar.DAY_OF_MONTH);
        c1.set(Calendar.DAY_OF_MONTH, lastDay);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        map.put("monthEnd", c1.getTime());
        return map;
    }

    /**
     * 获取当天和30天前日期
     * 如:
     * 2018-11-28 00:00:00 至 2018-12-28 23:59:59
     *
     * @return
     */
    public static Map<String, Date> getMonthBeginAndEndFor30Days() {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DAY_OF_MONTH, -30);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        map.put("monthBegin", c1.getTime());
        c1.add(Calendar.DAY_OF_MONTH, 30);
        c1.set(Calendar.HOUR_OF_DAY, 23);
        c1.set(Calendar.MINUTE, 59);
        c1.set(Calendar.SECOND, 59);
        map.put("monthEnd", c1.getTime());
        return map;
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
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
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek()); // Sunday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                calendar.getFirstDayOfWeek() + 6); // Saturday
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 返回指定日期的月的第一天
     *v
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的上个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfDate(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfDate(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR),
                getQuarterOfDate(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的年的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                0, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的年的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                11, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的年
     *
     * @param date
     * @return
     */
    public static int getYearOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 返回指定日期的月
     *
     * @param date
     * @return
     */
    public static int getMonthOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }

    /**
     * 返回指定日期的周
     *
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回指定日期的相应的年，季，月，周以及其的最后一天
     * @param date
     * @return
     */
    public static JSONObject getAllOfDate(Date date){
        JSONObject json = new JSONObject();
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
        json.put("currentDate",dateFm.format(date));
        json.put("currentDateYear",getYearOfDate(date));
        json.put("currentDateQuarter",getQuarterOfDate(date));
        json.put("currentDateMonth",getMonthOfDate(date));
        json.put("currentDateWeek",getWeekOfDate(date));
        json.put("currentDateYearLastDay",dateFm.format(getLastDayOfYear(date)));
        json.put("currentDateQuarterLastDay",dateFm.format(getLastDayOfQuarter(date)));
        json.put("currentDateMonthLastDay",dateFm.format(getLastDayOfMonth(date)));
        json.put("currentDateWeekLastDay",dateFm.format(getLastDayOfWeek(date)));
//        System.out.println("当前日期：" + dateFm.format(date));
//        System.out.println("当前日期的年份：" + getYearOfDate(date));
//        System.out.println("当前日期的季度：" + getQuarterOfDate(date));
//        System.out.println("当前日期的月份：" + getMonthOfDate(date));
//        System.out.println("当前日期的周：" + getWeekOfDate(date));
//        System.out.println("当前日期的周的最后一天：" + dateFm.format(getLastDayOfWeek(date)));
//        System.out.println("当前日期的月的最后一天：" + dateFm.format(getLastDayOfMonth(date)));
//        System.out.println("当前日期的季度的最后一天：" + dateFm.format(getLastDayOfQuarter(date)));
//        System.out.println("当前日期的你年的最后一天：" + dateFm.format(getLastDayOfYear(date)));
//        System.out.println(json);
        return json;
    }

    /**
     * 获取时间的年月日
     */
    public static String getYearMonthDate(Date date){
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
    }

    /**
     * 获取传入 @date 的 当前时间为早上7点45
     */
    public static Date getMonitor_745(Date date){
        date = new Date();
        date.setHours(7);
        date.setMinutes(45);
        date.setSeconds(0);
        return date;
    }

    /**
     * 获取传入 @date 的 当前时间的前一小时15分钟数据
     */
    public static String getMonitor_Later15Miniter(Date date){
        if (date == null) {
            date = new Date();
        }
        if(date.getMinutes() < 45){
            date.setMinutes(date.getMinutes()+15);
        }else {
            date.setHours(date.getHours()+1);
            date.setMinutes(0);
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }


    /**
     * 获取传入 @date 的 0点0分0秒
     */
    public static String getCurrentDay_000_forHuawei(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setDate(date.getDate()-1);
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }
    /**
     * 获取传入 @date 的 0点0分0秒
     */
    public static Date getCurrentDay_000(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        return date;
    }
    /**
     * 获取传入 @date 的 23点59分59秒
     */
    public static Date getCurrentDay_999(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    /**
     * 获取传入 @date 的 yyyy-MM-dd
     */
    public static String getCurrentDay_yyyyMMdd(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setDate(date.getDate()-1);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(date);
    }

    public static Date getYesterday(){
        Date date = new Date();
        date.setDate(date.getDate()-1);
        return date;
    }



    /**
     * 获取传入 @date 的 0点0分0秒
     */
    public static String getDay_000(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    /**
     * 获取传入 @date 的 23点59分59秒
     */
    public static String getDay_999(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    /**
     * 获取传入 @date 的前一天的 0点0分0秒
     */
    public static String getDay_000_yesterday(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setDate(date.getDate()-1);
        date.setHours(00);
        date.setMinutes(00);
        date.setSeconds(00);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    /**
     * 获取传入 @date 的前一天的 23点59分59秒
     */
    public static String getDay_999_yesterday(Date date){
        if (date == null) {
            date = new Date();
        }
        date.setDate(date.getDate()-1);
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(date);
    }

    /**
     * 根据秒数获取时间串
     * (eg: 100s)  -->  (eg: 00:01:40)
     */
    public static String getTimeStrBySecond(int seconds) {
        if (seconds < 0) {
            seconds = 0;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        temp = seconds / 3600;
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

        temp = seconds % 3600 / 60;
        sb.append((temp < 10) ? "0" + temp + ":" : "" + temp + ":");

        temp = seconds % 3600 % 60;
        sb.append((temp < 10) ? "0" + temp : "" + temp);
        return sb.toString();
    }
}
