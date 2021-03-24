/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.listener;

import com.myz.spring.listener.event.CommonApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.Async;
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
// @Async
@Component
public class LastApplicationListener implements ApplicationListener<CommonApplicationEvent>, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LastApplicationListener.class);


    @Override
    public void onApplicationEvent(CommonApplicationEvent event) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("LastApplicationListerner 接收事件: " + event);
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
