/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-01 10:49  Inc. All rights reserved.
 */
package com.myz.spring.listener;

import com.myz.spring.listener.event.CommonApplicationEvent;
import com.myz.spring.listener.event.SystemEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author maoyz
 */
@Service
public class EventPublishService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void push() {
        EventMsg msg = new EventMsg();
        msg.setId(1111L);
        msg.setDesc("处置完成");

        CommonApplicationEvent event = new CommonApplicationEvent(SystemEventType.FINISH_INCIDENT_APPEAL, msg);
        applicationEventPublisher.publishEvent(event);
        System.out.println("****************** End *******************");
    }
}