package com.myz.java.study.base.net.tcp.chat;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收信息
 *
 * @author maoyz on 18-3-22.
 */
class ReceiveMessage implements Runnable {

    private DataInputStream in;
    private boolean isRunning = true;

    public ReceiveMessage() {
    }

    public ReceiveMessage(Socket client) {
        try {
            in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            isRunning = false;
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e1) {
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("                           客户端接收信息:" + receive());
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
        }
        return message;
    }
}
