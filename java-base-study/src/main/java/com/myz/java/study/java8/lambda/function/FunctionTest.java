/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda.function;

import java.util.function.Function;

/**
 * @author maoyz0621 on 19-8-2
 * @version: v1.0
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, String> function = s -> s + " hello";
        String apply = function.apply("abc ->");
        // abc -> hello
        System.out.println(apply);


        Function<String, String> other = s -> s + " !!!";
        String apply1 = function.andThen(other).apply("aaaa");
        // aaaa hello !!!
        System.out.println(apply1);

        String apply2 = function.compose(other).apply("bbbb");
        // bbbb !!! hello
        System.out.println(apply2);

        Function<String, String> f1 = Function.identity();
        System.out.println(f1.apply("hahahah"));

    }
}
