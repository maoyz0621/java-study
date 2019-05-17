/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.uuid;

import com.vip.vjtools.vjkit.id.IdUtil;
import org.junit.Test;

import java.util.UUID;

/**
 * @author maoyz0621 on 19-4-23
 * @version: v1.0
 */
public class IdUtilTest {

    @Test
    public void test() {
        UUID uuid1 = IdUtil.fastUUID();
        UUID uuid2 = IdUtil.fastUUID();
        System.out.println("uuid1 = " + uuid1);
        System.out.println("uuid2 = " + uuid2);

    }
}
