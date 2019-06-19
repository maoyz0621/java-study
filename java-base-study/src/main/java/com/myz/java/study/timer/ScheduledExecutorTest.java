package com.myz.java.study.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用ScheduledExecutorService替代Timer
 * newScheduledThreadPool定长线程池，可执行定时任务
 *
 * @author maoyz on 18-3-14.
 */
class ScheduledExecutorTest {

  private ScheduledExecutorService scheduExec;
  public long start;


  public ScheduledExecutorTest() {
    this.scheduExec = Executors.newScheduledThreadPool(2);
    this.start = System.currentTimeMillis();
  }

  public void timerOne() {
    scheduExec.schedule(new Runnable() {

      @Override
      public void run() {
        System.out.println("timerOne,the time:" + (System.currentTimeMillis() - start));
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, 1000, TimeUnit.MILLISECONDS);
  }

  public void timerTwo() {
    scheduExec.schedule(new Runnable() {
      @Override
      public void run() {
        System.out.println("timerTwo,the time:" + (System.currentTimeMillis() - start));
      }
    }, 2000, TimeUnit.MILLISECONDS);
  }

  public static void main(String[] args) {
    ScheduledExecutorTest test = new ScheduledExecutorTest();
    test.timerOne();
    test.timerTwo();

  }
}