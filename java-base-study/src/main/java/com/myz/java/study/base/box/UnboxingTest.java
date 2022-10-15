/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.base.box;

import org.junit.Test;

/**
 * @author maoyz0621 on 2022/7/18
 * @version v1.0
 */
public class UnboxingTest {

    /**
     * Unboxing of 'null' may produce 'NullPointerException'
     */
    @Test
    public void test() {
        Long b;
        Long a;
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                a = null;
                b = null;
            } else {
                a = 1L;
                b = 10L;
            }
            Integer val = (a == null) ?
                    ((b == null) ? null : b.intValue())
                    : a.intValue();
            System.out.println(val);
        }
    }

    /**
     * PNE，原因：
     */
    @Test
    public void test1(){
        int simpleInt = 66;
        Integer nullInteger = null;
        Integer result = true ? nullInteger : simpleInt;
        System.out.println(result);
    }

}