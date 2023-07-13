/**
 * Copyright 2023 Inc.
 **/
package com.myz.squirrel.fsm.config;

import com.myz.squirrel.fsm.core.FsmContext;
import com.myz.squirrel.fsm.core.FsmEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author maoyz0621 on 2023/3/1
 * @version v1.0
 */
@Slf4j
@Component
public class OrderStateMachineEngineFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void fire(FsmEvent event, FsmContext context) {
        Map<String, AbstractOrderStateMachineEngine> machineEngineMap = applicationContext.getBeansOfType(AbstractOrderStateMachineEngine.class);
        boolean accept = false;
        for (AbstractOrderStateMachineEngine machineEngine : machineEngineMap.values()) {
            machineEngine.fire(event, context);
            accept = true;
        }

        if (!accept) {
            throw new RuntimeException("");
        }

    }
}