/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.concurrent.limiter;

import com.vip.vjtools.vjkit.concurrent.Concurrents;
import com.vip.vjtools.vjkit.concurrent.limiter.TimeIntervalLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 19-4-26
 * @version: v1.0
 */
public class TimeIntervalLimiterTest {

    @Test
    public void test() {
        long interval = 100L;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        TimeIntervalLimiter limiter = Concurrents.timeIntervalLimiter(interval, timeUnit);
    }
}
