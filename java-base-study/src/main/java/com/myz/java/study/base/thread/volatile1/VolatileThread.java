package com.myz.java.study.base.thread.volatile1;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * volatile　保证线程之间可见性,但不能保证原子性, 用来修饰boolean最佳
 *
 * @author maoyz on 18-1-7.
 */
@Slf4j
public class VolatileThread implements Runnable {

    /**
     * 使用volatile修饰, 确保了flag的可见性
     */
    private volatile boolean flag = true;
    // private boolean flag = true;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        log.debug("{} 准备执行...", Thread.currentThread().getName());

        while (flag) {

        }
        log.debug("{} 结束执行...", Thread.currentThread().getName());

    }

    public static void main(String[] args) throws InterruptedException {
        VolatileThread volatileThread = new VolatileThread();
        new Thread(volatileThread, "线程1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程修改共享数据为false
        volatileThread.setFlag(false);


        System.out.println("main线程将flag修改为false");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(volatileThread.getFlag());
    }

}
