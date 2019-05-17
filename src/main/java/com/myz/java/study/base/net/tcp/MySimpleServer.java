package com.myz.java.study.base.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerSocket
 * 实现服务器套接字
 *
 * @author maoyz on 18-3-18.
 */
class MySimpleServer {

    public static void main(String[] args) {
        try {
            //1 创建套接字
            ServerSocket server = new ServerSocket(9999);
            // 接收多个客户端
            while (true) {
                //2 侦听并接受到此套接字的连接。
                Socket socket = server.accept();
                System.out.println("建立连接....");

                //3 发送数据
                OutputStream socketOutputStream = socket.getOutputStream();
                InputStream socketInputStream = socket.getInputStream();

                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socketOutputStream));
                DataInputStream in = new DataInputStream(new BufferedInputStream(socketInputStream));

                while (true) {
                    String msg = in.readUTF();
                    System.out.println("服务器接收:" + msg);

                    out.writeUTF("服务器发送--->" + msg);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
