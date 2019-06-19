package com.myz.java.study.base.net.tcp.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ServerSocket
 * 实现服务器套接字
 *
 * @author maoyz on 18-3-18.
 */
class MyServer {

    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) {
        new MyServer().start();
    }

    private void start() {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        try {
            ServerSocket server = new ServerSocket(9999);

            while (true) {
                Socket socket = server.accept();
                System.out.println("建立连接.....");
                MyChannel task = new MyChannel(socket);
                // 添加列表中
                all.add(task);
                // 线程池执行
                newFixedThreadPool.execute(task);
                // new Thread(task).start();
            }
        } catch (IOException e) {
            // 移除自身对象
            all.remove(this);
            newFixedThreadPool.shutdown();
        }

    }

    /**
     * 使用内部分封装
     */
    private class MyChannel implements Runnable {

        private DataOutputStream out;
        private DataInputStream in;
        private boolean isRunning = true;

        public MyChannel(Socket server) {
            try {
                this.out = new DataOutputStream(new BufferedOutputStream((server.getOutputStream())));
                this.in = new DataInputStream(new BufferedInputStream(server.getInputStream()));
            } catch (IOException e) {
                try {
                    out.close();
                    in.close();
                } catch (IOException e1) {
                }
                isRunning = false;
                all.remove(this);
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                sendOthers();
                // send(receive());
            }
        }

        /**
         * 转发信息
         */
        private void send(String msg) {
            msg = this.receive();

            if (null == msg || ("".equals(msg))) {
                return;
            }

            try {
                out.writeUTF("服务端转发信息-->" + msg);
                out.flush();
            } catch (IOException e) {
                isRunning = false;
                all.remove(this);
            }
        }


        /**
         * 转发转其它人
         */
        private void sendOthers() {
            String msg = this.receive();

            if (null == msg || "".equals(msg)) {
                return;
            }

            for (MyChannel other : all) {
                // 跳过本身
                if (other == this) {
                    continue;
                }
                other.send(msg);
            }

        }

        /**
         * 接收信息
         */
        private String receive() {
            String message = "";

            try {
                message = in.readUTF();
            } catch (IOException e) {
                isRunning = false;
                all.remove(this);
            }

            System.out.println("服务器接收信息<--" + message);
            return message;
        }
    }
}
