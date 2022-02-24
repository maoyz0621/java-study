/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.enums;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
public enum OrderStateChangeEvent {
    // 支付
    EVENT_CANCEL(0, "取消"),
    // 发货
    EVENT_SHIPPER_CANCEL(1, "货主取消"),
    // 确认收货
    EVENT_RELEASE(2, "发布"),
    EVENT_ASSIGN(3, "指派"),
    EVENT_CONFIRM(4, "确认"),
    EVENT_REASSIGN(5, "改派"),
    EVENT_DISPATCHING(6, "派单中"),
    EVENT_DISPATCH_FAILED(7, "派单失败"),
    ;

    private int code;
    private String desc;

    OrderStateChangeEvent(int code, String desc) {
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