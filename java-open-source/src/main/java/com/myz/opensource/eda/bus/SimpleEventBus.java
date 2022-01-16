/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.bus;

import com.myz.opensource.eda.IResult;
import com.myz.opensource.eda.event.IEvent;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public class SimpleEventBus extends AbstractEventBus {

    @Override
    public IResult publish(IEvent<?> event) {
        return null;
    }
}