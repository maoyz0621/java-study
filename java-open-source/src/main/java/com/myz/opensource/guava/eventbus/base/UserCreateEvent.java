/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.eventbus.base;

/**
 * @author maoyz0621 on 2021/12/24
 * @version v1.0
 */
public class UserCreateEvent {

    private String msg;

    public UserCreateEvent() {
    }

    public UserCreateEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}