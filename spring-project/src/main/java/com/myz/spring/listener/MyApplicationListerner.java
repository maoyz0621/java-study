/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义Listener
 *
 * initApplicationEventMulticaster();
 * ContextRefreshedEvent
 * 自定义ApplicationEvent
 * ContextClosedEvent
 *
 * @author maoyz0621 on 19-10-13
 * @version: v1.0
 */
@Component
public class MyApplicationListerner implements ApplicationListener<ApplicationEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationListerner.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        LOGGER.info("接收事件: " + event);
    }
}
