package com.myz.java.study.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * Timer
 * 定时器
 * schedule()
 * purge()
 *
 * @author maoyz on 18-3-14.
 */
class TimerTest {

    private static Timer timer = new Timer();

    private static MyTimerTask myTimerTask = null;

    static Calendar calendar = Calendar.getInstance();
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) {
        // test();
        test1();
        // test2();
        // test3();
    }

    /**
     * schedule(TimerTask task, long delay)
     * 安排指定的任务从指定的延迟后开始执行,执行一次
     */
    private static void test() {
        String format = df.format(calendar.getTime());

        System.out.println(format);

        myTimerTask = new MyTimerTask("任务器0");

        timer.schedule(myTimerTask, 3000L);
    }

    /**
     * schedule(TimerTask task, long delay, long period)
     * 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
     */
    private static void test1() {
        String format = df.format(calendar.getTime());

        System.out.println(format);
        myTimerTask = new MyTimerTask("任务器1");

        timer.schedule(myTimerTask, 3000L, 2000L);

    }

    /**
     * schedule(TimerTask task, Date time)
     * 安排在指定的时间执行指定的任务,只执行一次
     */
    private static void test2() {
        myTimerTask = new MyTimerTask("任务器2");

        String format = df.format(calendar.getTime());

        System.out.println(format);
        // 进行时间操作
        calendar.add(Calendar.SECOND, 5);
        timer.schedule(myTimerTask, calendar.getTime());
    }

    /**
     * schedule(TimerTask task, Date firstTime, long period)
     * 安排指定的任务在指定的时间开始进行重复的固定延迟执行
     */
    private static void test3() {
        myTimerTask = new MyTimerTask("任务器3");

        String format = df.format(calendar.getTime());

        System.out.println(format);
        // 3秒后执行
        calendar.add(Calendar.SECOND, 3);
        // 每隔2秒执行一次
        timer.schedule(myTimerTask, calendar.getTime(), 2000L);
    }

}
