/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.enums;

/**
 * @author maoyz0621 on 2022/2/23
 * @version v1.0
 */
public enum A {

    // 待支付
    WAIT_PAYMENT(0,"待支付"),
    // 待发货
    WAIT_DELIVER(1,"待发货"),
    // 待收货
    WAIT_RECEIVE(2,"待收货"),
    // 已完成
    FINISH(3,"已完成"),
    // 已取消
    CANCELED(-1,"已取消"),
    ;

    private int code;
    private String desc;

    A(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}