/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.java8.localDateTime;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
     * LocalDate和LocalDateTime
     */
    @Test
    public void testLocal() {
        LocalDate now = LocalDate.now();
        // 2019-03-04
        System.out.println("当前日期：" + now);

        LocalDateTime localDateTime = LocalDateTime.now();
        // 2019-03-04T15:20:23.521
        System.out.println("当前日期和时间：" + localDateTime);
        System.out.println("======================");
        System.out.println("当前年:" + localDateTime.getYear());
        // 172
        System.out.println("当前年份天数:" + localDateTime.getDayOfYear());
        System.out.println("当前月:" + localDateTime.getMonthValue());
        System.out.println("当前时:" + localDateTime.getHour());
        System.out.println("当前分:" + localDateTime.getMinute());
        System.out.println("当前时间:" + localDateTime.toString());
    }

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

    /**
     * 通过指定年月日来创建 of()
     * Period计算间隔时间
     */
    @Test
    public void testLocalDate() {
        LocalDate date1 = LocalDate.of(2019, Month.APRIL, 24);
        LocalDate date2 = LocalDate.of(2030, Month.APRIL, 24);
        // 计算间隔时间
        Period period = Period.between(date1, date2);
        System.out.println("相差年: " + period.getYears() + " 相差月 :" + period.getMonths() + " 相差天:" + period.getDays());
    }

    /**
     * ChronoUnit日期周期单位的标准集合
     */
    @Test
    public void testChronoUnit() {
        LocalDate date1 = LocalDate.of(2019, Month.APRIL, 24);
        LocalDate date2 = LocalDate.of(2030, Month.SEPTEMBER, 23);
        // 计算间隔时间
        System.out.println("相差年数：" + ChronoUnit.YEARS.between(date1, date2));
        System.out.println("相差月数：" + ChronoUnit.MONTHS.between(date1, date2));
        System.out.println("相差天数：" + ChronoUnit.DAYS.between(date1, date2));
    }

    /**
     * Instant
     * Duration
     */
    @Test
    public void testDuration() {
        Instant instant = Instant.now();
        // 2019-03-24T15:04:00.311Z
        System.out.println("当前时间戳 :" + instant);
        Instant plus = instant.plus(Duration.ofSeconds(10));
        // 2019-03-24T15:04:10.311Z
        System.out.println("增加之后的时间 : " + plus);
        System.out.println("相差毫秒 :" + Duration.between(instant, plus).toMillis());
        System.out.println("相差秒 :" + Duration.between(instant, plus).getSeconds());
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
