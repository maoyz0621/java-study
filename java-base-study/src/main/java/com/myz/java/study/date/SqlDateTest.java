/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-09 10:24 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.date;

import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 对应数据库中字段类型,日期，时间，时间戳
 * @author maoyz0621
 */
public class SqlDateTest {

    @Test
    public void test(){
        Date sqlDate = new Date(new java.util.Date().getTime());
        // 2019-09-18  只存储日期数据不存储时间数据
        System.out.println(sqlDate);

        Time time = new Time(new java.util.Date().getTime());
        // 14:29:24 时分秒
        System.out.println(time);

        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        // 2019-09-18 14:31:00.645 年月日时分秒纳秒
        System.out.println(timestamp);
    }
}
