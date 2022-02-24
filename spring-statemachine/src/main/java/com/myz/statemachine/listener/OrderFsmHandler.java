/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.listener;

import com.myz.statemachine.config.OrderContext;
import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

/**
 * @author maoyz0621 on 2022/2/22
 * @version v1.0
 */
@Slf4j
@Component
public class OrderFsmHandler {

    public static final String orderStateMachineId = "orderStateMachine";
    private OrderContext orderContext;

    @Setter(onMethod_ = {@Autowired})
    private StateMachineFactory<OrderState, OrderStateChangeEvent> orderStateMachineFactory;

    @Setter(onMethod_ = {@Autowired})
    private StateMachinePersister<OrderState, OrderStateChangeEvent, OrderContext> persister;

    public OrderFsmHandler() {
    }

    public OrderFsmHandler(OrderContext orderContext) {
        this.orderContext = orderContext;
    }

    public boolean sendStateMachineFactory(Message<OrderStateChangeEvent> message, OrderContext orderContext) {
        StateMachine<OrderState, OrderStateChangeEvent> stateMachine = orderStateMachineFactory.getStateMachine(orderStateMachineId);
        try {
            // 1、
            stateMachine.start();
            // 2、
            persister.restore(stateMachine, orderContext);
            // 3、
            boolean result = stateMachine.sendEvent(message);
            // 4、
            persister.persist(stateMachine, orderContext);
            log.info("================= sendStateMachineFactory = {} ======================", orderContext);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}