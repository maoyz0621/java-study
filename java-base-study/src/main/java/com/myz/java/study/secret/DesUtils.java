/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:16  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * @author maoyz
 */
public final class DesUtils {
    private static final Logger logger = LoggerFactory.getLogger(DesUtils.class);
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public static String encode(String key, String data) {
        if (data != null) {
            try {
                byte[] keys = StringUtils.getBytesUtf8(key);
                DESKeySpec dks = new DESKeySpec(keys);
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                Key secretKey = keyFactory.generateSecret(dks);
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(1, secretKey, new IvParameterSpec(dks.getKey()));
                byte[] bytes = cipher.doFinal(data.getBytes());
                return byte2hex(bytes);
            } catch (Exception var8) {
                logger.error(var8.getMessage());
            }
        }

        return null;
    }

    public static String decode(String key, String data) {
        if (data != null) {
            try {
                DESKeySpec dks = new DESKeySpec(key.getBytes());
                SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                Key secretKey = keyFactory.generateSecret(dks);
                Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(2, secretKey, new IvParameterSpec(dks.getKey()));
                return new String(cipher.doFinal(hex2byte(data.getBytes())));
            } catch (Exception var6) {
                logger.error(var6.getMessage());
            }
        }

        return null;
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();

        for(int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0) {
            throw new IllegalArgumentException();
        } else {
            byte[] b2 = new byte[b.length / 2];

            for(int n = 0; n < b.length; n += 2) {
                String item = new String(b, n, 2);
                b2[n / 2] = (byte)Integer.parseInt(item, 16);
            }

            return b2;
        }
    }
}