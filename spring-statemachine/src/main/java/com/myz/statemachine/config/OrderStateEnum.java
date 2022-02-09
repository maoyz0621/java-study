/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
public enum OrderStateEnum {
    // 待支付
    WAIT_PAYMENT(0,"WAIT_PAYMENT"),
    // 待发货
    WAIT_DELIVER(1,"WAIT_DELIVER"),
    // 待收货
    WAIT_RECEIVE(2,"WAIT_PAYMENT"),
    // 已完成
    FINISH(3,"FINISH"),
    // 已取消
    CANCELED(-1,"CANCELED"),
    ;

    private int code;
    private String type;

    OrderStateEnum(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}