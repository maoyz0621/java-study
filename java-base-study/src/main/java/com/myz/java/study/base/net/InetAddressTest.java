package com.myz.java.study.base.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress
 * 没有封装端口
 * <p>
 * getHostName() 获取此IP地址的主机名
 * getHostAddress() 返回IP地址字符串（以文本表现形式）
 *
 * @author maoyz on 18-3-18.
 */
public class InetAddressTest {
    private InetAddress inetAddress = null;

    /**
     * InetAddress创建
     */
    @Test
    public void test() throws UnknownHostException {
        // 本地计算机
        inetAddress = InetAddress.getLocalHost();
        // 计算机名称
        System.out.println(inetAddress.getHostName());
        // ip地址
        System.out.println(inetAddress.getHostAddress());
    }

    /**
     * getByName() 根据域名或ip获取
     */
    @Test
    public void testByName() throws UnknownHostException {
        // 根据域名获取远程计算机信息
        inetAddress = InetAddress.getByName("www.baidu.com");
        // 220.181.111.188
        System.out.println(inetAddress.getHostAddress());

        // 根据ip地址获取
        inetAddress = InetAddress.getByName("220.181.111.188");
        // 返回的220.181.111.188
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
    }

}
