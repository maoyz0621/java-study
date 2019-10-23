/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用注解@EventListener 实现监听器
 *
 * @author maoyz0621 on 19-10-13
 * @version: v1.0
 */
@Component
public class ListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerService.class);

    /**
     * 参考 EventListenerMethodProcessor
     * @param event
     */
    @EventListener(classes = ApplicationEvent.class)
    public void listen(ApplicationEvent event) {
        LOGGER.info("ListenerService 监听事件" + event);
    }
}
