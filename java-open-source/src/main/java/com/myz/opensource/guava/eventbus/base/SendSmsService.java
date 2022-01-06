/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.guava.eventbus.base;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maoyz0621 on 2021/12/24
 * @version v1.0
 */
@Slf4j
public class SendSmsService {

    /**
     * 使用 @Subscribe 标识此方法用来处理事件消息
     * 方法参数只能为1个，且为对应要处理的事件类
     *
     * @param event
     */
    @Subscribe
    public void listen(UserCreateEvent event) {
        log.info("SendSmsService {}...", event.getMsg());
    }
}