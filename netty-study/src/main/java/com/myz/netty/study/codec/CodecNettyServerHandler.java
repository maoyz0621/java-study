/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class CodecNettyServerHandler extends SimpleChannelInboundHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecNettyServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("**************** HandlerNettyServerHandler channelRead0() *********************");

        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;

            LOGGER.debug("Client send ByteBuf: {}", buf.toString(CharsetUtil.UTF_8));
            LOGGER.debug("Client send ByteBuf Long: {}", buf.readLong());
        }

        // 此类型　encode不起作用
        // ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + ctx.channel().remoteAddress(), CharsetUtil.UTF_8));

        // 发送long 类型，　encode编码
        ctx.writeAndFlush(123456L);
    }
}
