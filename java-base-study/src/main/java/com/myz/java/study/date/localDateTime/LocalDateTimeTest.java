/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.date.localDateTime;

import com.myz.java.study.date.DateFormatUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class LocalDateTimeTest {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTimeTest.class);

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
        LocalDate date1 = LocalDate.of(2022, Month.APRIL, 24);
        LocalDate date2 = LocalDate.of(2030, Month.SEPTEMBER, 23);
        // 计算间隔时间
        System.out.println("相差年数：" + ChronoUnit.YEARS.between(date1, LocalDateTime.now()));
        System.out.println("相差月数：" + ChronoUnit.MONTHS.between(date1, LocalDateTime.now()));
        System.out.println("相差天数：" + ChronoUnit.DAYS.between(date1, date2));
    }

    /**
     * LocalDateTime转Date
     */
    @Test
    public void LocalDateTimePlusMonths() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        // 2023-02-23 22:44:45
        logger.info("date={}", DateFormatUtil.format(date));

        Date datePlus = Date.from(localDateTime.plusMonths(2).atZone(zoneId).toInstant());
        // 2023-04-23 22:44:45
        logger.info("datePlus={}", DateFormatUtil.format(datePlus));

        Date dateSub = Date.from(localDateTime.plusMonths(-2).atZone(zoneId).toInstant());
        // 2022-12-23 22:44:45
        logger.info("dateSub={}", DateFormatUtil.format(dateSub));
    }

    /**
     * 月底的计算
     */
    @Test
    public void LocalDateTimePlusMonthsLastDay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse("2023-01-31 12:12:12", formatter);
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        // 2023-01-31 12:12:12
        logger.info("date={}", DateFormatUtil.format(date));

        Date datePlus = Date.from(localDateTime.plusMonths(1).atZone(zoneId).toInstant());
        // 2023-02-28 12:12:12
        logger.info("datePlus={}", DateFormatUtil.format(datePlus));

        Date dateSub = Date.from(localDateTime.plusMonths(-2).atZone(zoneId).toInstant());
        // 2022-11-30 12:12:12
        logger.info("dateSub={}", DateFormatUtil.format(dateSub));
    }

    /**
     * 月底的计算
     */
    @Test
    public void LocalDateTimeFirstDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        // 月的第一天，LocalTime.MIN - 00:00:00
        LocalDateTime localDateTime = LocalDateTime.of(localDate.plusMonths(2).with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MIN);
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        // 2023-04-01 00:00:00
        logger.info("date={}", DateFormatUtil.format(date));


        // 月的第一天，LocalTime.MAX - 23:59:59
        LocalDateTime end = LocalDateTime.of(localDate.plusMonths(2).with(TemporalAdjusters.firstDayOfMonth()), LocalTime.MAX);
        Date endDate = Date.from(end.atZone(zoneId).toInstant());
        // 2023-04-01 23:59:59
        logger.info("endDate={}", DateFormatUtil.format(endDate));
    }

    @Test
    public void LocalDateTimeLastDayOfMonth() {
        LocalDate localDate = LocalDate.now();
        // 月的最后一天，LocalTime.MIN - 00:00:00
        LocalDateTime localDateTime = LocalDateTime.of(localDate.with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MIN);
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        // 2023-02-28 00:00:00
        logger.info("date={}", DateFormatUtil.format(date));


        // 月的最后一天，LocalTime.MAX - 23:59:59
        LocalDateTime end = LocalDateTime.of(localDate.with(TemporalAdjusters.lastDayOfMonth()), LocalTime.MAX);
        Date endDate = Date.from(end.atZone(zoneId).toInstant());
        // 2023-02-28 23:59:59
        logger.info("endDate={}", DateFormatUtil.format(endDate));
    }

    /**
     * LocalDateTime转Date
     */
    @Test
    public void testLocalDateTime2Date() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Date date = Date.from(localDateTime.atZone(zoneId).toInstant());
        // 2023-02-23 22:44:45
        logger.info("date={}", DateFormatUtil.format(date));
    }

    /**
     * Date转LocalDateTime
     */
    @Test
    public void testDate2LocalDateTime() {
        Date date = new Date();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zoneId);
        System.out.println(localDateTime);
    }
}