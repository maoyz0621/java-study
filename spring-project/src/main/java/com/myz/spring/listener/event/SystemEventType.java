/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-01 10:32  Inc. All rights reserved.
 */
package com.myz.spring.listener.event;

/**
 * @author maoyz
 */
public enum SystemEventType implements IEventType {

    FINISH_INCIDENT_APPEAL(3, "接警完成");

    /**
     * 消息码
     */
    private int code;
    /**
     * 内容
     */
    private String content;

    SystemEventType(int code, String content) {
        this.code = code;
        this.content = content;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getContent() {
        return content;
    }
}