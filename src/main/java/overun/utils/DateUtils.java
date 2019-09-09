package overun.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: 时间处理类扩展
 * @author: 壹米滴答-西安-ZhangPY
 * @version: V1.0
 * @date: 2019/9/9 10:30
 * @Copyright: 2019 www.yimidida.com Inc. All rights reserved.
 */
public class DateUtils {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String PATTEN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTEN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static final String PATTEN_YYYY_MM_DD_T_HH_MM_SS_T = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String PATTEN_HH_MM_SS = "HH:mm:ss";
    /**
     * 时间格式转换
     * 字符串转日期
     *
     * @param date
     * @return
     */
    public static Date convertStringToDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat format = new SimpleDateFormat(DateUtils.PATTEN_YYYY_MM_DD_HH_MM_SS);
            try {
                return format.parse(date);
            } catch (ParseException e) {
                // e.printStackTrace();
                logger.error("字符串转日期" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * 时间格式转换
     * 字符串转日期
     * 包含 yyyy-MM-dd'T'HH:mm:ss.SSS'Z' 类型的时间格式并且时间格式为yyyy-MM-dd'T'HH:mm:ss.SSS'Z'时自动延后一天
     * @param date
     * @return
     */
    public static Date convertStringToDateZ(String date) {
        if (StringUtils.isNotBlank(date)) {
            SimpleDateFormat format = null;
            if (date.contains("T")) {
                format = new SimpleDateFormat(DateUtils.PATTEN_YYYY_MM_DD_T_HH_MM_SS_T);
            } else {
                format = new SimpleDateFormat(DateUtils.PATTEN_YYYY_MM_DD_HH_MM_SS);
            }
            try {
                if (date.contains("T")) {
                    Date parse = format.parse(date);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(parse);
                    calendar.add(Calendar.DATE,1);
                    return calendar.getTime();
                } else {
                    return format.parse(date);
                }
            } catch (ParseException e) {
                // e.printStackTrace();
                logger.error("字符串转日期" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * 时间格式转换
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String covertDateToString(Date date) {
        String time = "";
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTEN_YYYYMMDDHHMMSS);
            time = sdf.format(date);
        }
        return time;
    }

    /**
     * 获取前i天的时间
     * @param i 天数
     * @return
     */
    public static String getPastDate(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - i);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取当前时间是否在工作时间08:00-21:00
     * @return
     */
    public static boolean getWorkPart() {
        Date date = new Date();
        /** 早上 */
        SimpleDateFormat formatM = new SimpleDateFormat("yyyy-MM-dd 08:00:00");
        Date dateM = DateUtils.convertStringToDate(formatM.format(date));
        /** 下午 */
        SimpleDateFormat formatN = new SimpleDateFormat("yyyy-MM-dd 21:00:00");
        Date dateN = DateUtils.convertStringToDate(formatN.format(date));

        if (date.after(dateM) && date.before(dateN)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断两个时间之差是否小于12小时
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean getTimeRange(Date startDate , Date endDate) {

        if (startDate != null && endDate != null) {
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long diff = endTime - startTime;
            long second = diff / 1000 ;
            if (0 < second && 43200 > second) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 时间格式转换
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String covertDateToStringFormat(Date date) {
        String time = "";
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTEN_YYYY_MM_DD_HH_MM_SS);
            time = sdf.format(date);
        }
        return time;
    }

    /**
     * 时间戳转HH:mm:ss
     * @param timeStamp
     * @return
     */
    public static String covertTimeStampToStringFormat(Long timeStamp) {
        String time = "";
        if (timeStamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTEN_HH_MM_SS);
            time = sdf.format(timeStamp);
        }
        return time;
    }

}
