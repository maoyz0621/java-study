/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.io.nio.selector;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Objects;

/**
 * 工作线程
 *
 * @author maoyz on 18-10-22
 * @version: v1.0
 */
public class WorkerThread implements Runnable {

    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    private ThreadPool threadPool;
    private SelectionKey selectionKey;

    public WorkerThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public synchronized void run() {
        for (; ; ) {
            try {
                // Sleep and release object lock
                // 休眠并且释放掉对象锁
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Objects.isNull(selectionKey)) {
                continue;
            }

            try {
                drainChannel(selectionKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

            selectionKey = null;
            this.threadPool.returnWorker(this);
        }


    }

    /**
     * 通过一个被提供SelectionKey对象的工作线程来初始化一个工作集合，这个方法是同步的，所以
     * 里面的run方法只有一个key能被服务在同一个时间，在唤醒工作线程和返回到主循环之前，这个key的
     * 感兴趣的集合被更新来删除OP_READ，这将会引起工作线程在提供服务的时候选择器会忽略读就绪的通道
     */
    public synchronized void serviceChannel(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
        selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_READ));
        // 唤醒线程
        notifyAll();
    }

    public void drainChannel(SelectionKey selectionKey) throws Exception {
        SocketChannel client = (SocketChannel) selectionKey.channel();
        int count;
        buffer.clear();
        if (Objects.nonNull(client)) {
            while ((count = client.read(buffer)) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    client.write(buffer);
                }
                buffer.clear();
            }
            if (0 > count) {
                // 读取结束后关闭通道，使key失效
                client.close();
                return;
            }
            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_READ);
            // 唤醒selector
            selectionKey.selector().wakeup();
        }
    }
}
