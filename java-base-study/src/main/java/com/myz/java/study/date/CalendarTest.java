/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class CalendarTest {

    @Test
    public void testCalendar() {
        // abstract类,GregorianCalendar
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getClass().getName());
        // getTIme()返回Date()
        Date date = calendar.getTime();
        System.out.println(date);
        // calendar.set(2016, 1, 2);
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        // 天数超过30(31)天时,自动进位
        calendar.set(Calendar.DATE, 32);
        System.out.println(calendar.getTime());
        int year = calendar.get(Calendar.YEAR);
        System.out.println("年份:" + year);
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println("月份:" + month);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("日期:" + day);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println("第几周:" + week);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("周几:" + weekDay);

        System.out.println("-----------------");

        // 输出2017年每个月的天数
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 2);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println(days);

        System.out.println("-----------------");

        // 一年零两个月后日期
        calendar.set(2018, Calendar.FEBRUARY, 19);
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, 2);
        System.out.println(calendar.getTime());

        System.out.println("-----------------");

        // 130天后日期
        calendar.add(Calendar.DAY_OF_YEAR, 130);
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }
}