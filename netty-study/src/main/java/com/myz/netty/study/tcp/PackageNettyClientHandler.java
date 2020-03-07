/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.tcp;

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
public class PackageNettyClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageNettyClientHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("******************* PackageNettyClientHandler channelActive() *********************");

        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("PackageNettyClientHandler - " + i + "\n", CharsetUtil.UTF_8));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.debug("******************* PackageNettyClientHandler channelRead() *********************");

        ByteBuf byteBuf = (ByteBuf) msg;
        LOGGER.debug("received: \n{}", byteBuf.toString(CharsetUtil.UTF_8));

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // LOGGER.debug("******************* PackageNettyClientHandler channelReadComplete() *********************");
    }
}
