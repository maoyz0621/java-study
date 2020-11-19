package com.myz.java.study.juc.concurrent;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * ConcurrentLinkedDeque
 * 并发队列,非阻塞
 * 无界队列，不能添加null
 *
 * @author maoyz on 18-1-11.
 */
public class ConcurrentLinkedDequeDemo {

    public static void main(String[] args) {
        // 不能设置队列大小
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<String>();

        // 添加在最后
        deque.offer("a");
        deque.offer("b");
        deque.add("c");
        // 在头部添加
        deque.offerFirst("d");
        // deque.offerFirst(null);

        // 从头部开始遍历poll(),移除头部信息
        System.out.println(deque.size());
        System.out.println(deque.poll() + "-->" + deque.size());
        System.out.println(deque.poll() + "-->" + deque.size());
        System.out.println(deque.poll() + "-->" + deque.size());
        System.out.println(deque.poll() + "-->" + deque.size());
        // null--> 0
        System.out.println(deque.poll() + "-->" + deque.size());
    }
}
