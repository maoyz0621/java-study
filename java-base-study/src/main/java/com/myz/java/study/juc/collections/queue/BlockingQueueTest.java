/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-18 15:05 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.juc.collections.queue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * https://juejin.im/post/5b5dcf33f265da0f51406e31#heading-14
 * @author maoyz
 */
public class BlockingQueueTest {

    /**
     * 数组实现的有界阻塞队列，默认采用不公平访问，因为公平性通常会降低吞吐量
     * 使用Condition的等待、唤醒机制，完成可阻塞式的入队和出队
     * offer() 返回boolean
     * put() void throws
     */
    @Test
    public void arrayBlockingQueueTest() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        boolean offer = queue.offer("1");
        queue.put("2");
        System.out.println(queue);
        queue.take();
        System.out.println(queue);
    }

    /**
     * 链表实现的有界阻塞队列
     * 维护两个锁在入队和出队时保证线程安全，两个锁降低线程由于线程无法获取lock而进入WAITING状态的可能性提高了线程并发执行的效率，
     * 并且count属性使用AtomicInteger原子操作类（可能两个线程一个出队一个入队操作count，各自的锁显然起不到用处）
     */
    @Test
    public void linkedBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("a");
        System.out.println(queue);
        System.out.println("出列：" + queue.take());
        System.out.println(queue);
    }

    /**
     * 优先级的  无界阻塞队列
     * 默认情况下元素采取自然顺序升序排序，也可以通过构造函数来指定Comparator来对元素进行排序，需要注意的是PriorityBlockingQueue不能保证同优先级元素的顺序
     */
    @Test
    public void priorityBlockingQueueTest() throws InterruptedException {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>(12);
        for (int i = 0; i < 100; i++) {
            queue.put("a" + i);
        }
        System.out.println(queue);
    }

    /**
     * 支持延时获取元素的无界阻塞队列,
     * DelayQueue队列中的元素必须实现Delayed接口
     */
    @Test
    public void delayQueueTest() {
        BlockingQueue queue = new DelayQueue<>();
    }
}
