/**
 * Copyright 2019 Inc.
 **/
package com.myz.opensource.vip.net;

import com.vip.vjtools.vjkit.net.IPUtil;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-24
 * @version: v1.0
 */
public class IpUtilTest {

    @Test
    public void stringAndInt() {
        int toInt = IPUtil.ipv4StringToInt("192.168.0.1");
        System.out.println(toInt);
        String ipv4String = IPUtil.intToIpv4String(toInt);
        System.out.println(ipv4String);
    }
}
