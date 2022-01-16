/**
 * Copyright 2022 Inc.
 **/
package com.myz.opensource.eda.event;

import java.time.LocalDateTime;

/**
 * @author maoyz0621 on 2022/1/13
 * @version v1.0
 */
public interface IEvent<T> {

    String eventId();

    String topic();

    String tag();

    String payload();

    String json();

    LocalDateTime timestamp();

    IEvent<T> original();
}