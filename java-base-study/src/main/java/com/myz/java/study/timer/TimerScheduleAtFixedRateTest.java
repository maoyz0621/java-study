package com.myz.java.study.timer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

/**
 * Timer
 * 定时器
 * scheduleAtFixedRate()
 *
 * @author maoyz on 18-3-14.
 */
class TimerScheduleAtFixedRateTest {

    private static Timer timer = new Timer();

    private static MyTimerTask myTimerTask = null;

    static Calendar calendar = Calendar.getInstance();
    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) {
        // test1();
        test3();
    }


    /**
     * scheduleAtFixedRate(TimerTask task, long delay, long period)
     * 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行
     */
    private static void test1() {
        String format = df.format(calendar.getTime());

        System.out.println(format);
        myTimerTask = new MyTimerTask("任务器1");

        timer.scheduleAtFixedRate(myTimerTask, 3000L, 2000L);

    }

    /**
     * scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
     * 安排指定的任务在指定的时间开始进行重复的固定延迟执行
     */
    private static void test3() {
        myTimerTask = new MyTimerTask("任务器3");

        String format = df.format(calendar.getTime());

        System.out.println(format);
        // 时间被提前
        calendar.add(Calendar.SECOND, -5);
        // 每隔2秒执行一次
        timer.scheduleAtFixedRate(myTimerTask, calendar.getTime(), 2000L);


    }

}
