/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * IdleStateHandler　空闲转台处理器
 * long readerIdleTime, 多长时间没有读，就会发送心跳检测包检测是否连接
 * long writerIdleTime, 多长时间没有写，就会发送心跳检测包检测是否连接
 * long allIdleTime,　多长时间没有读写
 * 触发IdleStateEvent
 *
 * @author maoyz0621 on 20-1-14
 * @version v1.0
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Channel channel = ctx.channel();

        // 判断心跳类型
        if (evt instanceof IdleStateEvent) {

            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType;

            switch (event.state()) {
                case READER_IDLE:
                    eventType = "READER_IDLE";
                    break;
                case WRITER_IDLE:
                    eventType = "WRITER_IDLE";
                    break;
                case ALL_IDLE:
                    eventType = "ALL_IDLE";
                    break;
                default:
                    return;
            }

            System.out.println(channel.remoteAddress() + " IdleStateEvent = " + eventType);
        }
    }
}
