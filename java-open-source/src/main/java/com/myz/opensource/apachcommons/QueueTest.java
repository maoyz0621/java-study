package com.myz.opensource.apachcommons;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.NotNullPredicate;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.collections4.queue.PredicatedQueue;
import org.apache.commons.collections4.queue.UnmodifiableQueue;
import org.junit.Test;

import java.util.Queue;

/**
 * CircularFifoQueue　循环队列
 * UnmodifiableQueue只读队列
 * PredicatedQueue 断言队列
 *
 * @author maoyz on 18-3-5.
 */
public class QueueTest {

    /**
     * 循环队列
     */
    @Test
    public void testCircular() {
        // 长度是2，因此只能保留两个，循环着走。
        CircularFifoQueue<String> que = new CircularFifoQueue<>(2);
        que.add("demo");
        que.add("b");
        que.add("c");
        que.add("d");

        for (int i = 0; i < que.size(); i++) {
            System.out.println(que.get(i));
        }
    }

    /**
     * 只读队列
     */
    @Test
    public void testReadOnly() {
        Queue<String> que = new CircularFifoQueue<>(2);
        que.add("demo");
        que.add("b");
        que.add("c");
        // UnmodifiableQueue只读队列
        Queue<String> readOnlyOne = UnmodifiableQueue.unmodifiableQueue(que);
        // java.lang.UnsupportedOperationException
        readOnlyOne.add("d");
    }

    /**
     * 断言队列
     */
    @Test
    public void testPredicate() {
        //循环队列
        CircularFifoQueue<String> que = new CircularFifoQueue<>(2);
        que.add("demo");
        que.add("b");
        que.add("c");
        // 非空断言
        Predicate notNull = NotNullPredicate.notNullPredicate();
        // 包装成对应的队列
        Queue<String> NotNullque = PredicatedQueue.predicatedQueue(que, notNull);
        //java.lang.IllegalArgumentException
        NotNullque.add(null);
    }

}
