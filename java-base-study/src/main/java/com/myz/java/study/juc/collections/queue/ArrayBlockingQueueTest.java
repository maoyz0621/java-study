package com.myz.java.study.juc.collections.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * BlockingQueue
 * 　　阻塞队列，有界
 *
 * https://segmentfault.com/a/1190000016311925  #ArrayBlockingQueue
 *
 * @author maoyz on 18-1-11.
 */
class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        // 定义n个阻塞队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        queue.add("1");
        // 添加一个元素并返回true,如果队列已满，则返回false,设置超时时间,尝试在timeout时间内向队列尾部插入一个元素。
        // 如果成功，立即返回true；否则，当到达超时间，返回false,同理poll()
        queue.offer("2", 5, TimeUnit.SECONDS);

        // 当超出n时，抛出异常:Queue full
        // queue.add("1");

        // 阻塞,5秒之内不能添加到队列中，返回false
        queue.offer("3", 5, TimeUnit.SECONDS);

        // 数量只能为2
        System.out.println("队列数：" + queue.size());
        // 在给定的时间里，从队列中获取值，时间到了直接调用普通的poll方法，为null则直接返回null。
        System.out.println(queue.poll(3, TimeUnit.SECONDS));
        System.out.println(queue.poll(3, TimeUnit.SECONDS));
        System.out.println(queue.poll(3, TimeUnit.SECONDS));   //null
    }
}
