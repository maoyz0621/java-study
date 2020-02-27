/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.groupchat;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author maoyz0621 on 20-1-18
 * @version v1.0
 */
public class SocketHandler {

    private static final int BUFFER_SIZE = 1024;
    private static final String SEPECTOR = "##";
    private static Map<String, String> userMap = new HashMap<>();

    ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

    private Selector selector;

    public SocketHandler(Selector selector) {
        this.selector = selector;
    }

    public void handleRead(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            // 获取该channel对用的buffer
            String message = read(socketChannel);

            if (StringUtils.isBlank(message)) {
                closeChannel(key, socketChannel);
                return;
            }

            String[] split = message.split(SEPECTOR);

            if (split.length == 1) {
                if (userMap.containsKey(split[0])) {
                    write("Failure, nickName已被注册 " + split[0], socketChannel);
                } else {
                    userMap.put(split[0], socketChannel.getRemoteAddress().toString());
                    // 提醒该用户注册成功
                    write("Welcome " + split[0], socketChannel);
                    // 通知上线
                    boardMessage(split[0] + "上线了", socketChannel);
                }

            } else if (split.length == 2) {
                if (StringUtils.equals("bye", split[1])) {
                    closeChannel(key, socketChannel);
                    return;
                }
                // 转发其他Client
                boardMessage(message.replace("##", " say:"), socketChannel);
            } else if (split.length == 3) {
                p2pChat(split[1], split[2], socketChannel);
            }


        } catch (IOException e) {
            try {
                System.out.println(String.format("============ 用户[%s]离线了 =============", socketChannel.getRemoteAddress().toString()));
                // 取消注册
                key.cancel();
                // 关闭通道
                socketChannel.close();
                // 通知其他客户端
                boardMessage(String.format("============ 用户[%s]离线了 =============", socketChannel.getRemoteAddress().toString()), socketChannel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void closeChannel(SelectionKey key, SocketChannel socketChannel) throws IOException {
        System.out.println(String.format("============ 用户[%s]离线了 =============", socketChannel.getRemoteAddress().toString()));
        // 取消注册
        key.cancel();
        // 关闭通道
        socketChannel.close();
        // 通知其他客户端
        boardMessage(String.format("============ 用户[%s]离线了 =============", socketChannel.getRemoteAddress().toString()), socketChannel);
        return;
    }


    public void handleAccept(Selector selector, SelectionKey key) {
        ServerSocketChannel serverSocketChannel;
        try {
            serverSocketChannel = (ServerSocketChannel) key.channel();
            // 生成一个 SocketChannel
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            // 将SocketChannel注册到Selector
            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(BUFFER_SIZE));

            System.out.println("[" + socketChannel.getRemoteAddress() + "] is already connect server!");

            write("please register your name:", socketChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleConnect(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try {
            System.out.println("[" + socketChannel.getRemoteAddress() + "] is already connect server!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取消息
     */
    private String read(SocketChannel socketChannel) throws IOException {
        buffer.clear();
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = socketChannel.read(buffer)) > 0) {
            sb.append(new String(buffer.array(), 0, len, StandardCharsets.UTF_8));
            System.out.println("接收来自[" + socketChannel.getRemoteAddress() + "]的消息:" + sb.toString());
            buffer.flip();
        }
        return sb.toString();
    }

    /**
     * 发送消息
     *
     * @param socketChannel
     * @throws IOException
     */
    private void write(String msg, SocketChannel socketChannel) throws IOException {
        buffer.clear();
        buffer.put(msg.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        socketChannel.write(buffer);
    }


    /**
     * 转发消息到其他客户端（需要排除自身）
     *
     * @param msg               转发的消息
     * @param selfSocketChannel 　需要排除的
     */
    private void boardMessage(Object msg, SocketChannel selfSocketChannel) throws IOException {
        System.out.println("转发消息．．．．．．．．．．．");
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();
            if (targetChannel.isOpen() && targetChannel instanceof SocketChannel && targetChannel != selfSocketChannel) {
                if (msg instanceof String) {
                    ByteBuffer byteBuffer = ByteBuffer.wrap(((String) msg).getBytes(StandardCharsets.UTF_8));
                    // 处理转发客户端逻辑
                    ((SocketChannel) targetChannel).write(byteBuffer);
                }
            }
        }

    }

    /**
     * 私聊消息
     */
    private void p2pChat(Object msg, String nickName, SocketChannel sourceChannel) throws IOException {
        AtomicBoolean flag = new AtomicBoolean(false);
        selector.keys().forEach((key) -> {
            Channel targetChannel = key.channel();
            if (targetChannel.isOpen() && targetChannel instanceof SocketChannel) {
                try {
                    if (((SocketChannel) targetChannel).getRemoteAddress().toString().equals(userMap.get(nickName))) {
                        if (msg instanceof String) {
                            ByteBuffer byteBuffer = ByteBuffer.wrap((sourceChannel.getRemoteAddress() + " to " + nickName + " say: " + msg).getBytes(StandardCharsets.UTF_8));
                            flag.set(true);
                            // 处理转发客户端逻辑
                            ((SocketChannel) targetChannel).write(byteBuffer);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (!flag.get()) {
            write(String.format("用户[%s]不存在", nickName), sourceChannel);
        } else {
            write(msg + "---------【私聊发送给：" + nickName + "　　　状态：成功】", sourceChannel);
        }
    }

}
