package com.myz.java.study.base.reference;

import org.junit.Test;

/**
 * @author maoyz
 */
public class NumberReferenceTest {

    /**
     * 基本数据类型 包装类 final修饰
     */
    @Test
    public void test() {
        Integer temp = 1;
        System.out.println("start = " + temp.hashCode());
        reference(temp);
        System.out.println(temp);

        System.out.println("\r\n================================\r\n");

        Integer temp1 = null;
        reference(temp1);
        System.out.println(temp1);
    }

    private void reference(Integer temp) {
        temp = 11;
        System.out.println("end = " + temp.hashCode());
    }


    /**
     * 基本数据类型
     */
    @Test
    public void test1() {
        int temp = 1;
        reference1(temp);
        System.out.println(temp);

        System.out.println("\r\n================================\r\n");

        int temp1 = 0;
        reference1(temp1);
        System.out.println(temp1);
    }

    private int reference1(int temp) {
        temp = 11;
        return temp;
    }
}
