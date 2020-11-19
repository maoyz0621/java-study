package com.myz.java.study.base.net.tcp.chat;

import java.io.*;
import java.net.Socket;

/**
 * 返送信息
 *
 * @author maoyz on 18-3-22.
 */
class SendMessage implements Runnable {

    /**
     * 键盘输入信息
     */
    private BufferedReader console;
    private DataOutputStream out;
    /**
     * 线程状态
     */
    private boolean isRunning = true;

    public SendMessage() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public SendMessage(Socket client) {
        this();
        try {
            // 获取输出流
            out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        } catch (IOException e) {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e1) {
            }
            // 改变线程状态
            isRunning = false;
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            send();
        }
    }

    /**
     * 发送信息
     */
    private void send() {
        String message = getMessageFromConsole();

        try {
            if (message != null && !("".equals(message))) {
                out.writeUTF("客户端发送 ..." + message);
                // 强制刷新
                out.flush();
            }
        } catch (IOException e) {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e1) {
            }
            isRunning = false;
        }

    }

    /**
     * 从控制台获取信息
     */
    private String getMessageFromConsole() {
        // return new Scanner(console).nextLine();
        String msg = "";
        System.out.println("输入信息:");
        try {
            msg = console.readLine();
            return msg;
        } catch (IOException e) {
        }
        return msg;
    }
}
