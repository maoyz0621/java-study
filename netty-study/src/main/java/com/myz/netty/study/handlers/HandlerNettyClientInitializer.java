/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.handlers;

import io.netty.channel.socket.SocketChannel;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class HandlerNettyClientInitializer extends HandlerInitializer {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        super.initChannel(ch);

        ch.pipeline().addLast(new HandlerNettyClientHandler());
        ch.pipeline().addLast(new HandlerNettyClientSecondHandler());
    }
}
