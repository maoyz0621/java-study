/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base.string;

import org.junit.Test;

/**
 * @author maoyz0621 on 2022/7/15
 * @version v1.0
 */
public class StringReferenceTest {

    @Test
    public void test1() {
        String a = "123";
        System.out.println(a.hashCode());
        change(a);
        // 123
        System.out.println("hashCode=" + a.hashCode() + ",val=" + a);
    }

    @Test
    public void test2() {
        Integer a = 1;
        change(a);
        // 1
        System.out.println(a);
    }

    private String change(String str) {
        System.out.println(str.hashCode());
        str = "change";
        // 此时入参对象已改变
        System.out.println("此时hashCode = " + str.hashCode());
        return str;
    }

    private Integer change(Integer i) {
        i = 10;
        return i;
    }
}