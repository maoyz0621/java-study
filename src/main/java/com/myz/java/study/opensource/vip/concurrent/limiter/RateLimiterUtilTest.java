/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.concurrent.limiter;

import com.google.common.util.concurrent.RateLimiter;
import com.vip.vjtools.vjkit.concurrent.limiter.RateLimiterUtil;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-25
 * @version: v1.0
 */
public class RateLimiterUtilTest {

    @Test
    public void test() throws ReflectiveOperationException {
        RateLimiter rateLimiter = RateLimiterUtil.create(20000, 0.1);
    }
}
