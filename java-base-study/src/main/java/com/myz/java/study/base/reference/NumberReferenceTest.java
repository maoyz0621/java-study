package com.myz.java.study.base.reference;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本数据类型的装箱和拆箱
 * Integer total = 99;
 * 系统：Integer total = Integer.valueOf(99);
 * <p>
 * int totalprim = total;
 * 系统：int totalprim = total.intValue();
 *
 * @author maoyz
 */
public class NumberReferenceTest {

    private static final Logger logger = LoggerFactory.getLogger(NumberReferenceTest.class);

    public static void main(String[] args) {
        test0();
    }

    /**
     * 1) Integer i1=40；Java在编译的时候会执行将代码封装成Integer i1=Integer.valueOf(40); 也就是自动装箱;
     * 2) + - * / ... 运算符号都是基本类型操作
     */
    public static void test0() {
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);

        System.out.println("i1=i2\t" + (i1 == i2));     // true
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));     // true
        System.out.println("i4=i5\t" + (i4 == i5));     // false
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));     // true

        System.out.println();
    }

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

    @Test
    public void test2() {
        int temp = 1;
        reference2(temp);
        // 1
        logger.info("temp={}", temp);

        System.out.println("\r\n================================\r\n");

        int temp1 = 0;
        reference2(temp1);
        // 0
        logger.info("temp1={}", temp1);

        System.out.println("\r\n================================\r\n");

        int temp2 = 1;
        temp2 = reference2(temp2);
        logger.info("temp2={}", temp2);
    }

    private int reference2(int temp) {
        return ++temp;
    }
}
