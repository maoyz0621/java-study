/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 监听浏览的信息
 *
 * @author maoyz0621 on 20-1-9
 * @version: v1.0
 */
public class HttpNettyServer {

    public static void main(String[] args) {
        main0();
    }

    private static void main0() {
        // 服务类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    // 监听器
                    .childHandler(new HttpNettyServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
