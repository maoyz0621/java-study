/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:17  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author maoyz
 */
public class HmacMD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(HmacMD5Utils.class);

    public HmacMD5Utils() {
    }

    public static String encryptHMAC(String data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data.getBytes());
        return byteArrayToHexString(digest);
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);

        for(int i = 0; i < b.length; ++i) {
            int v = b[i] & 255;
            if (v < 16) {
                sb.append('0');
            }

            sb.append(Integer.toHexString(v));
        }

        return sb.toString();
    }
}