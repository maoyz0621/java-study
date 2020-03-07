/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 19-3-19
 * @version v1.0
 */
public class ProtocolClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ProtocolClientHandler.class);

    /**
     * 通道就绪
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("=================== ProtocolClientHandler channelActive() =======================");

        // 连续发送10次请求
        for (int i = 0; i < 10; i++) {
            MessageProtocol protocol = new MessageProtocol();
            protocol.setHeader("mao");
            String msg = "Hello - " + i + "\n";
            protocol.setLength(msg.length());
            protocol.setContent(msg.getBytes());
            ctx.writeAndFlush(protocol);
        }
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
        logger.debug("=================== ProtocolClientHandler channelRead() =======================");

        System.out.println("Client Receive: " + msg);
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
        logger.error(cause.getMessage());
        if (ctx.channel().isActive()) {
            ctx.channel().close();
        }
    }
}
