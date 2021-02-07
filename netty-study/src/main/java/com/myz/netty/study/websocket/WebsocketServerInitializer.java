/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.websocket;

import com.myz.netty.study.groupchat.BaseGroupChatServerInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author maoyz0621 on 20-2-23
 * @version v1.0
 */
public class WebsocketServerInitializer extends BaseGroupChatServerInitializer {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        // 块模式写
        pipeline.addLast(new ChunkedWriteHandler());

        // http数据传输过程中是分段的，可能发送多次请求，将多个段聚合起来
        pipeline.addLast(new HttpObjectAggregator(65536));

        // 1 websocket以　帧　的形式传递
        // 2 WebSocketFrame
        // 3 请求url: ws://127.0.0.1:8880/talk
        // 4 WebSocketServerProtocolHandler将http协议升级为ws协议，保持长连接
        // 5 通过一个状态码　101  Switching Protocols
        pipeline.addLast("protocolHandler", new WebSocketServerProtocolHandler("/talk"));

        pipeline.addLast("websocketServerHandler", new WebsocketServerHandler());
    }

}
