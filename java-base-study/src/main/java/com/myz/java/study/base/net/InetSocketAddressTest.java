package com.myz.java.study.base.net;

import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * InetSocketAddress
 * 封装了InetAddress
 * IP地址 + 端口号
 *
 * @author maoyz on 18-3-18.
 */
public class InetSocketAddressTest {

    private InetSocketAddress inetSocketAddress = null;

    @Test
    public void test() {
        // 根据主机名和端口号创建套接字地址
        inetSocketAddress = new InetSocketAddress("127.0.0.1", 2222);
        // localhost
        System.out.println(inetSocketAddress.getHostName());
        // 2222
        System.out.println(inetSocketAddress.getPort());
    }
}
