/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * supervise 监督
 *
 * @author maoyz0621 on 20-2-27
 * @version v1.0
 */
public abstract class ChannelSupervise {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static ConcurrentMap<String, ChannelId> channelMap = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel) {
        channelGroup.add(channel);
        channelMap.put(channel.id().asShortText(), channel.id());
    }

    public static void removeChannel(Channel channel) {
        channelGroup.remove(channel);
        channelMap.remove(channel.id().asShortText());
    }

    public static Channel findChannel(String id) {
        return channelGroup.find(channelMap.get(id));
    }

    public static void sendToAll(TextWebSocketFrame tws) {
        channelGroup.writeAndFlush(tws);
    }

    public static void sendToOthers(Channel channel, TextWebSocketFrame tws) {
        channelGroup.forEach((ch) -> {
            if (ch != channel) {
                channel.writeAndFlush(tws);
            }
        });
    }

}
