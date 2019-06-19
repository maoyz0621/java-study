package com.myz.java.study.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz on 18-4-3.
 */
public class TimerWithScheduledExecutor implements Runnable {
    private String jobName = "";

    public TimerWithScheduledExecutor(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("execute " + jobName);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1,每次执行时间为上一次任务开始起向后推一个时间间隔
        service.scheduleAtFixedRate(
                new TimerWithScheduledExecutor("job1"), initialDelay1,
                period1, TimeUnit.SECONDS);

        long initialDelay2 = 1;
        long delay2 = 1;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        service.scheduleWithFixedDelay(
                new TimerWithScheduledExecutor("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
    }
}
