/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.groupchat;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author maoyz0621 on 20-1-18
 * @version v1.0
 */
public class GroupChatClient {

    private static final int DEFAULT_PORT = 8888;
    private static final String LOCAL_HOSTNAME = "localhost";

    private static final int PORT = DEFAULT_PORT;
    private static final String HOSTNAME = LOCAL_HOSTNAME;
    private static final int TIMEOUT = 3000;
    private static final String SEPECTOR = "##";

    private boolean hasNickName = false;
    private String clientName;
    private SocketHandler socketHandler;
    private Selector selector;
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public GroupChatClient() {
        init();
    }

    private void init() {
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(HOSTNAME, PORT));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            // sb之前是OP_ACCEPT
            socketChannel.register(selector, SelectionKey.OP_READ);

            socketHandler = new SocketHandler(selector);
            clientName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(clientName + " 上线了");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void work() throws IOException {
        new Thread(() -> {
            while (true) {
                listenKeyboard();
            }
        }).start();

        listen();
    }

    private void listenKeyboard() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            if (StringUtils.isNotBlank(msg)) {
                send(msg);
            }
        }
    }

    /**
     * 接收消息
     */
    public void listen() throws IOException {
        try {
            for (; ; ) {
                if (selector.select(TIMEOUT) > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isReadable()) {
                            handleRead(key);
                        }

                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    private void close() throws IOException {
        if (socketChannel != null && socketChannel.isOpen()) {
            socketChannel.close();
        }
        if (selector != null && selector.isOpen()) {
            selector.close();
        }
    }


    public void handleRead(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            // 获取该channel对用的buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int len;
            while ((len = socketChannel.read(byteBuffer)) > 0) {
                byteBuffer.flip();
                String msg = new String(byteBuffer.array(), 0, len, StandardCharsets.UTF_8);
                if (msg.startsWith("Welcome")) {
                    hasNickName = true;
                }
                System.out.println(msg);
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

    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    public Boolean send(Object msg) {
        // 注册用户
        if (hasNickName) {
            // 私聊　@XXX
            if (((String) msg).contains("@")) {
                msg = clientName + SEPECTOR + ((String) msg).replace("@", SEPECTOR);
            } else {
                msg = clientName + SEPECTOR + msg;
            }
        } else {
            if (!((String) msg).contains("@")) {
                clientName = (String) msg;
            }
        }
        // 否则msg表示用户名称

        try {
            byteBuffer.clear();
            byteBuffer.put(("" + msg).getBytes(StandardCharsets.UTF_8));
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        GroupChatClient client = new GroupChatClient();
        client.work();
    }
}
