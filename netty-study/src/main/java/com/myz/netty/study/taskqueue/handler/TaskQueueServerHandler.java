/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.taskqueue.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * ChannelInboundHandlerAdapter
 * ChannelHandlerContext
 * Channel和ChannelPipeline，你中有我，我中有你
 *
 * @author maoyz0621 on 19-3-19
 * @version: v1.0
 */
@ChannelHandler.Sharable
public class TaskQueueServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(TaskQueueServerHandler.class);

    /**
     * 通道读取事件
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("=========== TaskQueueServerHandler channelRead() ============");

        // 都是痛的同一个Thread, nioEventLoopGroup-n
        // 1 用户自定义任务, 队列是TaskQueue
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(5 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + Thread.currentThread().getName() + " => " + ctx.channel().remoteAddress() + ", Welcome 1 " + LocalDateTime.now(), CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 此时消耗时间是在上一个的基础之上，所以这次是5+6=11s
        ctx.channel().eventLoop().execute(() -> {
            try {
                Thread.sleep(6 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + Thread.currentThread().getName() + " => " + ctx.channel().remoteAddress() + ", Welcome 2 " + LocalDateTime.now(), CharsetUtil.UTF_8));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 2 用户自定义定时任务,队列是ScheduleTaskQueue
        // 此时消耗时间是在上一个的基础之上，所以这次是5+6+6=17s
        ctx.channel().eventLoop().schedule(() -> {
            try {
                // Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello " + Thread.currentThread().getName() + " => " + ctx.channel().remoteAddress() + ", Welcome 3 " + LocalDateTime.now(), CharsetUtil.UTF_8));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 10, TimeUnit.SECONDS);


        System.out.println("============ TaskQueueServerHandler channelRead() ==============");
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
