/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.number;

import com.vip.vjtools.vjkit.number.RandomUtil;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-24
 * @version: v1.0
 */
public class RandomUtilTest {

    @Test
    public void getRandom() {
        System.out.println(RandomUtil.secureRandom().nextInt());
        System.out.println(RandomUtil.threadLocalRandom().nextInt());
        System.out.println(RandomUtil.secureRandom().nextInt(10));
        System.out.println(RandomUtil.nextInt(10));
        System.out.println(RandomUtil.nextInt(10, 20));
    }

    @Test
    public void generateString() {
        System.out.println(RandomUtil.randomStringFixLength(5));
        System.out.println(RandomUtil.randomAsciiRandomLength(5, 10));
        System.out.println(RandomUtil.randomLetterFixLength(5));
        System.out.println(RandomUtil.randomAsciiFixLength(5));

    }
}
