/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.pub;

import com.myz.opensource.eda.IResult;
import com.myz.opensource.eda.event.IEvent;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public interface IEventPublisher<T> {

    IResult publish(IEvent<T> event);

}