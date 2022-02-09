/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 1、@EnableStateMachine
 * 2、@EnableStateMachineFactory
 *
 * @author maoyz0621 on 2022/1/28
 * @version v1.0
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStateEnum, OrderStateChangeEventEnum> {

    /**
     * 配置状态
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigBuilder<OrderStateEnum, OrderStateChangeEventEnum> config) throws Exception {
        super.configure(config);
    }

    /**
     * 状态机定义所有的状态和初始状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStateEnum, OrderStateChangeEventEnum> states) throws Exception {
        states.withStates()
                // 定义状态机的初始状态
                .initial(OrderStateEnum.WAIT_PAYMENT)
                // 状态机的所有状态
                .states(EnumSet.allOf(OrderStateEnum.class));
    }

    /**
     * 配置状态机状态的迁移动作
     * <p>
     * source
     * target
     * event
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStateEnum, OrderStateChangeEventEnum> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStateEnum.WAIT_PAYMENT).target(OrderStateEnum.WAIT_DELIVER).event(OrderStateChangeEventEnum.PAYED)
                .and()
                .withExternal().source(OrderStateEnum.WAIT_DELIVER).target(OrderStateEnum.WAIT_RECEIVE).event(OrderStateChangeEventEnum.DELIVERED)
                .and()
                .withExternal().source(OrderStateEnum.WAIT_RECEIVE).target(OrderStateEnum.FINISH).event(OrderStateChangeEventEnum.RECEIVED)
        ;

    }

    /**
     * 持久化配置
     * 可以结合
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStateEnum, OrderStateChangeEventEnum, Order> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStateEnum, OrderStateChangeEventEnum, Order>() {

            /**
             * 写操作
             * @param context
             * @param contextObj
             * @throws Exception
             */
            @Override
            public void write(StateMachineContext<OrderStateEnum, OrderStateChangeEventEnum> context, Order contextObj) throws Exception {

            }

            /**
             * 读操作
             * @param contextObj
             * @return
             * @throws Exception
             */
            @Override
            public StateMachineContext<OrderStateEnum, OrderStateChangeEventEnum> read(Order contextObj) throws Exception {
                return new DefaultStateMachineContext<OrderStateEnum, OrderStateChangeEventEnum>(contextObj.getStatus(), null, null, null);
            }
        });
    }
}