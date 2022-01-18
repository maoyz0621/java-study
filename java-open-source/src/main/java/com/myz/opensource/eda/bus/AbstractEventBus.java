/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.bus;

import com.myz.opensource.eda.sub.IEventSubscriber;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
@Slf4j
public abstract class AbstractEventBus implements IEventBus {

    protected Map<String, Set<IEventSubscriber<?>>>  eventSubscriberMap= new HashMap<>(128);

    /**
     * 注册、订阅
     * @param topic
     * @param subscriber
     */
    @Override
    public void register(String topic, IEventSubscriber<?> subscriber) {
        Set<IEventSubscriber<?>> eventSubscribers = eventSubscriberMap.getOrDefault(topic,new LinkedHashSet<>());
        eventSubscribers.add(subscriber);
        eventSubscriberMap.put(topic, eventSubscribers);
    }

    protected Set<IEventSubscriber<?>> getEventSubscriber(String topic){
        return eventSubscriberMap.get(topic);
    }
}