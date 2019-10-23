/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.math;

import org.junit.Test;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * @author maoyz0621 on 19-10-17
 * @version: v1.0
 */
public class NumberWrapperTest {

    @Test
    public void test1() {
        Long a = null;
        // java.lang.NullPointerException
        System.out.println(a == 1L);
    }

    @Test
    public void test2() {
        Long a = null;
        // java.lang.NullPointerException
        System.out.println(1L == a);
    }

    /**
     * 使用 Objects.equals()
     */
    @Test
    public void test3() {
        Long a = null;
        // fasle
        System.out.println(Objects.equals(a, 1L));
    }

    /**
     * 使用 Optional
     */
    @Test
    public void test4() {
        Long a = null;
        System.out.println(1L == Optional.ofNullable(a).orElseGet(() -> 0L));
    }
}
