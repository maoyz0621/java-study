/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.nio.channel.tcp;


import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.stream.Stream;

/**
 * ServerSocketChannel真正实现类　ServerSocketChannelImpl
 * Buffer的分散和聚集
 * 使用telnet 127.0.0.1 8888　登录客户端发送信息
 * @author maoyz0621 on 19-12-29
 * @version: v1.0
 */
public class ServerSocketChannelTest {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        main0();
    }

    private static void main0() {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            socketChannel = serverSocketChannel.accept();

            //　分散：采用buffer数组，依次写入；聚集：依次读取
            ByteBuffer[] byteBuffer = new ByteBuffer[2];
            byteBuffer[0] = ByteBuffer.allocateDirect(2);
            byteBuffer[1] = ByteBuffer.allocateDirect(5);
            // 总量
            int size = 7;

            for (; ; ) {
                // 读操作
                int byteRead = 0;
                while (byteRead < size) {
                    long read = socketChannel.read(byteBuffer);
                    byteRead += read;
                    System.out.println("byteRead = " + byteRead);
                    Stream.of(byteBuffer)
                            .map((buffer) -> "position = " + buffer.position() + ", limit = " + buffer.limit())
                            .forEach(System.out::println);
                }

                // 写操作
                Stream.of(byteBuffer).forEach(Buffer::flip);
                int byteWrite = 0;
                while (byteWrite < size) {
                    long write = socketChannel.write(byteBuffer);
                    byteWrite += write;
                }

                Stream.of(byteBuffer).forEach(Buffer::clear);

                System.out.println("byteRead = " + byteRead + ";byteWrite = " + byteWrite + "; " + size);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(serverSocketChannel);
            IOUtils.closeQuietly(socketChannel);
        }
    }
}
