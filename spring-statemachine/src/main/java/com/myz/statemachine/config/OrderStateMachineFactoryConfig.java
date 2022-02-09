/**
 * Copyright 2022 Inc.
 **/
package com.myz.statemachine.config;

import com.myz.statemachine.listener.MyStateMachineListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigBuilder;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
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
public class OrderStateMachineFactoryConfig extends EnumStateMachineConfigurerAdapter<OrderStateEnum, OrderStateChangeEventEnum> {


    public static final String orderStateMachineId = "orderStateMachineId";

    public MyStateMachineListener myStateMachineListener() {
        return new MyStateMachineListener();
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStateEnum, OrderStateChangeEventEnum> config) throws Exception {
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
    public void configure(StateMachineConfigBuilder<OrderStateEnum, OrderStateChangeEventEnum> config) throws Exception {
        super.configure(config);
    }

    /**
     * 状态机定义所有的状态和初始状态
     *
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
                contextObj.setStatus(context.getState());
            }

            /**
             * 读操作
             * @param contextObj
             * @return
             * @throws Exception
             */
            @Override
            public StateMachineContext<OrderStateEnum, OrderStateChangeEventEnum> read(Order contextObj) throws Exception {
                return new DefaultStateMachineContext<OrderStateEnum, OrderStateChangeEventEnum>(contextObj.getStatus(), null, null, null, null, orderStateMachineId);
            }
        });
    }

}