/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.simpletomcat;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author maoyz0621 on 19-12-27
 * @version: v1.0
 */
public class TomcatServer {


    public static void main(String[] args) {
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setMaxPoolSize(100);
        poolTaskExecutor.setThreadNamePrefix("Tomcat-Server-");
        ServerSocket server = null;
        Socket socket = null;
        try {
            // 创建服务器Socket，绑定端口号80,浏览器默认访问端口
            server = new ServerSocket(8080);
            System.out.println("服务器启动....");
            while (true) {
                // 开始监听,浏览器相当于客户端
                socket = server.accept();
                poolTaskExecutor.execute(new TomcatRunnable(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
