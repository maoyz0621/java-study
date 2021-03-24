/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-01 10:12  Inc. All rights reserved.
 */
package com.myz.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 容器关闭
 * @author maoyz
 */
@Component
public class CloseRunner implements ApplicationListener<ContextClosedEvent> {

    /**
     * 该事件，是在上下文初始化完成后被发布。
     * <p>
     * 这样的话，就能保证，在我们listener监听到这个事件的时候，整个应用上下文已经可以使用了。
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //root application context，因为是web项目，
        if (event.getApplicationContext().getParent() == null) {
            System.out.println("ContextClosedEvent ......");
        }

    }
}