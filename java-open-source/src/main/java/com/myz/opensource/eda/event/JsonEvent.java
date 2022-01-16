/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.event;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public class JsonEvent implements IEvent<String>, RetryEvent<String>, DelayEvent<String> {

    private String eventId;

    private String topic;

    private String tag;

    private String payload;

    private String json;

    private LocalDateTime timestamp;


    @Override
    public String eventId() {
        return this.eventId;
    }

    @Override
    public String topic() {
        return this.topic;
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
    public IEvent<String> original() {
        return null;
    }

    @Override
    public int delay() {
        return 0;
    }
}