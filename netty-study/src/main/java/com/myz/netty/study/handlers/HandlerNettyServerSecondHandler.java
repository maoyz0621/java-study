/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 第二个handler
 *
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class HandlerNettyServerSecondHandler extends SimpleChannelInboundHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerNettyServerSecondHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("**************** HandlerNettyServerSecondHandler channelRead() *********************");

        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;

            LOGGER.debug("Client send ByteBuf: {}", buf.toString(CharsetUtil.UTF_8));
        }
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello SecondHandler " + ctx.channel().remoteAddress(), CharsetUtil.UTF_8));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("**************** HandlerNettyServerSecondHandler channelRead0() *********************");
    }
}
