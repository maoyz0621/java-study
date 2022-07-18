/**
 * Copyright 2022 Inc.
 **/
package com.myz.java.study.java8.lambda.test;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author maoyz0621 on 2022/6/21
 * @version v1.0
 */
public class AM<T> {

    public <R> R a(Function<T, R> function, T t) {
        return function.apply(t);
    }

    public void a1(Consumer<T> consumer, T t) {
        consumer.accept(t);
    }

    public <T> T a2(Supplier<T> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        AM am = new AM();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a1");
        TestService test1 = new TestService();
        Object a = am.a(new Function<TestService, String>() {

            @Override
            public String apply(TestService testService) {
                return testService.mo(hashMap);
            }

        }, test1);
        System.out.println(a);

        am.a1(new Consumer<TestService>() {
            @Override
            public void accept(TestService testService) {
                testService.mo1(hashMap);
            }
        }, test1);


        am.a2(new Supplier<String>() {
            @Override
            public String get() {
                return test1.mo(hashMap);
            }
        });
    }
}