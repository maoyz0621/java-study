/**
 * Copyright 2022 Inc.
 **/
package com.myz.fms.enums;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
public enum OrderState {
    // 待支付
    STATE_INIT(0, "初始状态"),
    // 待发货
    STATE_DISPATCHING(5, "派单中"),
    // 待收货
    STATE_DISPATCH_FAILED(10, "系统派单失败"),
    // 已完成
    STATE_FINISH(15, "已完成"),
    // 已取消
    STATE_CANCELED(-1, "已取消"),
    ;

    private int code;
    private String desc;

    OrderState(int code, String desc) {
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