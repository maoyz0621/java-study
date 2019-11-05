/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-10-09 17:33 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.juc.collections;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class ConcurrentHashMapTest {

    @Test
    public void test1() {
        Map a = null;
        System.out.println("null强转类型: " + (List) a);
        Map<String, Object> b = new HashMap<>();
        System.out.println("empty map: " + b.get("a"));
        // true   "null"
        System.out.println(String.valueOf(b.get("a")) instanceof String);
        // false   null
        System.out.println((String) b.get("a") instanceof String);
    }

    @Test
    public void testHashMap() {
        Map<String, Object> b = new HashMap<>(12);
        b.put(null, "a");
        b.put(null, "b");
        b.put(null, null);
        b.put("a", null);
        b.put("b", null);
        // {null=null, a=null, b=null}
        System.out.println(b);
    }

    /**
     * if (key == null || value == null) throw new NullPointerException();
     */
    @Test
    public void testConcurrentHashMap() {
        Map<String, Object> c = new ConcurrentHashMap<>(12);
        // c.put(null,"a");
        // c.put("b",null);
        // key和val = null时, java.lang.NullPointerException  原因: if (key == null || value == null) throw new NullPointerException();
        System.out.println(c);
    }

    @Test
    public void test() throws InterruptedException {
        int a = 0;
        // 创建ThreadFactory
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build();
        /* ThreadPoolExecutor(int corePoolSize,
                        int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue,
                        ThreadFactory threadFactory,
                        RejectedExecutionHandler handler)*/
        ExecutorService fixedThreadPool = new ThreadPoolExecutor(
                100,
                150,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(50),
                threadFactory,
                new ThreadPoolExecutor.DiscardOldestPolicy());
        while (a < 5) {
            Map<Integer, Integer> b = new HashMap<>();
            Map<Integer, Integer> c = new ConcurrentHashMap<>();
            for (int i = 0; i < 100; i++) {
                int temp = i;
                fixedThreadPool.execute(() -> {
                    b.put(temp, temp);
                    c.put(temp, temp);
                });

            }
            Thread.sleep(3000);
            System.out.println(b.size());
            System.out.println(c.size());
            System.out.println("-----------------------");
            a++;
        }
        ;
    }
}
