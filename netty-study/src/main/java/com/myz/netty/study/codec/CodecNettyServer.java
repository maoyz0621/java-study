/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author maoyz0621 on 20-1-13
 * @version: v1.0
 */
public class CodecNettyServer {


    private static final int PORT = 8880;

    private int port;

    public CodecNettyServer() {
        this(PORT);
    }

    public CodecNettyServer(int port) {
        this.port = port;
    }


    private void main0() throws Exception {
        // 启动服务引导 框架启动引导类 屏蔽网络通讯配置信息
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 创建线程池组
        // boss接收服务请求 但是并不处理而是将请求委托给worker
        // bossGroup和workGroup 包含子线程(NioEventLoop) 默认CPU核数*2 = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        // 实际是EventExecutor执行
        EventLoopGroup boss = new NioEventLoopGroup();
        // 真正的处理类
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //　将委托类和执行类关联至启动类
            serverBootstrap.group(boss, worker)
                    // 使用NioServerSocketChannel作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    // 该handler对应　boss; childHandler对应　worker
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 监听器
                    .childHandler(new CodecNettyServerInitializer())
                    //　线程队列得到的连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 保存活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            futureWork(serverBootstrap);

        } catch (InterruptedException e) {
            throw e;
        } finally {
            // 关闭服务
            closeServerEventLoop(boss, worker);
        }
    }

    private void closeServerEventLoop(EventLoopGroup boss, EventLoopGroup worker) {
        if (null != boss) {
            boss.shutdownGracefully();
        }

        if (null != worker) {
            worker.shutdownGracefully();
        }

    }

    private void futureWork(ServerBootstrap serverBootstrap) throws InterruptedException {
        //　绑定端口并同步，启动服务器
        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

        // future-listener机制
        channelFuture.addListener(future -> {

            if (future.isDone()) {
                System.out.println("=========== isDone ===========");
            }

            if (future.isSuccess()) {
                System.out.println("=========== isSuccess ===========");
            }
        });

        //　对关闭通道进行监听
        channelFuture.channel().closeFuture().sync();
    }

    public static void main(String[] args) {
        try {
            new CodecNettyServer().main0();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
