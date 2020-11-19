/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author maoyz0621 on 2020/11/11
 * @version v1.0
 */
public class TimeZoneTest {

    public static final String TIME_ZONE_GMT = "GMT+00:00";
    public static final String TIME_ZONE_BEIJING = "GMT+08:00";
    public static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND));

    /**
     * 获取日期对应时区时间
     *
     * @param pattern   格式化
     * @param timeZone  时区
     * @param timestamp 时间戳
     * @return String
     */
    public static String dateFormatFromTimestamp(String pattern, TimeZone timeZone, long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * 获取日期对应时区时间
     *
     * @param pattern   格式化
     * @param timeZone  时区
     * @param timestamp 时间戳
     * @return String
     */
    public static String dateFormatFromTimestamp(String pattern, String timeZone, long timestamp) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * Date表示当地的时间，即加上时区展示的时间
     *
     * @param timestamp 时间戳
     * @return Date
     */
    public static Date parseDate(long timestamp) {
        return new Date(timestamp);
    }

    public static Date parseDate(String date) throws ParseException {
        return parseDate(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, TimeZone.getTimeZone(TIME_ZONE_BEIJING), date);
    }

    /**
     * @param pattern  格式化
     * @param timeZone 时区
     * @param date     字符串时间
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(String pattern, TimeZone timeZone, String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.parse(date);
    }

    /**
     * @param pattern  格式化
     * @param timeZone 时区
     * @param date     字符串时间
     * @return Date
     * @throws ParseException
     */
    public static Date parseDate(String pattern, String timeZone, String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.parse(date);
    }

    /**
     * 获取日期对应的0时区时间
     *
     * @param date Date
     * @return String
     */
    public static String dateFormat(Date date) {
        return dateFormat(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, TimeZone.getTimeZone(TIME_ZONE_GMT), date);
    }

    /**
     * 获取日期对应时区时间
     *
     * @param pattern  格式化
     * @param timeZone 时区
     * @param date     Date
     * @return String
     */
    public static String dateFormat(String pattern, String timeZone, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(date);
    }

    /**
     * 获取日期对应时区时间
     *
     * @param pattern  格式化
     * @param timeZone 时区
     * @param date     Date
     * @return String
     */
    public static String dateFormat(String pattern, TimeZone timeZone, Date date) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    /**
     * 获取当前时间戳
     *
     * @return long
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳
     *
     * @return long
     */
    public static long getCurrentTime() {
        return getCurrentTimeMillis() / 1000;
    }

    public static void main(String[] args) throws ParseException {
        // Wed Nov 11 22:43:44 CST 2020  ，也就是东8区的时间
        System.out.println(parseDate(getCurrentTimeMillis()));
        // 0时区时间：2020-11-11 14:43:44
        System.out.println("0时区时间：" + dateFormatFromTimestamp(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, TIME_ZONE_GMT, getCurrentTimeMillis()));
        // 东8区时间：2020-11-11 22:43:44
        System.out.println("东8区时间：" + dateFormatFromTimestamp(DateFormatUtil.PATTERN_DEFAULT_ON_SECOND, TIME_ZONE_BEIJING, getCurrentTimeMillis()));
    }
}