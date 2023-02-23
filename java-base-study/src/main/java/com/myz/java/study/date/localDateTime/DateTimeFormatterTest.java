/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.date.localDateTime;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * LocalDate、LocalDateTime
 * DateTimeFormat
 *
 * @author maoyz0621 on 2019/3/4
 * @version: v1.0
 */
public class DateTimeFormatterTest {

    /**
     * 时间格式化
     */
    @Test
    public void testDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        LocalDate parse = LocalDate.parse("2018-12-12 12:12:12 222", formatter);
        System.out.println(parse);

        String format = LocalDate.now().format(formatter);
        System.out.println(format);

        String format1 = LocalDateTime.now().format(formatter);
        System.out.println(format1);

        LocalDateTime parse1 = LocalDateTime.parse("2018-12-12 12:12:12 222", formatter);
        System.out.println(parse1);
    }

    @Test
    public void testCalculateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("后5天时间:" + localDateTime.plusDays(5).format(formatter));
        System.out.println("前5天时间:" + localDateTime.minusDays(5).format(formatter));
        System.out.println("前1个月时间:" + localDateTime.minusMonths(1).format(formatter));
        System.out.println("后1个月时间:" + localDateTime.plusMonths(1).format(formatter));
        System.out.println("指定2020年的当前时间::" + localDateTime.withYear(2020).format(formatter));
    }

    @Test
    public void testSystem() {
        int count = 1000;
        while (count > 0) {
            System.out.println(count--);
            System.out.println(System.currentTimeMillis());
            // nanoTime纳秒，每次都没一样，可以用来计算时间差
            System.out.println(System.nanoTime());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
                    .format(System.currentTimeMillis()));
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
                    .format(System.nanoTime()));
            System.out.println("\r\n");
        }
    }

    @Test
    public void test() throws Exception {
        long start0 = System.currentTimeMillis();
        long start1 = System.nanoTime();
        Thread.sleep(1000 * ThreadLocalRandom.current().nextInt(5));
        long end0 = System.currentTimeMillis();
        long end1 = System.nanoTime();
        System.out.println("currentTimeMillis = " + (end0 - start0) / 1000);
        System.out.println("nanoTime = " + (end1 - start1) / 1000 / 1000);
    }
}
