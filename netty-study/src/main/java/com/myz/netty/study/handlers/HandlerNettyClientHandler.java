/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 只触发下一个handler的fireChannelRead()事件
 *
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class HandlerNettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerNettyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("******************* PackageNettyClientHandler channelActive() *********************");

        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.debug("******************* PackageNettyClientHandler channelRead() *********************");

        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            LOGGER.debug("Server received: {}", buf.toString(CharsetUtil.UTF_8));
        }

        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("******************* PackageNettyClientHandler channelReadComplete() *********************");

        // ctx.writeAndFlush(Unpooled.copiedBuffer("Receive server message", CharsetUtil.UTF_8));
    }
}
