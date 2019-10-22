package com.myz.java.study.base.reference;

import org.junit.Test;

/**
 * @author maoyz
 */
public class StringReferenceTest {

    /**
     * String 类型  final修饰
     */
    @Test
    public void test() {
        String temp = "1";
        System.out.println("start = " + temp.hashCode());
        reference(temp);
        System.out.println(temp);

        System.out.println("\r\n================================\r\n");

        String temp1 = null;
        reference(temp1);
        System.out.println(temp1);
    }

    private void reference(String temp) {
        temp = "aaa";
        System.out.println("end = " + temp.hashCode());
    }
}
