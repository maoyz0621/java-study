/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author maoyz0621 on 20-1-14
 * @version: v1.0
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();

        if (evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent) evt;

            switch (event.state()){

            }
        }
    }
}
