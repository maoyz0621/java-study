/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.listener;

import com.myz.statemachine.config.Order;
import com.myz.statemachine.config.OrderStateChangeEventEnum;
import com.myz.statemachine.config.OrderStateEnum;
import com.myz.statemachine.config.OrderStateMachineFactoryConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 状态机监听器
 *
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
@Slf4j
@Component
@WithStateMachine(id = OrderStateMachineFactoryConfig.orderStateMachineId)
public class OrderStateFactoryListener {

    /**
     * 转变
     *
     * @param message
     * @return
     */
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStateChangeEventEnum> message) {
        log.info("***************** OrderStateFactoryListener payTransition = {} *****************", message.getHeaders().toString());
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStateEnum.WAIT_DELIVER);
        return true;
    }

    /**
     * @param message
     * @return
     */
    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStateChangeEventEnum> message) {
        log.info("***************** OrderStateFactoryListener deliverTransition = {} *****************", message.getHeaders().toString());
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStateEnum.WAIT_RECEIVE);
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean finishTransition(Message<OrderStateChangeEventEnum> message) {
        log.info("***************** OrderStateFactoryListener finishTransition = {} *****************", message.getHeaders().toString());
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStateEnum.FINISH);
        return true;
    }
}