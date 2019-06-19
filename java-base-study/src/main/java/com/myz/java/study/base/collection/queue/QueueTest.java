package com.myz.java.study.base.collection.queue;

import com.myz.java.study.base.collection.Book;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue： 基本上，一个队列就是一个先入先出（FIFO）的数据结构
 * Queue接口与List、Set同一级别，都是继承了Collection接口
 * LinkedList实现了Deque接口
 *
 * @author maoyz on 18-3-1.
 */
public class QueueTest {

    /**
     * 先进先出
     * offer() 入队,将指定的元素插入此队列（如果立即可行且不会违反容量限制）
     * poll() 获取并移除此队列的头，如果此队列为空，则返回 null。
     * peek() 获取但不移除此队列的头；如果此队列为空，则返回 null。
     */
    @Test
    public void testQueue() {
        Queue<Book> queue = new LinkedList<>();

        queue.offer(new Book("JAVA", 40.5));
        queue.offer(new Book("JSP", 30.6));
        queue.offer(new Book("Struct", 20.4));
        queue.offer(new Book("JAVA", 40.5));

        System.out.println(queue);

        while (null != queue.peek()) {
            System.out.println(queue);
            System.out.println(queue.size());
            System.out.println(queue.peek());
            System.out.println(queue.poll() + "\r\n");
        }

    }
}
