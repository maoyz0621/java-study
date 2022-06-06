/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda.predicate;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * Predicate 断言 返回true/false
 * 谓词型接口，其实只是起到一个判断作用
 *
 * @author maoyz0621 on 19-8-2
 * @version: v1.0
 */
public class PredicateTest {

    public static void main(String[] args) {

    }

    /**
     * test() 核心方法
     */
    @Test
    public void testTest() {
        Predicate<Integer> predicate = t -> t > 20;
        // false
        System.out.println(predicate.test(10));
        // false
        System.out.println(predicate.test(20));
        // true
        System.out.println(predicate.test(30));
    }

    /**
     * negate() 对test()方法取非运算
     */
    @Test
    public void testNegate() {
        Predicate<Integer> predicate = t -> t > 20;
        // true
        System.out.println(predicate.negate().test(10));
        // true
        System.out.println(predicate.negate().test(20));
        // false
        System.out.println(predicate.negate().test(30));
    }

    /**
     * and() 把传入的函数和当前函数拼接为一个新的函数，必须同时满足两个函数才会返回True
     */
    @Test
    public void testAnd() {
        Predicate<Integer> predicate1 = t -> t > 20;
        Predicate<Integer> predicate2 = t -> t % 2 == 0;
        Predicate<Integer> predicate = predicate1.and(predicate2);
        // false
        System.out.println(predicate.test(10));
        // true
        System.out.println(predicate.test(30));
        // false
        System.out.println(predicate.test(31));
    }


    /**
     * or() 把传入的函数和当前函数拼接为一个新的函数，满足其中一个函数就会返回True
     */
    @Test
    public void testOr() {
        Predicate<Integer> predicate1 = t -> t > 20;
        Predicate<Integer> predicate2 = t -> t % 2 == 0;
        Predicate<Integer> predicate = predicate1.or(predicate2);
        // true
        System.out.println(predicate.test(10));
        // true
        System.out.println(predicate.test(30));
        // true
        System.out.println(predicate.test(31));
    }

    @Test
    public void testIsEqual() {
        Predicate<String> predicate = Predicate.isEqual("a");
        System.out.println(predicate.test("ab"));
    }
}
