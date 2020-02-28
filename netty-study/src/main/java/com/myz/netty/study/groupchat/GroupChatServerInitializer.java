/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.groupchat;

import io.netty.channel.socket.SocketChannel;

/**
 * @author maoyz0621 on 20-2-23
 * @version v1.0
 */
public class GroupChatServerInitializer extends BaseGroupChatServerInitializer {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        super.initChannel(ch);
        ch.pipeline().addLast(new GroupChatServerHandler());
    }

}
