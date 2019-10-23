/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.base.thread.volatile1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * todo volatile 禁止重排序,保证有序性
 *
 * @author maoyz0621 on 19-9-27
 * @version: v1.0
 */
public class VolatileOrderly {

    static Integer x, y = 0;
    static volatile Integer x1, y1 = 0;

    public static void main(String[] args) throws InterruptedException {
        main0();
        // main1();
    }

    private static void main0() throws InterruptedException {
        Map<String, Integer> map = new HashMap<>(2);
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            x = 0;
            y = 0;
            map.clear();

            Thread one = new Thread(() -> {
                int a = y;
                x = 1;
                map.put("a", a);
            });

            Thread two = new Thread(() -> {
                int b = x;
                y = 1;
                map.put("b", b);
            });

            one.start();
            two.start();
            one.join();
            two.join();

            set.add("a=" + map.get("a") + ", b=" + map.get("b"));
            System.out.println(set);
        }
    }

    private static void main1() throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>(2);
            x1 = 0;
            y1 = 0;
            map.clear();

            Thread one = new Thread(() -> {
                int a = y1;
                x1 = 1;
                map.put("a", a);
            });

            Thread two = new Thread(() -> {
                int b = x1;
                y1 = 1;
                map.put("b", b);
            });

            one.start();
            two.start();
            one.join();
            two.join();
            System.out.println(map);
        }
    }
}
