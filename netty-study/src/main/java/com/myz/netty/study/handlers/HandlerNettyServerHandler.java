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
 * 关注以fire开头的方法，fire表示inbound事件，当调用fireXXX方法的时候，表示触发下一个handler中的XXX事件,手动调用
 * 第一个handler
 *
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class HandlerNettyServerHandler extends SimpleChannelInboundHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerNettyServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("**************** HandlerNettyServerHandler channelRead() *********************");

        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            LOGGER.debug("Client send ByteBuf: {}", buf.toString(CharsetUtil.UTF_8));

            // 在原有基础之上添加 Hello World + "", 如果替换原有内容，先使用clear()
            buf.writeBytes(" HandlerNettyServerHandler handle".getBytes());
        }

        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + ctx.channel().remoteAddress(), CharsetUtil.UTF_8));

        // 通知执行下一个InboundHandler
        ctx.fireChannelRead(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("**************** HandlerNettyServerHandler channelRead0() *********************");

    }

    /**
     * handlerAdded方法中加入其它的handler
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.pipeline().addLast(new HandlerNettyServerLastHandler());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("Netty Error:", cause);
        if (ctx.channel().isActive()) {
            ctx.channel().close();
        }
    }
}
