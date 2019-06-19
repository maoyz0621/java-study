package com.myz.java.study.base.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * udp服务端
 * DatagramSocket
 * DatagramPacket
 * 1 创建端口号
 * 2 接收数据
 * 3 封装成数据报包 DatagramPacket(byte buf[], int length)
 * 4 接收数据报包
 * 5 分析数据
 *
 * @author maoyz on 18-3-18.
 */
class MyUDPServer {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        DatagramPacket packet = null;

        try {
            //1 创建端口号
            socket = new DatagramSocket(9999);

            //2 接收数据
            byte[] data = new byte[1024];

            //3 封装成数据报包 DatagramPacket(byte buf[], int length)
            packet = new DatagramPacket(data, data.length);

            //4 接收数据报包
            socket.receive(packet);

            //5 分析数据
            byte[] info = packet.getData();
            int len = packet.getLength();
            System.out.println(new String(info, 0, len));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
