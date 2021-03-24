/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.listener;

import com.myz.spring.listener.event.CommonApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
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
public class MyApplicationListener implements ApplicationListener<CommonApplicationEvent>, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationListener.class);

    @Override
    public void onApplicationEvent(CommonApplicationEvent event) {
        LOGGER.info("MyApplicationListerner 接收事件: " + event);

    }

    @Override
    public int getOrder() {
        return 3;
    }
}
