/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.nio.channel.tcp;

import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * SocketChannel真正实现类　SocketChannelImpl
 *
 * @author maoyz0621 on 19-12-29
 * @version: v1.0
 */
public class NioClient {

    public static void main(String[] args) {
        new NioClient().main0();
    }

    private void main0() {
        SocketChannel socketChannel = null;
        Selector selector = null;
        try {
            // 1. 创建SocketChannel
            socketChannel = SocketChannel.open();
            //　2. 设置非阻塞
            socketChannel.configureBlocking(false);

            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);

            selector = Selector.open();
            // 注册连接事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            //　连接服务器
            if (!socketChannel.connect(socketAddress)) {
                //　如果connect()连接失败，通过此方法完成连接
                while (!socketChannel.finishConnect()) {
                    System.out.println("正在等待连接");
                }
            }

            // 连接成功．发送数据
            ByteBuffer byteBuffer = ByteBuffer.wrap("message ...".getBytes(StandardCharsets.UTF_8));
            //　发送数据
            socketChannel.write(byteBuffer);
            // System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(socketChannel);
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
