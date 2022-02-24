/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource;

import com.alibaba.ttl.TtlRunnable;

/**
 * @author maoyz0621 on 2022/1/21
 * @version v1.0
 */
public class EnhancedRunnable implements Runnable {

    private final TtlRunnable ttlRunnable;

    private EnhancedRunnable(TtlRunnable ttlRunnable) {
        this.ttlRunnable = ttlRunnable;
    }

    @Override
    public void run() {
        this.ttlRunnable.run();
    }
}