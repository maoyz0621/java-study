/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChannelInboundHandlerAdapter
 * ChannelHandlerContext, 默认是实现类　DefaultChannelHandlerContext
 * Channel和ChannelPipeline，你中有我，我中有你
 *
 * @author maoyz0621 on 19-3-19
 * @version v1.0
 */
public class ProtocolServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ProtocolServerHandler.class);

    /**
     * 通道读取事件
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("=========== ProtocolServerHandler channelRead() ============");
        if (msg instanceof MessageProtocol) {
            MessageProtocol protocol = (MessageProtocol) msg;
            logger.debug("Server received: {}", protocol);
        }
    }

    /**
     * 数据读完channelRead()之后，执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("=========== ProtocolServerHandler channelReadComplete() ============");

        MessageProtocol protocol = new MessageProtocol();
        protocol.setHeader("hah");
        String msg = "Hello " + ctx.channel().remoteAddress() + ", Welcome !!!\n";
        protocol.setLength(msg.length());
        protocol.setContent(msg.getBytes());
        ctx.writeAndFlush(protocol);
    }

    /**
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage());

        if (ctx.channel().isActive()) {
            ctx.channel().close();
        }
    }
}
