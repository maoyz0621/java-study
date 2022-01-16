/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.event;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public interface RetryEvent<T> extends IEvent<T>{

    int delay();
}