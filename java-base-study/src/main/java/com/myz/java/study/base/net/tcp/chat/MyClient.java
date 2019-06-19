package com.myz.java.study.base.net.tcp.chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Socket
 *
 * @author maoyz on 18-3-18.
 */
class MyClient {

    public static void main(String[] args) {
        new MyClient().start();
    }

    /**
     * 启动入口
     */
    public void start() {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
        try {
            //1 建立套接字
            Socket socket = new Socket("localhost", 9999);
            newFixedThreadPool.execute(new SendMessage(socket));
            newFixedThreadPool.execute(new ReceiveMessage(socket));
            // new Thread(new SendMessage(socket)).start();
            // new Thread(new ReceiveMessage(socket)).start();

        } catch (UnknownHostException e) {
        } catch (IOException e) {
            newFixedThreadPool.shutdown();
        }
    }

}
