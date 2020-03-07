/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class PackageNettyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new PackageNettyClientHandler());
    }
}
