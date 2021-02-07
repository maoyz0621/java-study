/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author maoyz0621 on 19-5-13
 * @version v1.0
 */
public class DateFormatUtil {

    public static final String PATTERN_DEFAULT_ON_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter FORMATE_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND));

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND);
        return sdf.format(date);
    }

    public static Date parseDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DEFAULT_ON_SECOND);
        return sdf.parse(strDate);
    }

    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }
}
