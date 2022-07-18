/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.apachcommons;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * @author maoyz0621 on 2022/7/17
 * @version v1.0
 */
public class RandomStringUtilsTest {

    @Test
    public void randomAlphanumeric() {
        String random = RandomStringUtils.randomAlphanumeric(20);
        System.out.println(random);
    }
}