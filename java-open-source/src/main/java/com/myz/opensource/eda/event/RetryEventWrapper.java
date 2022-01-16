/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.event;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public class RetryEventWrapper<T> implements RetryEvent<T>{
    @Override
    public String eventId() {
        return null;
    }

    @Override
    public String topic() {
        return null;
    }

    @Override
    public String tag() {
        return null;
    }

    @Override
    public String payload() {
        return null;
    }

    @Override
    public String json() {
        return null;
    }

    @Override
    public LocalDateTime timestamp() {
        return null;
    }

    @Override
    public IEvent<T> original() {
        return null;
    }

    @Override
    public int delay() {
        return 0;
    }
}