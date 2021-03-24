/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-01 10:22  Inc. All rights reserved.
 */
package com.myz.spring.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author maoyz
 */
public class CommonApplicationEvent extends ApplicationEvent {
    /**
     * 事件类型
     */
    private IEventType eventType;

    /**
     * 事件数据
     */
    private Object data;

    public CommonApplicationEvent(IEventType eventType, Object data) {
        super(data);
        this.eventType = eventType;
        this.data = data;
    }

    public IEventType getEventType() {
        return eventType;
    }

    public void setEventType(IEventType eventType) {
        this.eventType = eventType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommonApplicationEvent{");
        sb.append("eventType=").append(eventType);
        sb.append(", data=").append(data);
        sb.append(", source=").append(source);
        sb.append('}');
        return sb.toString();
    }
}