/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.aio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

/**
 * @author maoyz0621 on 19-9-20
 * @version: v1.0
 */
public class AsynchronousServerSocketChannelMain {

    public static void main(String[] args) {
        main0();
    }

    private static void main0() {
        ExecutorService executorService = new ThreadPoolExecutor(
                200,
                150,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100),
                new ThreadFactoryBuilder().setNameFormat("aio_thread_pool_%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);
            // 使用多线程  open(channelGroup)
            final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup)
                    .bind(new InetSocketAddress(8888));

            serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

                // 连接成功
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {

                    // todo
                    serverSocketChannel.accept(null, this);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    result.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {

                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });
                }

                // 连接失败
                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
