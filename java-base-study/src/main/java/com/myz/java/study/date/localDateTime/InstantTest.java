/**
 * Copyright 2023 Inc.
 **/
package com.myz.java.study.date.localDateTime;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

/**
 * @author maoyz0621 on 2023/2/23
 * @version v1.0
 */
public class InstantTest {

    /**
     * Instant
     * Duration
     */
    @Test
    public void testDuration() {
        Instant instant = Instant.now();
        // 22023-02-23T15:09:00.504441700Z
        System.out.println("当前时间戳 :" + instant);
        // 1677164940
        System.out.println("当前时间获取秒数: " + instant.getEpochSecond());
        // 1677164940504
        System.out.println("当前时间获取毫秒数:" + instant.toEpochMilli());

        Instant plus = instant.plus(Duration.ofSeconds(10));
        // 2023-02-23T15:09:10.504441700Z
        System.out.println("增加之后的时间 : " + plus);
        System.out.println("相差毫秒 :" + Duration.between(instant, plus).toMillis());
        System.out.println("相差秒 :" + Duration.between(instant, plus).getSeconds());
    }
}