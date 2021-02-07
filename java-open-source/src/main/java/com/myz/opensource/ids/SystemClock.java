/*
 * Copyright (C) 2020, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2020-12-24 16:55  Inc. All rights reserved.
 */
package com.myz.opensource.ids;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author maoyz
 */
public class SystemClock {

    private final long period;
    private final AtomicLong now;

    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduledClockUpdating();
    }

    public static SystemClock getInstance() {
        return SystemClockEnum.SYSTEM_CLOCK.getInstance();
    }

    public static long now() {
        return getInstance().now.get();
    }

    private enum SystemClockEnum {
        SYSTEM_CLOCK;
        private SystemClock systemClock;

        SystemClockEnum() {
            systemClock = new SystemClock(1);
        }

        public SystemClock getInstance() {
            return systemClock;
        }
    }

    private void scheduledClockUpdating() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor((runnable) -> {
            Thread thread = new Thread(runnable, "System Clock");
            thread.setDaemon(true);
            return thread;
        });

        executor.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), period, period, TimeUnit.MILLISECONDS);
    }
}