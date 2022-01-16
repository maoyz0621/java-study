/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.event;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public class DelayEventWrapper<T> implements DelayEvent<T> {

    private IEvent<T> event;
    private DelayEvent<T> delayEvent;

    public DelayEventWrapper(IEvent<T> event) {
        if (event instanceof DelayEvent) {
            this.delayEvent = (DelayEvent<T>) event;
        }
        this.event = event;
    }

    @Override
    public String eventId() {
        return event.eventId();
    }

    @Override
    public String topic() {
        return event.topic();
    }

    @Override
    public String tag() {
        return event.topic();
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
}