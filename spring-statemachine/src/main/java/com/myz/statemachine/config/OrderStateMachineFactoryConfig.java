/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

import com.myz.statemachine.enums.OrderState;
import com.myz.statemachine.enums.OrderStateChangeEvent;
import com.myz.statemachine.listener.MyStateMachineListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 状态机工厂
 *
 * @author maoyz0621 on 2022/2/9
 * @version v1.0
 */
@Configuration
@EnableStateMachineFactory(name = "orderStateMachineFactory")
public class OrderStateMachineFactoryConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderStateChangeEvent> {


    public static final String orderStateMachineId = "orderStateMachineId";

    public MyStateMachineListener myStateMachineListener() {
        return new MyStateMachineListener();
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderStateChangeEvent> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(myStateMachineListener());
    }

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
     *
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
     * source
     * target
     * event
     * guard 看守
     * action 活动
     * withChoice()
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderState.STATE_INIT).target(OrderState.STATE_DISPATCHING).event(OrderStateChangeEvent.EVENT_ASSIGN)
                .and()
                .withExternal().source(OrderState.STATE_DISPATCHING).target(OrderState.STATE_DISPATCH_FAILED).event(OrderStateChangeEvent.EVENT_SHIPPER_CANCEL).guard(guard())
                .and()
                .withExternal().source(OrderState.STATE_DISPATCH_FAILED).target(OrderState.STATE_FINISH).event(OrderStateChangeEvent.RECEIVED).action(action())
                .and()
                //
                // .withChoice().source().first().last();
        ;

    }

    /**
     * 看守
     *
     * @return
     */
    @Bean
    public Guard<OrderState, OrderStateChangeEvent> guard() {
        return new Guard<OrderState, OrderStateChangeEvent>() {
            @Override
            public boolean evaluate(StateContext<OrderState, OrderStateChangeEvent> context) {
                return true;
            }
        };
    }

    @Bean
    public Action<OrderState, OrderStateChangeEvent> action() {
        return new Action<OrderState, OrderStateChangeEvent>() {

            @Override
            public void execute(StateContext<OrderState, OrderStateChangeEvent> context) {
                // do something
            }
        };
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
                contextObj.setStatus(context.getState());
            }

            /**
             * 读操作
             * @param contextObj
             * @return
             * @throws Exception
             */
            @Override
            public StateMachineContext<OrderState, OrderStateChangeEvent> read(OrderContext contextObj) throws Exception {
                return new DefaultStateMachineContext<OrderState, OrderStateChangeEvent>(contextObj.getStatus(), null, null, null, null, orderStateMachineId);
            }
        });
    }

}