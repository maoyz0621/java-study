/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * @author maoyz0621 on 20-1-9
 * @version: v1.0
 */
public class PackageNettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        // 管道
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("packageNettyServerHandler", new PackageNettyServerHandler());

    }
}
