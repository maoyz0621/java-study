/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:15  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author maoyz
 */
public class AESUtils {

    private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);
    public static final int INIT_VECTOR_LENGTH = 16;
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    protected String data;
    protected String initVector;
    protected String errorMessage;

    private AESUtils() {
    }

    private AESUtils(String initVector, String data, String errorMessage) {
        this.initVector = initVector;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static AESUtils encryptWith128CBC(String secretKey, String plainText) {
        String initVector = null;

        try {
            if (!isKeyLengthValid(secretKey)) {
                throw new Exception("Secret key's length must be 128");
            } else {
                SecureRandom secureRandom = new SecureRandom();
                byte[] initVectorBytes = new byte[8];
                secureRandom.nextBytes(initVectorBytes);
                initVector = bytesToHex(initVectorBytes);
                initVectorBytes = initVector.getBytes("UTF-8");
                IvParameterSpec ivParameterSpec = new IvParameterSpec(initVectorBytes);
                SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(1, secretKeySpec, ivParameterSpec);
                byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
                ByteBuffer byteBuffer = ByteBuffer.allocate(initVectorBytes.length + encrypted.length);
                byteBuffer.put(initVectorBytes);
                byteBuffer.put(encrypted);
                String result = Base64.getEncoder().encodeToString(byteBuffer.array());
                return new AESUtils(initVector, result, (String)null);
            }
        } catch (Throwable var11) {
            logger.error("AES encrypt error. plainText={}", plainText);
            return new AESUtils(initVector, (String)null, var11.getMessage());
        }
    }

    public static AESUtils decryptWith128CBC(String secretKey, String cipherText) {
        try {
            if (!isKeyLengthValid(secretKey)) {
                throw new Exception("Secret key's length must be 128");
            } else {
                byte[] encrypted = Base64.getDecoder().decode(cipherText);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(encrypted, 0, 16);
                SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, ivParameterSpec);
                String result = new String(cipher.doFinal(encrypted, 16, encrypted.length - 16));
                return new AESUtils(bytesToHex(ivParameterSpec.getIV()), result, (String)null);
            }
        } catch (Throwable var7) {
            logger.error("AES decrypt error. plainText={}", cipherText);
            return new AESUtils((String)null, (String)null, var7.getMessage());
        }
    }

    public static boolean isKeyLengthValid(String key) {
        return key.length() == 16;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];

        for(int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public String getData() {
        return this.data;
    }

    public String getInitVector() {
        return this.initVector;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean hasError() {
        return this.errorMessage != null;
    }

    public String toString() {
        return this.getData();
    }

    public static String getKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = bytesToHex(b);
            return s;
        } catch (NoSuchAlgorithmException var4) {
            logger.error("没有此算法：", var4);
        } catch (Exception var5) {
            logger.error("生成密钥异常：", var5);
        }

        return null;
    }

    public static String rmsDecrypt(String key, String encrypted) {
        try {
            String initVector = encrypted.substring(0, 16);
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec sKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            cipher.init(2, sKeySpec, iv);
            byte[] original = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encrypted.substring(16)));
            return new String(original);
        } catch (Exception var7) {
            return null;
        }
    }
}