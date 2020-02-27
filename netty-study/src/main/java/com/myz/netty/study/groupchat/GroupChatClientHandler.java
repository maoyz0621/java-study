/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.groupchat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author maoyz0621 on 20-1-13
 * @version: v1.0
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler {

    private static final Logger logger = LoggerFactory.getLogger(GroupChatClientHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String){
            System.out.println(msg);
            return;
        }

        ByteBuf content = (ByteBuf) msg;
        System.out.println(content.toString(CharsetUtil.UTF_8));
    }

}
