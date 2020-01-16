/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.base;

import com.myz.netty.study.base.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author maoyz0621 on 19-3-18
 * @version: v1.0
 */
public class NettyServer {

    public static void main(String[] args) {
        try {
            main0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void main0() throws Exception{
        // 启动服务引导 框架启动引导类 屏蔽网络通讯配置信息
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 创建线程池组
        // boss接收服务请求 但是并不处理而是将请求委托给worker
        EventLoopGroup boss = new NioEventLoopGroup();
        // 真正的处理类
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //　将委托类和执行类关联至启动类
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    // 该handler对应　boss; childHandler对应　worker
                    .handler(null)
                    // 监听器
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        // 通道初始化
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 通道添加handler
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw e;
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}
