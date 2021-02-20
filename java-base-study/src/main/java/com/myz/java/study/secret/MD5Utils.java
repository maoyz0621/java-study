/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:17  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * @author maoyz
 */
public class MD5Utils {
    public MD5Utils() {
    }

    public static String encode(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] buffer = digest.digest(text.getBytes("utf-8"));
            StringBuffer sb = new StringBuffer();
            byte[] var4 = buffer;
            int var5 = buffer.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                byte b = var4[var6];
                int a = b & 255;
                String hex = Integer.toHexString(a);
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }

                sb.append(hex);
            }

            return sb.toString();
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static String encodeUpperCase(String text) {
        try {
            String result = encode(text);
            if (!StringUtils.isEmpty(result)) {
                return result.toUpperCase();
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static String encode(InputStream in) {
        try {
            MessageDigest digester = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[8192];

            int byteCount;
            while((byteCount = in.read(bytes)) > 0) {
                digester.update(bytes, 0, byteCount);
            }

            byte[] digest = digester.digest();
            StringBuffer sb = new StringBuffer();
            byte[] var6 = digest;
            int var7 = digest.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                byte b = var6[var8];
                int a = b & 255;
                String hex = Integer.toHexString(a);
                if (hex.length() == 1) {
                    hex = 0 + hex;
                }

                sb.append(hex);
            }

            String var22 = sb.toString();
            return var22;
        } catch (Exception var20) {
            var20.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var19) {
                    var19.printStackTrace();
                }

                in = null;
            }

        }

        return null;
    }

    public static String md5ValidationGenerate(String id, String salt, String ivString) {
        String md5Encrypt = encode(id + salt);
        String validation = encode(md5Encrypt + ivString);
        return validation;
    }
}