package com.web.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TimeUtil {

    private static final Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap();
    public static final SimpleDateFormat YMDHMSS_FORMAT = getSimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    public static final SimpleDateFormat YMD_FORMAT = getSimpleDateFormat("yyyy_MM_dd");
    public static Date DEFAULT_YMD;
    public static Date DEFAULT_YMDHMSS;

    public static final int SECOND = 1000;// 1秒
    public static final int MINUTE = 1 * 60 * 1000;// 1分钟
    public static final int FIVE_MINUTE = 5 * 60 * 1000;// 5分钟
    public static final int HOUR = 60 * 60 * 1000;// 1小时
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";// 格式：yyyyMMdd
    public static final String PATTERN_yyyyMM = "yyyyMM";//格式：yyyyMM

    static {
        try {
            DEFAULT_YMD = YMD_FORMAT.parse("2000_01_01");
            DEFAULT_YMDHMSS = YMD_FORMAT.parse("2000_01_01_01_01_01");
        } catch (Exception var1) {
            var1.printStackTrace();
        }
    }

    public static final SimpleDateFormat getSimpleDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> sdf = sdfMap.get(pattern);
        if (sdf == null) {
            sdf = new ThreadLocal<SimpleDateFormat>() {
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(pattern);
                }
            };
            sdfMap.put(pattern, sdf);
        }

        return sdf.get();
    }

    /**
     * 把日期类型转换为字节数组
     *
     * @param date
     * @return
     */
    public static byte[] dateToBytes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        byte[] byteArray = new byte[7];
        short year = (short) calendar.get(Calendar.YEAR);
        byteArray[0] = (byte) ((year >>> 8) & 0xFF);
        byteArray[1] = (byte) (year & 0xFF);
        byteArray[2] = (byte) (calendar.get(Calendar.MONTH) + 1);
        byteArray[3] = (byte) calendar.get(Calendar.DATE);
        byteArray[4] = (byte) calendar.get(Calendar.HOUR_OF_DAY);
        byteArray[5] = (byte) calendar.get(Calendar.MINUTE);
        byteArray[6] = (byte) calendar.get(Calendar.SECOND);
        return byteArray;
    }

    /**
     * 格式化日期,默认格式yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }


    /****
     * 获取当前时间 14位(yyyyMMddHHmmss)的时间戳
     * @return
     */
    public static String now() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    }

    public static boolean isToday(Date time) {
        Calendar now = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        return (now.get(Calendar.YEAR) == cal.get(Calendar.YEAR)) && (now.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR));
    }

    public static long getTime(Date time) {
        return time != null ? time.getTime() / 1000L : 0;
    }

    public static int getCurrentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static boolean checkToday(int time) {
        return new SimpleDateFormat("yyyy:MM:dd").format(new Date(time * 1000L)).equals(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));
    }

    /**
     * 得到一个距离当天 时间00：00的秒数
     *
     * @return
     */
    public static int getNowTime() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) * 60 * 60 + c.get(Calendar.MINUTE) * 60 + c.get(Calendar.SECOND);
    }

    static final int DAY_SECONDS = 86400;// 一天的秒数

    /**
     * 判断是否为系统时间的昨天
     *
     * @param time 秒为单位
     * @return
     */
    public static boolean isYesterday(int time) {
        return new SimpleDateFormat("yyyy:MM:dd").format(new Date((time + DAY_SECONDS) * 1000l))
                .equals(new SimpleDateFormat("yyyy:MM:dd").format(new Date()));
    }

    public static int[] getOverDay(int startTime, int endTime) {
        int result[] = new int[3];
        return result;
    }

    /**
     * 获取系统距1970年1月1日总毫秒
     *
     * @return
     */
    public static long getSysCurTimeMillis() {
        return getCalendar().getTimeInMillis();
    }

    /**
     * 获取系统距1970年1月1日总秒
     *
     * @return
     */
    public static long getSysCurSeconds() {
        return getCalendar().getTimeInMillis() / 1000;
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Timestamp getSysteCurTime() {
        Timestamp ts = new Timestamp(getCalendar().getTimeInMillis());
        return ts;
    }

    public static Timestamp getSysMonth() {
        Calendar now = getCalendar();
        now.set(Calendar.DAY_OF_MONTH, 1);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.format(now.getTime());
        return new Timestamp(now.getTimeInMillis());
    }

    /**
     * 获取指定日期距1970年1月1日总秒
     *
     * @param date
     * @return
     */
    public static long getDateToSeconds(Date date) {
        return getCalendar(date).getTimeInMillis() / 1000;
    }

    /**
     * 获取时间的秒
     *
     * @param time
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getTimeToSeconds(Time time) {
        if (time != null) {
            return time.getHours() * 3600 + time.getMinutes() * 60 + time.getSeconds();
        }
        return 0;
    }

    @SuppressWarnings("deprecation")
    public static Time getSecondsToTime(int seconds) {
        Time time = new Time(seconds / 3600, seconds % 3600 / 60, seconds % 3600 % 60);
        return time;
    }

    /**
     * 获取当前时间的秒
     *
     * @return
     */
    public static int getSysTimeSeconds() {
        Calendar cal = getCalendar();
        return cal.get(Calendar.HOUR_OF_DAY) * 3600 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
    }

    /**
     * 获取指定日期距1970年1月1日总毫秒
     *
     * @param date
     * @return
     */
    public static long getDateToMillis(Date date) {
        return getCalendar(date).getTimeInMillis();
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static int getCurrentHour() {
        return getCalendar().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获 取当前分钟
     *
     * @return
     */
    public static int getCurrentMinute() {
        return getCalendar().get(Calendar.MINUTE);
    }

    /**
     * 获取当前分钟
     *
     * @return
     */
    public static int getCurrentSecond() {
        return getCalendar().get(Calendar.SECOND);
    }

    /**
     * 获取当前天
     */
    public static int getCurrentDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 指定的毫秒long值转成Timestamp类型
     *
     * @param value
     * @return
     */
    public static Timestamp getMillisToDate(long value) {
        return new Timestamp(value);
    }

    /**
     * 当前系统时间增加值
     *
     * @param type
     * @param value
     * @return
     */
    public static Date addSystemCurTime(int type, int value) {
        Calendar cal = getCalendar();
        switch (type) {
            case Calendar.DATE:// 增加天数
                cal.add(Calendar.DATE, value);
                break;
            case Calendar.HOUR:// 增加小时
                cal.add(Calendar.HOUR, value);
                break;
            case Calendar.MINUTE:// 增加分钟
                cal.add(Calendar.MINUTE, value);
                break;
            case Calendar.SECOND:// 增加秒
                cal.add(Calendar.SECOND, value);
                break;
            case Calendar.MILLISECOND:// 增加毫秒
                cal.add(Calendar.MILLISECOND, value);
                break;
            default:
                break;
        }
        return new Date(cal.getTimeInMillis());
    }

    public static Date getNextDate() {
        Calendar cal = getCalendar();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MILLISECOND, 0);
        return new Date(cal.getTimeInMillis());
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String getDateFormat(Date date) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(date);
        return ctime;
    }

    /**
     * 获取默认日期2000-01-01
     *
     * @return 返回默认起始时间
     */
    public static Timestamp getDefaultDate() {
        Date defaultDate = null;
        try {
            defaultDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject("2000-01-01 00:00:00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(defaultDate.getTime());
    }

    /**
     * 获取默认目上限日期2999-01-01
     *
     * @return 返回默认上限时间
     */
    public static Timestamp getDefaultMaxDate() {
        Date defaultDate = null;
        try {
            defaultDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject("2999-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Timestamp(defaultDate.getTime());
    }

    /**
     * <pre>
     * 比较日期是否同一天(注意：分界线为晚上 12 点)
     * </pre>
     *
     * @param date
     * @return
     */
    public static boolean dateCompare(Date date) {
        if (date == null) {
            return false;
        }
        Calendar now = getCalendar();
        Calendar other = getCalendar(date);
        return dateCompare(now, other) == 0 ? true : false;
    }

    /**
     * <pre>
     * 比较日期是否同一天(注意：分界线为晚上 12 点)
     * </pre>
     *
     * @return
     */
    public static boolean dateCompare(long date) {
        Calendar now = getCalendar();
        Calendar other = getCalendar(getMillisToDate(date));
        return dateCompare(now, other) == 0 ? true : false;
    }

    public static boolean isSameDay(long time1, long time2) {
        if (time1 == 0 || time2 == 0) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(time1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(time2);
        int y1 = calendar1.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        return y1 == y2 && d1 == d2;
    }

    public static boolean isSameDaySix(long time1, long time2) {
        if (time1 == 0 || time2 == 0) {
            return false;
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(Math.min(time1, time2));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(Math.max(time1, time2));
        int y1 = calendar1.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int y2 = calendar2.get(Calendar.YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        if (y1 == y2 && d1 == d2) {
            if (h1 < 6 && h2 >= 6) {
                return false;
            }
            return true;
        } else if (y1 != y2 && d1 != d2) {
            if (y1 + 1 != y2) {
                return false;
            }
            if (d2 != 1) {
                return false;
            }
            if (h1 >= 6 && h2 < 6) {
                return true;
            }
            return false;
        } else if (y1 == y2 && d1 != d2) {
            if (d1 + 1 != d2) {
                return false;
            }
            if (h1 >= 6 && h2 < 6) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * <pre>
     * 比较是否为同一天(注意：分界线为凌晨 5 点)
     * </pre>
     *
     * @param date
     * @return
     */
    public static boolean dataCompare5(Date date) {
        if (date == null) {
            return false;
        }
        Calendar now = getCalendar();
        now.add(Calendar.HOUR_OF_DAY, -5);
        Calendar other = getCalendar(date);
        other.add(Calendar.HOUR_OF_DAY, -5);
        if (dateCompare(now, other) == 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * 比较日期是否同一天(注意：分界线为晚上 12 点)
     * </pre>
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean dataCompare(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar c1 = getCalendar(date1);
        Calendar c2 = getCalendar(date2);
        return dateCompare(c1, c2) == 0 ? true : false;
    }

    /**
     * 返回两个日期相差天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int dateCompare(Calendar startDate, Calendar endDate) {
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);

        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);

        int day = (int) (endDate.getTimeInMillis() / 1000 / 60 / 60 / 24 - startDate.getTimeInMillis() / 1000 / 60 / 60 / 24);
        return day;
    }

    /**
     * <pre>
     * 比较日期是否同一天(注意：分界线为晚上 12 点)
     * </pre>
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static int dateCompare(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        Calendar c1 = getCalendar(startDate);
        Calendar c2 = getCalendar(endDate);
        return dateCompare(c1, c2);
    }

    /**
     * <pre>
     * 返回两个日期相差天数(注意：分界线为凌晨 6 点)
     * </pre>
     */
    public static int dateCompare6(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        Calendar c1 = getCalendar(startDate);
        c1.add(Calendar.HOUR_OF_DAY, -6);
        Calendar c2 = getCalendar(endDate);
        c2.add(Calendar.HOUR_OF_DAY, -6);
        return dateCompare(c1, c2);
    }

    /**
     * <pre>
     * 返回两个日期相差天数(注意：分界线为凌晨 6 点)
     * </pre>
     */
    public static int dateCompare6(long startTime, long endTime) {
        if (startTime == 0 || endTime == 0) {
            return 0;
        }
        Calendar c1 = getCalendar(startTime);
        c1.add(Calendar.HOUR_OF_DAY, -6);
        Calendar c2 = getCalendar(endTime);
        c2.add(Calendar.HOUR_OF_DAY, -6);
        return dateCompare(c1, c2);
    }

    /**
     * 比较日期是否是同一个月份
     *
     * @param date 被比较的日期
     * @return
     */
    public static boolean monthCompare(Date date) {// 一年之内是否是同一个月
        if (date == null) {
            return false;
        }
        Calendar now = getCalendar();
        Calendar other = getCalendar(date);
        int nowMonth = now.get(Calendar.MONTH) + 1;
        int otherMonth = other.get(Calendar.MONTH) + 1;
        return (otherMonth - nowMonth) == 0 ? true : false;
    }

    /**
     * 获取该月的天数
     *
     * @return
     */
    public static int monthDays() {// 返回当前月份的天数
        Calendar now = getCalendar();
        return now.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前是该月的第几天
     *
     * @return
     */
    public static int monthDay() {
        Calendar now = getCalendar();
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 重置防沉迷刷新时间
     *
     * @param hour        刷新时间点
     * @param refreshTime 刷新时间引用
     */
    public static void setAASRefreshTime(int hour, Calendar refreshTime) {
        refreshTime.setTime(getSysteCurTime());
        refreshTime.set(Calendar.HOUR_OF_DAY, hour);
        refreshTime.set(Calendar.MINUTE, 0);
        refreshTime.set(Calendar.SECOND, 0);
    }

    public static long calcDistanceMillis(Date startTime, Date endTime) {
        long startSecond = getDateToSeconds(startTime);
        long endSecond = getDateToSeconds(endTime);
        return (endSecond - startSecond) * 1000;
    }

    /**
     * 间隔时间以小时为单位
     *
     * @param startDate
     * @param interval
     * @return
     */
    public static boolean isInterval(Date startDate, int interval) {
        return dataCompare5(startDate);
    }

    public static int timeToFrame(int secondTime) {
        return (secondTime * 25) / 1000;
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    private static Calendar getCalendar() {
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());
        return nowCalendar;
    }

    /**
     * 获取指定的时间
     *
     * @param date
     * @return
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取指定的时间
     *
     * @param time
     * @return
     */
    public static Calendar getCalendar(long time) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    public static Timestamp getCalendarToDate(Calendar calendar) {
        if (calendar != null) {
            return new Timestamp(getCalendar().getTimeInMillis());
        }
        return null;
    }

    public static Date addDate(Date date, long value) {
        long time = date.getTime() + value;
        return new Date(time);
    }

    public static Date getSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date monday = currentDate.getTime();
        return monday;
    }

    public static Date getNextMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date monday = currentDate.getTime();
        return monday;
    }

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static int[] getWeekHourMinute() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) {
            week = 7;
        }
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new int[]{week, hour, minute};
    }

    public static int getDayOfWeekIndex() {
        Calendar calendar = Calendar.getInstance();
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index == 0) {
            index = 7;
        }
        return index;
    }

    public static boolean isTimeOut(Date expDate) {
        Calendar curentDate = Calendar.getInstance();
        Calendar expirtDate = Calendar.getInstance();
        expirtDate.setTime(expDate);

        long intervalMillis = expirtDate.getTimeInMillis() - curentDate.getTimeInMillis();
        return intervalMillis <= 0;
    }

    public static Date getSaturday(int nextWeek) {
        int mondayPlus = getMondayPlus();
        if (nextWeek > 0) {
            mondayPlus = mondayPlus + (nextWeek * 7);
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 5);
        currentDate.set(Calendar.HOUR_OF_DAY, 5);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        Date saturday = currentDate.getTime();
        return saturday;
    }

    public static boolean isSaturday() {
        int dayIndex = getDayOfWeekIndex();
        if (6 == dayIndex) {
            return true;
        }
        return false;
    }

    public static String parseDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        return dateStr;
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = df.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isInDate(long curTimeMillis, Date openDate, Date stopDate) {
        if (openDate == null || stopDate == null) {
            return false;
        }
        long openMillis = getDateToMillis(openDate);
        long stopMillis = getDateToMillis(stopDate);
        return curTimeMillis >= openMillis && curTimeMillis <= stopMillis;
    }

    public static boolean isInDate(long curTimeMillis, Date openDate, long space) {
        if (openDate == null || space <= 0) {
            return false;
        }
        long openMillis = getDateToMillis(openDate);
        long stopMillis = openMillis + space;
        return curTimeMillis >= openMillis && curTimeMillis <= stopMillis;
    }

    public static boolean isAfter(long currentMillis, Date date) {
        if (date == null || date == null) {
            return false;
        }
        long openMillis = getDateToMillis(date);
        return currentMillis >= openMillis;
    }

    public static Date addTime(Date current, int type, int value) {
        Calendar cal = getCalendar(current);
        switch (type) {
            case Calendar.MONTH:
                cal.add(Calendar.MONTH, value);
                break;
            case Calendar.DATE:// 增加天数
                cal.add(Calendar.DATE, value);
                break;
            case Calendar.HOUR:// 增加小时
                cal.add(Calendar.HOUR, value);
                break;
            case Calendar.MINUTE:// 增加分钟
                cal.add(Calendar.MINUTE, value);
                break;
            case Calendar.SECOND:// 增加秒
                cal.add(Calendar.SECOND, value);
                break;
            case Calendar.MILLISECOND:// 增加毫秒
                cal.add(Calendar.MILLISECOND, value);
                break;
            default:
                break;
        }
        return new Date(cal.getTimeInMillis());
    }

    public static boolean isSaturday(Date date) {
        int dayIndex = getDayOfWeekIndex(date);
        if (6 == dayIndex) {
            return true;
        }
        return false;
    }

    /**
     * 当前时间是否在几点几分之后
     * @return
     */
    public static boolean isAfterHourMinute(int hour, int minute) {
        Calendar now = Calendar.getInstance();
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        if (nowHour > hour) {
            return true; //已经过时
        }
        if (nowHour < hour) {
            return false;//未过时
        }
        int nowMinute = now.get(Calendar.MINUTE);
        return nowMinute > minute;
    }

    /**
     * 今天是周几
     * @param date
     * @return
     */
    public static int getDayOfWeekIndex(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (index == 0) {
            index = 7;
        }
        return index;
    }

    /**
     * 上周一 00:00:01，譬如：当前时间 2013-05-03 返回：2013-04-29 00:00:30
     */
    public static Date getSunday(int nextWeek) {
        int mondayPlus = getMondayPlus();
        if (nextWeek > 0) {
            mondayPlus = mondayPlus + (nextWeek * 7);
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 30);
        Date saturday = currentDate.getTime();
        return saturday;
    }

    /**
     * <pre>
     * 获取 date 所在周的周日日期
     * </pre>
     *
     * @param date
     * @return
     */
    public static Date getCurrentWeekEndDate(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);

        // 取当前日期是星期几(week:星期几)
        int week = aCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        int count = 0;
        if (week == 1) {
            count = 6;
        } else if (week == 2) {
            count = 5;
        } else if (week == 3) {
            count = 4;
        } else if (week == 4) {
            count = 3;
        } else if (week == 5) {
            count = 2;
        } else if (week == 6) {
            count = 1;
        }

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK, count);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static boolean isOverTime(Date createTime, int peri) {
        return System.currentTimeMillis() - createTime.getTime() >= peri;
    }

    public static String phaseDate(long time) {
        return new SimpleDateFormat("M月d日 HH:mm").format(new Date(time)).toString();

    }

    public static String formatFullDate(long time) {
        return new SimpleDateFormat("Y年M月d日HH:mm").format(new Date(time)).toString();

    }

    public static void main(String[] aa) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        long startTime = calendar.getTimeInMillis();
        System.out.println(formatFullDate(startTime));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 5);
        long endTime = calendar1.getTimeInMillis();
        System.out.println(formatFullDate(endTime));

        System.out.println(dateCompare6(startTime, endTime));
    }

    public static String secondsFormat(long t) {
        long h = t / 60 / 60;
        long m = (t - h * 60 * 60) / 60;
        long s = (t - h * 60 * 60 - m * 60);
        String hh = h > 0 ? (h + "小时") : "";
        String mm = m > 0 ? (m + "分钟") : "";
        String ss = s > 0 ? (s + "秒") : "";
        return hh + mm + ss;

    }

    public static Date getDefault() {
        Date defaultDate = null;
        try {
            defaultDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject("2000-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return defaultDate;
    }

    public static Date getDefaultMax() {
        Date defaultDate = null;
        try {
            defaultDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject("2030-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return defaultDate;
    }

    public static int getRndFiveMinutePeriod(int period) {
        int rnd = period <= FIVE_MINUTE ? period : (MINUTE * (new Random().nextInt(FIVE_MINUTE) + 1));
        return rnd;
    }

    public static long getDoubleTimeDifferenceBySec(long time1, long time2) {

        return (time1 - time2) / 1000;
    }

    public static String getBeforeDayDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -day);
        Date time = calendar.getTime();
        String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(time);
        return yesterday;
    }

    public static String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String ctime = formatter.format(new Date());
        return ctime;
    }

    public static String getTodayDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(new Date());
        return ctime;
    }

    public static Date parse(String date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }

        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getTodayDatePushGameVersion() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder();
        builder.append(calendar.get(Calendar.YEAR)).append("年").append(calendar.get(Calendar.MONTH) + 1).append("月")
                .append(calendar.get(Calendar.DAY_OF_MONTH)).append("日");
        return builder.toString();
    }

    public static long getTimeByDateStr(String time) {
        return parseDate(time).getTime();
    }

}
