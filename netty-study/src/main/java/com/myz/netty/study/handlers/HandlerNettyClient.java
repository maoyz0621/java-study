/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.handlers;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author maoyz0621 on 20-1-13
 * @version: v1.0
 */
public class HandlerNettyClient {

    private static final int PORT = 8880;
    private static final String HOSTNAME = "127.0.0.1";

    private int port;
    private String hostName;

    public HandlerNettyClient() {
        this(PORT);
    }

    public HandlerNettyClient(int port) {
        this(port, HOSTNAME);
    }

    public HandlerNettyClient(int port, String hostName) {
        this.port = port;
        this.hostName = hostName;
    }

    private void main0() {
        // 启动客户端服务引导
        Bootstrap clientBootstrap = new Bootstrap();

        // 创建线程池组
        // 真正的处理类
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            clientBootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new HandlerNettyClientInitializer());

            // 启动客户端连接服务器
            connectWork(clientBootstrap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            closeClientEventLoop(worker);
        }
    }

    private void connectWork(Bootstrap clientBootstrap) throws InterruptedException {
        ChannelFuture channelFuture = clientBootstrap.connect(hostName, port).sync();
        Channel channel = channelFuture.channel();
        System.out.println("====================== 客户端 " + channel.remoteAddress() + " Start ================");

        channelFuture.channel().closeFuture().sync();
    }


    private void closeClientEventLoop(EventLoopGroup worker) {
        if (null != worker) {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new HandlerNettyClient().main0();
    }
}
