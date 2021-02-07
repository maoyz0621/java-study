package com.myz.java.study.base.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * udp客户端
 *
 * @author maoyz on 18-3-18.
 */
class MyUDPClient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;

        try {
            // 1　创建套接字
            socket = new DatagramSocket(7777);
            // 2 准备数据
            String msg = "1222222222";
            byte[] data = msg.getBytes();
            // 3 封装成数据报包 DatagramPacket(byte buf[], int offset, int length, SocketAddress address)
            packet = new DatagramPacket(data, 0, data.length, new InetSocketAddress(9999));
            // 4 发送数据
            socket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
