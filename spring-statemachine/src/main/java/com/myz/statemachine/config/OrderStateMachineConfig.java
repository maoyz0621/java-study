/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
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
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeEvent> {

    /**
     * 配置状态
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigBuilder<OrderState, OrderStateChangeEvent> config) throws Exception {
        super.configure(config);
    }

    /**
     * 状态机定义所有的状态和初始状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeEvent> states) throws Exception {
        states.withStates()
                // 定义状态机的初始状态
                .initial(OrderState.STATE_INIT)
                // 状态机的所有状态
                .states(EnumSet.allOf(OrderState.class));
    }

    /**
     * 配置状态机状态的迁移动作
     * <p>
     * withExternal - 表示source target两种状态不同
     * source - 当前状态
     * target - 目标状态
     * event - 导致当前变化的动作或事件
     * action - 执行当前状态变更导致的业务逻辑处理，以及出异常时的处理
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.STATE_INIT).target(OrderState.STATE_DISPATCHING).event(OrderStateChangeEvent.EVENT_CANCEL)
                .and()
                .withExternal().source(OrderState.STATE_DISPATCHING).target(OrderState.STATE_DISPATCH_FAILED).event(OrderStateChangeEvent.EVENT_SHIPPER_CANCEL)
                .and()
                .withExternal().source(OrderState.STATE_DISPATCH_FAILED).target(OrderState.STATE_FINISH).event(OrderStateChangeEvent.RECEIVED)
        ;

    }

    /**
     * 持久化配置
     * 可以结合
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderState, OrderStateChangeEvent, OrderContext> persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderState, OrderStateChangeEvent, OrderContext>() {

            /**
             * 写操作
             * @param context
             * @param contextObj
             * @throws Exception
             */
            @Override
            public void write(StateMachineContext<OrderState, OrderStateChangeEvent> context, OrderContext contextObj) throws Exception {

            }

            /**
             * 读操作
             * @param contextObj
             * @return
             * @throws Exception
             */
            @Override
            public StateMachineContext<OrderState, OrderStateChangeEvent> read(OrderContext contextObj) throws Exception {
                return new DefaultStateMachineContext<OrderState, OrderStateChangeEvent>(contextObj.getStatus(), null, null, null);
            }
        });
    }
}