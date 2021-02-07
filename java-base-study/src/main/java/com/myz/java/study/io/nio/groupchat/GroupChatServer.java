/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author maoyz0621 on 20-1-18
 * @version v1.0
 */
public class GroupChatServer {

    private static final int DEFAULT_PORT = 8888;
    private static final int PORT = DEFAULT_PORT;
    private static final int TIMEOUT = 3000;
    private Selector selector = null;
    private SocketHandler socketHandler;
    private ServerSocketChannel serverSocketChannel = null;

    public GroupChatServer() {
        this(PORT);
    }

    public GroupChatServer(int port) {
        init(port);
    }

    private void init(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            socketHandler = new SocketHandler(selector);
            System.out.println("**************** 服务端启动 ******************");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注：其中select() accept() read() 使用的是同一个Thread
     */
    public void listen() throws IOException {
        try {
            System.out.println("Selector 处理select()的线程： " + Thread.currentThread());

            for (; ; ) {
                if (selector.select(TIMEOUT) > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();

                        //　处理选择器
                        handleKey(key);

                        keyIterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


    public void work() {
        new Thread(() -> {
            try {
                listen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void handleKey(SelectionKey key) {
        // 处理Accept
        if (key.isAcceptable()) {
            System.out.println("Selector 处理accept()的线程： " + Thread.currentThread());

            socketHandler.handleAccept(selector, key);
        }

        // 处理Read
        if (key.isReadable()) {
            System.out.println("Selector 处理read()的线程： " + Thread.currentThread());

            socketHandler.handleRead(key);
        }

    }

    private void close() throws IOException {
        if (serverSocketChannel != null && serverSocketChannel.isOpen()) {
            serverSocketChannel.close();
        }
        if (selector != null && selector.isOpen()) {
            selector.close();
        }
    }

    public static void main(String[] args) {
        new GroupChatServer().work();
    }
}
