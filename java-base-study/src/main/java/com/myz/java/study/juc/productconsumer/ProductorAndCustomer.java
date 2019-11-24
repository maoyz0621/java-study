package com.myz.java.study.juc.productconsumer;
/**
 * @ClassName: ProductorAndCustomer
 * @Description: 生产者与消费者
 * 使用阻塞队列BlockingQueue
 * @author xuwt
 * @date 2017年6月20日 下午2:50:28
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
class Producter implements Runnable {

    /**
     * 传入阻塞队列
     */
    private BlockingQueue<Object> queue;
    private volatile boolean flag = true;
    private AtomicInteger count = new AtomicInteger(0);

    public Producter(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("生产者开始生产...");
            while (flag) {
                String data = count.incrementAndGet() + "";
                try {
                    // 判断队列是否入列
                    boolean isAdd = queue.offer(data, 2, TimeUnit.SECONDS);
                    if (isAdd) {
                        System.out.println("生产者生产了:" + data);
                    } else {
                        System.out.println("生产者失败了");
                        // 停止
                        stopThread();
                    }
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println("生产者结束了");
        }
    }

    /**
     * 停止信号
     */
    private void stopThread() {
        this.flag = false;
    }
}


/**
 * 消费者
 */
class Customer implements Runnable {

    private BlockingQueue<Object> queue;

    private volatile boolean flag = false;


    public Customer(BlockingQueue<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("消费者开始消费了...");
            while (!flag) {
                // 取出数值，并删除,poll(long timeout, TimeUnit unit)
                String data = (String) queue.poll(2, TimeUnit.SECONDS);
                if (data != null) {
                    System.out.println("消费者消费了:" + data);
                } else {
                    System.out.println("消费者失败了:" + data);
                    beginThread();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("消费者结束了");
        }

    }

    /**
     * 停止信号
     */
    private void beginThread() {
        this.flag = true;
    }
}

public class ProductorAndCustomer {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Object> queue = new LinkedBlockingDeque<Object>(5);

        Runnable p1 = new Producter(queue);
        Runnable p2 = new Customer(queue);

        Thread t1 = new Thread(p1);
        Thread t11 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t11.start();
        t2.start();

        Thread.sleep(10 * 1000);
        t1.stop();
        t11.stop();
    }

}


