/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Objects;

/**
 * @author maoyz on 18-10-22
 * @version: v1.0
 */
public class SelectSockets {

    private static final int PORT = 8888;
    private static final int BUFFER_SIZE = 1024;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) {
        new SelectSockets().service();
    }

    public void service() {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            // open channel
            serverSocketChannel = ServerSocketChannel.open();
            // bind port
            ServerSocket socket = serverSocketChannel.socket();
            socket.bind(new InetSocketAddress(PORT));
            // no block设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 创建一个Selector
            selector = Selector.open();
            //
            int ops = serverSocketChannel.validOps();
            // register selector
            serverSocketChannel.register(selector, ops, null);
            for (; ; ) {
                int select = selector.select(TIMEOUT);
                if (Objects.equals(0, select)) {
                    continue;
                }

                // 获取SelectionKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        handleAccept(key);
                    } else if (key.isConnectable()) {
                        // a connection was established with a remote server.
                        System.out.println("isConnectable = true");
                    } else if (key.isReadable()) {
                        // a channel is ready for reading 从SelectionKey访问Channel
                        readDataFromSocket(key);
                    } else if (key.isWritable()) {
                        // a channel is ready for writing
                        writeDataFromSocket(key);
                    } else {

                    }
                    //// 该事件是新的连接
                    //int readyOps = key.readyOps();
                    //// 从SelectionKey访问Channel和Selecto
                    //Selector selector1 = key.selector();
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != selector) {
                    selector.close();
                }
                if (null != serverSocketChannel) {
                    serverSocketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理连接
     */
    private void handleAccept(SelectionKey key) throws IOException {
        // a connection was accepted by a ServerSocketChannel 判断是否是一个连接到来
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // 监听新进来的连接
        SocketChannel client = server.accept();
        registerChannel(key.selector(), client, SelectionKey.OP_READ, ByteBuffer.allocate(BUFFER_SIZE));
    }

    /**
     * 一个选择器决定了和通道关联的SelectionKey object是准备读状态。如果通道返回EOF，通道将被关闭。
     * 并且会自动使相关的key失效，选择器然后会在下一次的select call时取消掉通道的注册
     */
    protected void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        long count = client.read(buffer);
        buffer.clear();
        if (Objects.nonNull(client)) {
            while (count > 0) {
                // 将缓冲区置为可读
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                buffer.clear();
                count = client.read(buffer);
            }
            if (-1 == count) {
                // 读取结束后关闭通道，使key失效
                client.close();
            }
        }
    }

    protected void writeDataFromSocket(SelectionKey key) throws IOException {
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.flip();
        SocketChannel client = (SocketChannel) key.channel();
        while (buffer.hasRemaining()) {
            client.write(buffer);
        }
        buffer.compact();
    }

    /**
     * 注册读事件
     */
    private void registerChannel(Selector selector, SelectableChannel channel, int ops, ByteBuffer allocate) throws IOException {
        if (Objects.isNull(channel)) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, ops, allocate);
    }
}
