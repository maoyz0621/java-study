/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.base.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 19-3-19
 * @version v1.0
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    /**
     * 通道就绪
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("=================== TimeServerHandler channelActive() =======================");

        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello !!!", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    /**
     * 通道读取事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("=================== TimeServerHandler channelRead() =======================");

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Client Receive: " + buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 处理异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    }
}
