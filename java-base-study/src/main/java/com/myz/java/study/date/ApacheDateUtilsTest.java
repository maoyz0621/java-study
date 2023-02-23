/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class ApacheDateUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(DateTest.class);

    @Test
    public void addMonths() {
        Date now = new Date();
        // 2023-02-23 22:04:01
        logger.info("now={}", DateFormatUtil.formatDate(now));

        Date dateAdd = DateUtils.addMonths(now, 2);
        // 2023-04-23 22:04:01
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateAdd));

        Date dateSub = DateUtils.addMonths(now, -2);
        // 2022-12-23 22:04:01
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateSub));
    }

    @Test
    public void addMonths0() throws ParseException {
        Date now = DateFormatUtil.parseDate("2023-01-30 22:04:01");

        // 2023-01-30 22:04:01
        logger.info("now={}", DateFormatUtil.formatDate(now));

        Date dateAdd = DateUtils.addMonths(now, 1);
        // 2023-02-28 22:04:01，月底
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateAdd));

        Date dateSub = DateUtils.addMonths(now, -1);
        // 2022-12-30 22:04:01
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateSub));
    }

    @Test
    public void addDays() {
        Date now = new Date();
        // 2023-02-23 22:05:52
        logger.info("now={}", DateFormatUtil.formatDate(now));

        Date dateAdd = DateUtils.addDays(now, 10);
        // 2023-03-05 22:05:52
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateAdd));

        Date dateSub = DateUtils.addDays(now, -10);
        // 2023-02-13 22:05:52
        logger.info("dateAdd={}", DateFormatUtil.formatDate(dateSub));
    }
}