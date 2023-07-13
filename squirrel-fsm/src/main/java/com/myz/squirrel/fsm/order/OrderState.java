/**
 * Copyright 2022 Inc.
 **/
package com.myz.squirrel.fsm.order;


import com.myz.squirrel.fsm.core.FsmState;

/**
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
public enum OrderState implements FsmState {
    // 待支付
    STATE_INIT(0, "初始状态"),
    // 待发货
    STATE_DISPATCHING(5, "派送中"),
    // 待收货
    STATE_DISPATCH_FAILED(10, "派送失败"),
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

    @Override
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}