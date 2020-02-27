/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.base.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChannelInboundHandlerAdapter
 * ChannelHandlerContext, 默认是实现类　DefaultChannelHandlerContext
 * Channel和ChannelPipeline，你中有我，我中有你
 *
 * @author maoyz0621 on 19-3-19
 * @version: v1.0
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);

    /**
     * 通道读取事件
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("=========== TaskQueueServerHandler channelRead() ============");
        ByteBuf byteBuf = (ByteBuf) msg;

        logger.debug("Server received: {}", byteBuf.toString(CharsetUtil.UTF_8));
        logger.debug("Client Address: {}", ctx.channel().remoteAddress());

        // 实则　return pipeline.channel(); 其中channel中包含pipeline
        Channel channel = ctx.channel();
        System.out.println("channel: " + channel);

        // 类似双向链表结构, pipeline中包含channel
        ChannelPipeline pipeline = ctx.pipeline();
        System.out.println("pipeline:" + pipeline);

        // try {
        //
        //     while (byteBuf.isReadable()) {
        //         logger.debug("Server received: {}", byteBuf.toString(CharsetUtil.UTF_8));
        //     }
        //
        //     ctx.write(byteBuf);
        //     ctx.flush();
        // } finally {
        //     //
        //     ReferenceCountUtil.release(msg);
        // }
    }

    /**
     * 数据读完channelRead()之后，执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("=========== TaskQueueServerHandler channelReadComplete() ============");

        // ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + ctx.channel().remoteAddress() + ", Welcome !!!", CharsetUtil.UTF_8));
    }

    /**
     *
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error(cause.getMessage());

        ctx.close();
    }
}
