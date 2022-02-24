/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.service;

import com.myz.statemachine.config.OrderContext;
import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maoyz0621 on 2022/2/8
 * @version v1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Setter(onMethod_ = {@Autowired})
    private StateMachine<OrderState, OrderStateChangeEvent> orderStateMachine;

    @Setter(onMethod_ = {@Autowired})
    private StateMachineFactory<OrderState, OrderStateChangeEvent> orderStateMachineFactory;

    @Setter(onMethod_ = {@Autowired})
    private StateMachinePersister<OrderState, OrderStateChangeEvent, OrderContext> persister;

    public static final String orderStateMachineId = "orderStateMachine";


    private Map<Long, OrderContext> orders = new HashMap<>();

    @PostConstruct
    public void init() {
        orders.put(1L, new OrderContext().setId(1L).setStatus(OrderState.STATE_INIT));
        orders.put(2L, new OrderContext().setId(2L).setStatus(OrderState.STATE_DISPATCHING));
        orders.put(3L, new OrderContext().setId(3L).setStatus(OrderState.STATE_DISPATCH_FAILED));
        orders.put(4L, new OrderContext().setId(4L).setStatus(OrderState.STATE_FINISH));
        orders.put(5L, new OrderContext().setId(5L).setStatus(OrderState.STATE_CANCELED));
        // for (int i = 0; i < 5; i++) {
        //     Long id = Long.valueOf(i);
        //     orders.put(id, new OrderEntity().setId(id).setStatus(OrderStateEnum.WAIT_PAYMENT));
        // }
    }

    @Override
    public OrderContext pay(Long id) {
        OrderContext orderContext = new OrderContext();
        orderContext = orders.get(id);
        Message<OrderStateChangeEvent> message = MessageBuilder.withPayload(OrderStateChangeEvent.EVENT_CANCEL)
                .setHeader("order", orderContext)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, orderContext);
        return null;
    }

    @Override
    public OrderContext deliver(Long id) {
        OrderContext orderContext = new OrderContext();
        orderContext = orders.get(id);
        Message<OrderStateChangeEvent> message = MessageBuilder.withPayload(OrderStateChangeEvent.EVENT_SHIPPER_CANCEL)
                .setHeader("order", orderContext)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, orderContext);
        return null;
    }

    @Override
    public OrderContext receive(Long id) {
        OrderContext orderContext = new OrderContext();
        orderContext = orders.get(id);
        Message<OrderStateChangeEvent> message = MessageBuilder.withPayload(OrderStateChangeEvent.RECEIVED)
                .setHeader("order", orderContext)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, orderContext);
        return null;
    }

    private boolean sendStateMachine(Message<OrderStateChangeEvent> message, OrderContext orderContext) {

        try {
            // 1、
            orderStateMachine.start();
            // 2、
            persister.restore(orderStateMachine, orderContext);
            // 3、
            boolean result = orderStateMachine.sendEvent(message);
            // 4、
            persister.persist(orderStateMachine, orderContext);
            log.info("================= sendStateMachine = {} ======================", orderContext);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean sendStateMachineFactory(Message<OrderStateChangeEvent> message, OrderContext orderContext) {
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