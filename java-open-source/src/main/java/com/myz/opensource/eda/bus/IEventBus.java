/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.bus;

import com.myz.opensource.eda.IResult;
import com.myz.opensource.eda.event.IEvent;
import com.myz.opensource.eda.sub.IEventSubscriber;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public interface IEventBus {

    /**
     * 发布
     * @param event
     * @return
     */
    IResult publish(IEvent<?> event);

    /**
     * 订阅
     * @param val
     * @param subscriber
     */
    void subscribe(String topic, IEventSubscriber<?> subscriber);
}