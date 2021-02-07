package com.myz.java.study.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 * 如果TimerTask抛出RuntimeException，Timer会终止所有任务的运行
 *
 * @author maoyz on 18-3-14.
 */
class TimerBug2Test {

    private Timer timer;

    public TimerBug2Test() {
        this.timer = new Timer();
    }

    public void timerOne() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, 1000, 1000);
    }

    public void timerTwo() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("我会不会执行呢？？");
            }
        }, 1000, 1000);
    }

    public static void main(String[] args) {
        TimerBug2Test test = new TimerBug2Test();
        test.timerOne();
        test.timerTwo();
    }
}
