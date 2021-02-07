/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.concurrent;

import com.vip.vjtools.vjkit.concurrent.Concurrents;
import com.vip.vjtools.vjkit.concurrent.jsr166e.LongAdder;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-28
 * @version: v1.0
 */
public class ConcurrentsTest {

    @Test
    public void test() {
        LongAdder longAdder = Concurrents.longAdder();
        // +1
        longAdder.increment();
        longAdder.add(2L);
        System.out.println(longAdder.longValue());

    }
}
