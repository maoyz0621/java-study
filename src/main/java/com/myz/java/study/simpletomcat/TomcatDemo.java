package com.myz.java.study.simpletomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author maoyz on 18-2-1.
 */
public class TomcatDemo {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        // 输出流
        OutputStream out = null;
        //　输出流转为字符流
        OutputStreamWriter outputStreamWriter = null;
        // 打印流
        PrintWriter writer = null;
        try {
            // 创建服务器Socket，绑定端口号80,浏览器默认访问端口
            server = new ServerSocket(8080);
            System.out.println("服务器启动....");
            socket = server.accept();

            // 获取输入流，解析浏览器发送的请求
            in = socket.getInputStream();
            //　将字节流转为字符流
            reader = new InputStreamReader(in);
            //　将字符流包装为缓冲流
            bufferedReader = new BufferedReader(reader);
            String info = "";
            while ((info = bufferedReader.readLine()) != null && info.length() > 0) {
                builder.append(info);
                builder.append("\r\n");
            }

            // 获取输出流，发送返回信息
            out = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(out);
            writer = new PrintWriter(outputStreamWriter);
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html;charset=utf-8");
            // 浏览器结束标志
            writer.println();
            writer.println("hello 你好.....");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
