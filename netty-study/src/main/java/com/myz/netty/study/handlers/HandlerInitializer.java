/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class HandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // pipeline.addLast("encoder", new StringEncoder());
        // pipeline.addLast("decoder", new StringDecoder());
    }
}
