/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.bus;

import com.myz.opensource.eda.sub.IEventSubscriber;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public abstract class AbstractEventBus implements IEventBus {

    @Override
    public void subscribe(String topic, IEventSubscriber<?> subscriber) {

    }
}