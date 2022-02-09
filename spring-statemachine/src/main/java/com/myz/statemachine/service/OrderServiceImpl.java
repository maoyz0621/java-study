/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.service;

import com.myz.statemachine.config.Order;
import com.myz.statemachine.config.OrderStateChangeEventEnum;
import com.myz.statemachine.config.OrderStateEnum;
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
    private StateMachine<OrderStateEnum, OrderStateChangeEventEnum> orderStateMachine;

    @Setter(onMethod_ = {@Autowired})
    private StateMachineFactory<OrderStateEnum, OrderStateChangeEventEnum> orderStateMachineFactory;

    @Setter(onMethod_ = {@Autowired})
    private StateMachinePersister<OrderStateEnum, OrderStateChangeEventEnum, Order> persister;

    public static final String orderStateMachineId = "orderStateMachine";


    private Map<Long, Order> orders = new HashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 5; i++) {
            Long id = Long.valueOf(i);
            orders.put(id, new Order().setId(id).setStatus(OrderStateEnum.WAIT_PAYMENT));
        }
    }

    @Override
    public Order pay(Long id) {
        Order order = new Order();
        order = orders.get(id);
        Message<OrderStateChangeEventEnum> message = MessageBuilder.withPayload(OrderStateChangeEventEnum.PAYED)
                .setHeader("order", order)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, order);
        return null;
    }

    @Override
    public Order deliver(Long id) {
        Order order = new Order();
        order = orders.get(id);
        Message<OrderStateChangeEventEnum> message = MessageBuilder.withPayload(OrderStateChangeEventEnum.DELIVERED)
                .setHeader("order", order)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, order);
        return null;
    }

    @Override
    public Order receive(Long id) {
        Order order = new Order();
        order = orders.get(id);
        Message<OrderStateChangeEventEnum> message = MessageBuilder.withPayload(OrderStateChangeEventEnum.RECEIVED)
                .setHeader("order", order)
                .build();

        // sendStateMachine(message, order);
        sendStateMachineFactory(message, order);
        return null;
    }

    private boolean sendStateMachine(Message<OrderStateChangeEventEnum> message, Order order) {

        try {
            // 1、
            orderStateMachine.start();
            // 2、
            persister.restore(orderStateMachine, order);
            // 3、
            boolean result = orderStateMachine.sendEvent(message);
            // 4、
            persister.persist(orderStateMachine, order);
            log.info("================= sendStateMachine = {} ======================", order);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean sendStateMachineFactory(Message<OrderStateChangeEventEnum> message, Order order) {
        StateMachine<OrderStateEnum, OrderStateChangeEventEnum> stateMachine = orderStateMachineFactory.getStateMachine(orderStateMachineId);
        try {
            // 1、
            stateMachine.start();
            // 2、
            persister.restore(stateMachine, order);
            // 3、
            boolean result = stateMachine.sendEvent(message);
            // 4、
            persister.persist(stateMachine, order);
            log.info("================= sendStateMachineFactory = {} ======================", order);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}