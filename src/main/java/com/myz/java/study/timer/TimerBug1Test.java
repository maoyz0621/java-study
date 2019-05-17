package com.myz.java.study.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 * 管理时间延迟缺陷
 * Timer内部是一个线程
 *
 * @author maoyz on 18-3-14.
 */
class TimerBug1Test {

  private Timer timer;
  public long start;

  public TimerBug1Test() {
    this.timer = new Timer();
    start = System.currentTimeMillis();
  }

  public void timerOne() {

    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }, 1000);
  }

  public void timerTwo() {

    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        System.out.println("timerTwo invoked ,the time:" + (System.currentTimeMillis() - start));
      }
    }, 3000);
  }

  /**
   * 按照我们正常思路，timerTwo应该是在3s后执行，其结果应该是：
   * timerOne invoked ,the time:1001
   * timerOne invoked ,the time:3001
   * 但是事与愿违，timerOne由于sleep(4000)，休眠了4S，同时Timer内部是一个线程，导致timeOne所需的时间超过了间隔时间
   */
  public static void main(String[] args) throws Exception {
    TimerBug1Test test = new TimerBug1Test();
    test.timerOne();
    test.timerTwo();
  }
}
