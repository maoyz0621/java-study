package com.myz.java.study.base.thread.volatile1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * volatile　保证线程之间可见性,但不能保证原子性
 *
 * @author maoyz on 18-1-7.
 */
public class VolatileThread implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(VolatileThread.class);

    /**
     * 使用volatile修饰
     */
    // private volatile boolean flag = true;
    private boolean flag = true;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        logger.debug(Thread.currentThread().getName() + " 准备执行...");
        while (flag) {

        }
        logger.debug(Thread.currentThread().getName() + " 结束执行...");

    }
}

class Demo {

    public static void main(String[] args) throws InterruptedException {
        VolatileThread volatileThread = new VolatileThread();
        Thread thread = new Thread(volatileThread, "线程1-->");
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程修改共享数据为false
        volatileThread.setFlag(false);


        System.out.println("flag已经修改为false");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(volatileThread.getFlag());
    }
}
