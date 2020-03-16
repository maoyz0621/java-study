/**
 * Copyright 2019 Inc.
 **/
package com.myz.netty.study.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author maoyz0621 on 19-9-21
 * @version: v1.0
 */
public class ProtocolNettyClient {

    public static final int PORT = 8880;

    public static void main(String[] args) {
        new ProtocolNettyClient().main0();
    }

    private void main0() {
        // 启动客户端服务引导
        Bootstrap clientBootstrap = new Bootstrap();

        // 创建线程池组
        // boss接收服务请求 但是并不处理而是将请求委托给worker
        // 真正的处理类
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            clientBootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MessageProtocolEncode(),
                                    new MessageProtocolDecode(),
                                    new ProtocolClientHandler());
                        }
                    });


            System.out.println("*************** 客户端启动　****************");

            // 启动客户端连接服务器
            ChannelFuture channelFuture = clientBootstrap.connect("127.0.0.1", PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
