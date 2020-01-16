/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author maoyz0621 on 19-9-21
 * @version: v1.0
 */
public class NettyClient {

    public static void main(String[] args) {
        main0();
    }

    private static void main0() {
        // 启动服务引导 框架启动引导类 屏蔽网络通讯配置信息
        Bootstrap clientBootstrap = new Bootstrap();

        // 创建线程池组
        // boss接收服务请求 但是并不处理而是将请求委托给worker
        EventLoopGroup boss = new NioEventLoopGroup();
        // 真正的处理类
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ChannelFuture channelFuture = clientBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
