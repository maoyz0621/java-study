/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.channel.tcp;

import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author maoyz0621 on 20-1-18
 * @version v1.0
 */
public class NioServerSocket {

    public static void main(String[] args) throws IOException {
        new NioServerSocket().main0();
    }

    private void main0() throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;

        try {
            // 1. 创建serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            // 2. 创建Selector
            selector = Selector.open();
            // 3. 绑定端口，进行监听
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            // 4. 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            // 5. 将serverSocketChannel注册到Selector，关心事件　OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 循环等待客户端连接
            for (; ; ) {
                if (selector.select(1000) == 0) {
                    // System.out.println("==============================");
                    continue;
                }

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                System.out.println("有事件发生的selectionKeys数量: " + selectionKeys.size());

                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    // OP_ACCEPT事件
                    if (key.isAcceptable()) {
                        accept(key, selector);
                    }

                    // OP_READ事件
                    if (key.isReadable()) {
                        readMsg(key);
                    }


                }
                // 移除
                keyIterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(serverSocketChannel);
            IOUtils.closeQuietly(selector);
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
            if (selector != null) {
                selector.close();
            }

        }
    }

    private void readMsg(SelectionKey key) {
        // 反向获取对应的channel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {

            // 获取该channel对用的buffer
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
            byteBuffer.clear();
            int read = socketChannel.read(byteBuffer);
            if (read == -1) {
                socketChannel.close();
            } else {
                byteBuffer.flip();
                System.out.println("接收来自[" + socketChannel.getRemoteAddress() + "]的消息:" + new String(byteBuffer.array(), 0, byteBuffer.limit()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void accept(SelectionKey key, Selector selector) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            // 生成一个 SocketChannel
            SocketChannel socketChannel = serverSocketChannel.accept();

            System.out.println("客户端连接成功,socketChannel = " + socketChannel + ", hashCode = " + socketChannel.hashCode());

            socketChannel.configureBlocking(false);
            // 将SocketChannel注册到Selector
            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
            System.out.println("注册的SelectionKey数量=" + selector.keys().size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
