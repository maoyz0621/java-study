/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.future;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maoyz0621 on 2021/12/21
 * @version v1.0
 */
public class MonitorTest<T> {
    private T value;
    // 等待时间
    private long seconds;

    private final Monitor monitor = new Monitor();
    private final Monitor.Guard valuePresent = monitor.newGuard(() -> value != null);
    private final Monitor.Guard valueAbsent = monitor.newGuard(() -> value == null);

    public MonitorTest() {
        this(2);
    }

    public MonitorTest(long seconds) {
        this.seconds = seconds;
    }

    ///////////////////////////////////// enterWhen start ///////////////////////////////////////////////////
    public T get() throws InterruptedException {
        monitor.enterWhen(valuePresent, Duration.ofSeconds(seconds));
        try {
            T result = value;
            value = null;
            return result;
        } finally {
            monitor.leave();
        }
    }

    public void set(T newValue) throws InterruptedException {
        monitor.enterWhen(valueAbsent, Duration.ofSeconds(seconds));
        try {
            value = newValue;
        } finally {
            monitor.leave();
        }
    }
    ///////////////////////////////////// enterWhen end ///////////////////////////////////////////////////


    ///////////////////////////////////// tryEnter start ///////////////////////////////////////////////////
    public T getWithTryEnter() throws InterruptedException {
        T result = null;
        // 尝试进入Monitor块，true表示可以进入, false表示不能，并且不会一直阻塞
        if (monitor.tryEnter()) {
            monitor.enterWhen(valuePresent, Duration.ofSeconds(seconds));
            try {
                result = value;
                value = null;
                return result;
            } finally {
                monitor.leave();
            }
        }
        return result;
    }

    public void setWithTryEnter(T newValue) throws InterruptedException {
        if (monitor.tryEnter()) {
            monitor.enterWhen(valueAbsent, Duration.ofSeconds(seconds));
            try {
                value = newValue;
            } finally {
                monitor.leave();
            }
        }
    }
    ///////////////////////////////////// tryEnter end ///////////////////////////////////////////////////


    ///////////////////////////////////// enterIf start ///////////////////////////////////////////////////
    public T getWithEnterIf() {
        T result = null;
        // 根据条件尝试进入Monitor块
        if (monitor.enterIf(valuePresent, Duration.ofSeconds(seconds))) {
            try {
                result = value;
                value = null;
                return result;
            } finally {
                monitor.leave();
            }
        } else {
            return result;
        }
    }

    public void setWithEnterIf(T newValue) {
        if (monitor.enterIf(valuePresent, Duration.ofSeconds(seconds))) {
            try {
                value = newValue;
            } finally {
                monitor.leave();
            }
        }
    }
    ///////////////////////////////////// enterIf end ///////////////////////////////////////////////////


    private static final Logger log = LoggerFactory.getLogger(MonitorTest.class);
    final static ThreadPoolExecutor executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            60,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("ListeningExecutor-thread-%d").build());

    public static void main(String[] args) throws InterruptedException {
        monitorTest1();
        // monitorSampleTest();
    }


    static class MonitorSample<T> {
        private final BlockingQueue<T> val = new LinkedBlockingQueue<>();
        private final Integer MAX_SIZE = 5;
        private final Monitor monitor = new Monitor();
        private final Monitor.Guard guardOffer = monitor.newGuard(() -> val.size() < MAX_SIZE);
        private final Monitor.Guard guardTake = monitor.newGuard(() -> !val.isEmpty());

        private long seconds;

        public MonitorSample() {
            this(4);
        }

        public MonitorSample(long seconds) {
            this.seconds = seconds;
        }

        public T take() throws InterruptedException {
            monitor.enterWhen(guardTake, Duration.ofSeconds(seconds));
            try {
                return val.take();
            } finally {
                monitor.leave();
            }
        }

        public void offer(T newValue) throws InterruptedException {
            monitor.enterWhen(guardOffer, Duration.ofSeconds(seconds));
            try {
                this.val.offer(newValue);
            } finally {
                monitor.leave();
            }
        }
    }

    private static void monitorSampleTest() {
        MonitorSample<Integer> monitor = new MonitorSample<>(10);
        // take值
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    Integer a = monitor.take();
                    log.info("a = {}", a);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            });

            // offer值
            int v = i;
            executor.submit(() -> {
                try {
                    monitor.offer(v);
                    log.info("set val = {}", v);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            });
        }
    }

    private static void monitorTest1() {
        MonitorTest<Integer> monitor = new MonitorTest<>(2);

        // get值
        for (int i = 0; i < 10; i++) {
            int v = i;
            executor.submit(() -> {
                try {
                    Integer a = monitor.get();
                    log.info("a = {}", a);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            });

            // set值
            executor.submit(() -> {
                try {
                    monitor.set(v);
                    log.info("set val = {}", v);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            });
        }
    }
}