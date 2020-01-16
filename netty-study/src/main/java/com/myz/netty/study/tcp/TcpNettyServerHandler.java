/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Arrays;
import java.util.List;


/**
 * SimpleChannelInboundHandler extends ChannelInboundHandlerAdapter
 * <p>
 * HttpObject 通讯数据封装
 *
 * @author maoyz0621 on 20-1-9
 * @version: v1.0
 */
@Slf4j
public class TcpNettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private List<String> uris;

    {
        uris = Arrays.asList("favicon.ico");
    }

    /**
     * 读取客户端信息
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if (msg instanceof HttpRequest) {

            // msg -> DefaultHttpRequest
            log.debug("msg = {},address = {}", msg.getClass(), ctx.channel().remoteAddress());

            // 根据http协议, http使用完就关闭, 不同的http请求使用不同的pipeline和ChannelHandler
            System.out.println("pipeline = " + ctx.pipeline().hashCode() + "; ChannelHandler = " + this.hashCode());

            // 回复信息给浏览器

            // 1. 过滤无效请求
            HttpRequest request = (HttpRequest) msg;

            URI uri = new URI(request.uri());
            if (uris.contains(uri.getPath().replaceFirst("/", ""))) {
                return;
            }

            // 是netty的ByteBuf
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            // 构建header信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
