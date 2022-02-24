/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.listener;

import com.myz.statemachine.config.OrderContext;
import com.myz.statemachine.config.OrderStateMachineFactoryConfig;
import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
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
    public boolean payTransition(Message<OrderStateChangeEvent> message) {
        log.info("***************** OrderStateFactoryListener payTransition = {} *****************", message.getHeaders().toString());
        OrderContext orderContext = (OrderContext) message.getHeaders().get("order");
        orderContext.setStatus(OrderState.STATE_DISPATCHING);
        return true;
    }

    /**
     * @param message
     * @return
     */
    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStateChangeEvent> message) {
        log.info("***************** OrderStateFactoryListener deliverTransition = {} *****************", message.getHeaders().toString());
        OrderContext orderContext = (OrderContext) message.getHeaders().get("order");
        orderContext.setStatus(OrderState.STATE_DISPATCH_FAILED);
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean finishTransition(Message<OrderStateChangeEvent> message) {
        log.info("***************** OrderStateFactoryListener finishTransition = {} *****************", message.getHeaders().toString());
        OrderContext orderContext = (OrderContext) message.getHeaders().get("order");
        orderContext.setStatus(OrderState.STATE_FINISH);
        return true;
    }
}