/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 20-1-13
 * @version: v1.0
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatServerHandler.class);

    /**
     * 全局事件处理器,单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 实现消息转发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("================ GroupChatServerHandler channelRead0() ===============");
        Channel channel = ctx.channel();
        if (msg instanceof String) {
            // ChannelGroup遍历->排除自身
            channelGroup.forEach((ch) -> {
                if (channel != ch) {
                    ch.writeAndFlush(channel.remoteAddress().toString().replace("/", "") + " say: " + msg);
                }
            });
        }

    }

    /**
     * 1 建立连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.debug("================ GroupChatServerHandler handlerAdded() ===============");

        Channel channel = ctx.channel();
        // 所有channel都发送消息
        channelGroup.writeAndFlush("用户[" + channel.remoteAddress() + "]　上线了");
        // 加入ChannelGroup中
        channelGroup.add(channel);

    }

    /**
     * 上线状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("================ GroupChatServerHandler channelActive() ===============");

        System.out.println("用户[" + ctx.channel().remoteAddress() + "]　上线了");
    }

    /**
     * 离线状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("================ GroupChatServerHandler channelInactive() ===============");

        System.out.println("用户[" + ctx.channel().remoteAddress() + "]　离线了");
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.debug("================ GroupChatServerHandler handlerRemoved() ===============");

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("用户[" + channel.remoteAddress() + "]　断开连接");
        System.out.println("channelGroup = " + channelGroup);
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
        ctx.close();
    }
}
