/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.concurrent.limiter;

import com.vip.vjtools.vjkit.concurrent.limiter.Sampler;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-26
 * @version: v1.0
 */
public class SamplerTest {

    @Test
    public void test() {
        Sampler sampler = Sampler.create(10.5);
    }
}
