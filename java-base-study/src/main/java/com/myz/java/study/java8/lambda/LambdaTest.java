/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.java8.lambda;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author maoyz0621 on 19-3-24
 * @version v1.0
 */
public class LambdaTest {

    /**
     * 使用Lambda表达式
     */
    @Test
    public void testMap() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", "a1");
        map.put("b", "b1");
        map.put("c", "c1");
        map.put("d", "d1");
        map.forEach((k, v) -> System.out.println("k=" + k + ", v=" + v));
    }

    @Test
    public void testList() {
        String[] arrs = {"a", "b", "c", "d"};
        List<String> list = Arrays.asList(arrs);
        // list双冒号运算符遍历:
        list.forEach(System.out::println);
        //
        list.forEach(v -> {
            System.out.println(v.length());
        });
    }

    /**
     * Lambda 表达式与匿名类的区别
     * 使用匿名类与 Lambda 表达式的一大区别在于关键词的使用。对于匿名类，关键词 this 解读为匿名类，而对于 Lambda 表达式，关键词 this 解读为写就 Lambda 的外部类。
     */
    @Test
    public void testRunnable() {
        System.out.println(this);
        new Thread(() -> {
            System.out.println("Lambda实现Runnable");
            System.out.println(this);
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" 匿名类： " + this);
            }
        }).start();
    }
}

class Hello {
    Runnable r1 = () -> {
        System.out.println(this);
    };
    Runnable r2 = () -> {
        System.out.println(toString());
    };

    public static void main(String[] args) {
        new Hello().r1.run();
        new Hello().r2.run();
    }

    @Override
    public String toString() {
        return "Hello World";
    }
}
