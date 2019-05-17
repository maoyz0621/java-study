package com.myz.java.study.simpletomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟tomcat服务端，浏览器相当于客户端，默认端口号80
 * @author maoyz on 18-1-31.
 */
public class MyTomcat {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        try {
            // 创建服务器Socket，绑定端口号80,浏览器默认访问端口
            server = new ServerSocket(8080);
            System.out.println("服务器启动....");
            while (true){
                // 开始监听,浏览器相当于客户端
                socket = server.accept();
                new Thread(new TomcatRunnable(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
