/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author maoyz0621 on 20-1-9
 * @version: v1.0
 */
public class HttpNettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        // 管道
        ChannelPipeline pipeline = channel.pipeline();

        // HttpServerCodec 处理http的变阿妈解码器, HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder>
        // pipeline.addLast("httpRequestDecoder", new HttpRequestDecoder());
        // pipeline.addLast("httpResponseEncoder",new HttpResponseEncoder());
        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        pipeline.addLast("httpNettyServerHandler", new HttpNettyServerHandler());

    }
}
