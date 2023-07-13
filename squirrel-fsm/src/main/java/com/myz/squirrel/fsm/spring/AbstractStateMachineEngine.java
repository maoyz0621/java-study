/**
 * Copyright 2023 Inc.
 **/
package com.myz.squirrel.fsm.spring;

import com.myz.squirrel.fsm.core.FsmContext;
import com.myz.squirrel.fsm.core.FsmEvent;
import com.myz.squirrel.fsm.core.FsmState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * @author maoyz0621 on 2023/2/27
 * @version v1.0
 */
@Slf4j
public abstract class AbstractStateMachineEngine<T extends UntypedStateMachine, S extends FsmState, E extends FsmEvent, C extends FsmContext> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected UntypedStateMachineBuilder stateMachineBuilder = null;

    public AbstractStateMachineEngine() {
        Class<T> genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractStateMachineEngine.class);
        // 创建StateMachineBuilder
        stateMachineBuilder = StateMachineBuilderFactory.create(genericType, ApplicationContext.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void fire(E event, C context) {
        T stateMachine = stateMachineBuilder.newUntypedStateMachine(context.getFromState(), applicationContext);
        // todo

        try {
            stateMachine.fire(event, context);
        } catch (Exception e) {
            log.error("stateMachine.fire error:", e);
        }
    }

    protected abstract boolean accept(S fsmState);

}