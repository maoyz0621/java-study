/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.eventbus.base;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maoyz0621 on 2021/12/24
 * @version v1.0
 */
@Slf4j
public class MultipleEventListener {

    /**
     * 监听 Integer 类型的消息
     */
    @Subscribe
    public void listenInteger(Integer param) {
        log.info("EventListener#listenInteger -> {}", param);
    }


    /**
     * 监听 String 类型的消息
     */
    @Subscribe
    public void listenString(String param) {
        log.info("EventListener#listenString -> {}", param);
    }

    /**
     * 监听失败事件
     * @param deadEvent
     */
    @Subscribe
    public void listenDeadEvent(DeadEvent deadEvent) {
        log.info("listenDeadEvent source = {}, event = {}", deadEvent.getSource(), deadEvent.getEvent());
    }
}