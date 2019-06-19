package com.myz.java.study.base.thread.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * ConcurrentHashMap
 * 线程安全的，效率高的
 * 其采用分段锁，将一个整体分成不大于16段小的HashTable,并且各自拥有自己的锁
 *
 * @author maoyz on 18-1-9.
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentMap concurrentMap = new ConcurrentHashMap<String, Object>();
        // Map<String ,Object> concurrentMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    concurrentMap.put("" + i1, i1);
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(10);

        System.out.println(concurrentMap.size());
    }
}
