package com.myz.java.study.simpletomcat;

import java.io.*;
import java.net.Socket;

/**
 * @author maoyz on 18-1-31.
 */
public class TomcatRunnable implements Runnable {

    private Socket socket;

    public TomcatRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        write();
    }

    /**
     * 解析请求
     */
    private StringBuilder read() {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        try {
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
        } catch (IOException e) {
        } finally {
            try {
                // 关闭资源
                in.close();
                reader.close();
                bufferedReader.close();
            } catch (IOException e) {
            }
        }
        return builder;
    }

    /**
     * 作出响应
     */
    private PrintWriter write() {
        // 输出流
        OutputStream out = null;
        //　输出流转为字符流
        OutputStreamWriter outputStreamWriter = null;
        // 打印流
        PrintWriter writer = null;
        try {
            // 获取输出流，发送返回信息
            out = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(out);
            writer = new PrintWriter(outputStreamWriter);
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html;charset=utf-8");
            // 浏览器结束标志
            writer.println();
            writer.println("hello 你好.....");
            String info = read().toString();
            System.out.println(info.trim());
        } catch (IOException e) {
        } /*finally {
            writer.flush();
            writer.close();
            try {
                outputStreamWriter.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return writer;
    }
}
