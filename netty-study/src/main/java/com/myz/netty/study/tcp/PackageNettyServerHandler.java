/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.tcp;

import com.myz.netty.study.base.handler.DiscardServerHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 20-1-9
 * @version v1.0
 */
@Slf4j
public class PackageNettyServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    /**
     * 接收拆包和粘包数据
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        logger.debug("Server received: \n{}", byteBuf.toString(CharsetUtil.UTF_8));

        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + ctx.channel().remoteAddress() + ", Welcome !!!\n", CharsetUtil.UTF_8));
    }

}
