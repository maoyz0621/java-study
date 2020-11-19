package com.myz.java.study.base.net.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Socket
 *
 * @author maoyz on 18-3-18.
 */
class MySimpleClient {

    public static void main(String[] args) {
        try {
            //1 建立套接字
            Socket socket = new Socket("localhost", 9999);

            // 获取控制台输出信息
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(console);
            System.out.println("请输入:");

            while (true) {
                //　接收键盘输入信息
                String msg = scanner.nextLine();

                // 发送数据
                OutputStream socketOutputStream = socket.getOutputStream();
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socketOutputStream));
                out.writeUTF(msg);
                out.flush();

                //2 接收数据
                InputStream socketInputStream = socket.getInputStream();
                DataInputStream in = new DataInputStream(new BufferedInputStream(socketInputStream));
                String sendMsg = in.readUTF();
                System.out.println("---->" + sendMsg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
