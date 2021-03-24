/**
 * Copyright 2019 Inc.
 **/
package com.myz.spring.listener;

import com.myz.spring.listener.event.CommonApplicationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 使用注解@EventListener 实现监听器
 *
 * @author maoyz0621 on 19-10-13
 * @version: v1.0
 */
@Component
public class ListenerEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerEventHandler.class);

    /**
     * 参考 EventListenerMethodProcessor
     * 这个处理器，是一个内部bean，在初始化的时候，会来扫描所有的@EventListener方法
     * 设置监听顺序@Order
     *
     * @param event
     */
    @Order(50)
    @EventListener(classes = CommonApplicationEvent.class)
    public void listen(CommonApplicationEvent event) {
        LOGGER.info("ListenerEventHandler 监听事件" + event);
        // throw new RuntimeException("aaa");
    }

}
