package com.myz.java.study.juc.collections;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * CopyOnWriteArrayList: 线程安全的ArrayList
 * 数据修改都需要final transient ReentrantLock lock = new ReentrantLock();
 *
 * @author maoyz
 */
public class CopyOnWriteArrayListTest {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            100,
            150,
            5,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.AbortPolicy());

    /**
     * add()
     * set()
     * clear()
     */
    @Test
    public void testAdd() throws InterruptedException {
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<String> arrayList = new ArrayList<>();
        final int size = 2000;
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int temp = i;
            executor.execute(() -> {
                try {
                    copyOnWriteArrayList.add("a" + temp);
                    arrayList.add("a" + temp);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(copyOnWriteArrayList.size());
        System.out.println(arrayList.size());
    }

    /**
     * 迭代中修改数据 抛出UnsupportedOperationException
     */
    @Test
    public void testIterate() {
        List<String> list = Lists.newArrayList("1", "a", "c");
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(list);

        Iterator<String> iterator = copyOnWriteArrayList.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("a".equals(next)) {
                iterator.remove();
            }
        }
    }
}
