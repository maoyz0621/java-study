/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:19  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.*;

/**
 * @author maoyz
 */
public class RSAUtils {
    private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);

    private static final String ALGORITHM = "RSA";
    private static final String CHARSET = "utf-8";
    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private static final String CIPHER = "RSA/ECB/PKCS1Padding";

    private static final ConcurrentHashMap<String, Future<PrivateKey>> privateKeyMap = new ConcurrentHashMap<>();

    public RSAUtils() {
    }

    public static String decryptByPrimaryKey(String content, String primaryKey) {
        PrivateKey prikey = getPrivateKey(primaryKey);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(2, prikey);
            InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
            byte[] data = read(cipher, ins, 128);
            return StringUtils.newStringUtf8(data);
        } catch (NoSuchAlgorithmException var6) {
            logger.error("RSA算法不存在,解密的内容:content={}", content, var6);
        } catch (InvalidKeyException | NoSuchPaddingException var7) {
            logger.error("错误的签名秘钥,解密的内容:content={}", content, var7);
        } catch (GeneralSecurityException | IOException var8) {
            logger.error("解密的内容:content={}", content, var8);
        }

        return content;
    }

    public static byte[] decryptByPrimaryKey(byte[] privateKey, byte[] raw) {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyf = null;

        try {
            keyf = KeyFactory.getInstance(ALGORITHM);
            PrivateKey key = keyf.generatePrivate(priPKCS8);
            Cipher cipher = Cipher.getInstance(ALGORITHM, new BouncyCastleProvider());
            cipher.init(2, key);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);

            for (int j = 0; raw.length - j * blockSize > 0; ++j) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            }

            return bout.toByteArray();
        } catch (NoSuchPaddingException | InvalidKeyException | InvalidKeySpecException var9) {
            logger.error("错误的签名秘钥", var9);
        } catch (NoSuchAlgorithmException var10) {
            logger.error("RSA算法不存在", var10);
        } catch (GeneralSecurityException | IOException var11) {
            logger.error(var11.getMessage(), var11);
        }

        return raw;
    }

    public static byte[] decryptByPublicKey(byte[] raw, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Cipher cipher = Cipher.getInstance(ALGORITHM, new BouncyCastleProvider());
            cipher.init(2, pubKey);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);

            for (int j = 0; raw.length - j * blockSize > 0; ++j) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
            }

            return bout.toByteArray();
        } catch (IOException var9) {
            logger.error(var9.getMessage(), var9);
        } catch (BadPaddingException var10) {
            logger.error(var10.getMessage(), var10);
        } catch (InvalidKeySpecException var11) {
            logger.error("错误的签名秘钥", var11);
        } catch (IllegalBlockSizeException var12) {
            logger.error(var12.getMessage(), var12);
        } catch (NoSuchAlgorithmException var13) {
            logger.error("RSA算法不存在", var13);
        } catch (NoSuchPaddingException var14) {
            logger.error("错误的签名秘钥", var14);
        } catch (InvalidKeyException var15) {
            logger.error("错误的签名秘钥", var15);
        } catch (GeneralSecurityException var16) {
            logger.error(var16.getMessage(), var16);
        }

        return raw;
    }

    public static byte[] encrypt(byte[] data, String publicKey) {
        KeyFactory keyf = null;

        try {
            keyf = KeyFactory.getInstance(ALGORITHM);
            PublicKey key = keyf.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(publicKey)));
            Cipher cipher = Cipher.getInstance(ALGORITHM, new BouncyCastleProvider());
            cipher.init(1, key);
            int blockSize = cipher.getBlockSize();
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];

            for (int i = 0; data.length - i * blockSize > 0; ++i) {
                if (data.length - i * blockSize > blockSize) {
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                } else {
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                }
            }

            return raw;
        } catch (BadPaddingException var11) {
            logger.error(var11.getMessage(), var11);
        } catch (InvalidKeySpecException var12) {
            logger.error("错误的签名秘钥", var12);
        } catch (IllegalBlockSizeException var13) {
            logger.error(var13.getMessage(), var13);
        } catch (NoSuchAlgorithmException var14) {
            logger.error("RSA算法不存在", var14);
        } catch (NoSuchPaddingException var15) {
            logger.error("错误的签名秘钥", var15);
        } catch (InvalidKeyException var16) {
            logger.error("错误的签名秘钥", var16);
        } catch (GeneralSecurityException var17) {
            logger.error(var17.getMessage(), var17);
        }

        return data;
    }

    public static String encrypt(String content, String key) {
        byte[] keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;

        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(1, publicKey);
            InputStream ins = new ByteArrayInputStream(StringUtils.getBytesUtf8(content));
            byte[] data = read(cipher, ins, 100);
            return Base64.encodeBase64String(data);
        } catch (IOException var9) {
            logger.error(var9.getMessage(), var9);
        } catch (BadPaddingException var10) {
            logger.error(var10.getMessage(), var10);
        } catch (InvalidKeySpecException var11) {
            logger.error("错误的签名秘钥", var11);
        } catch (IllegalBlockSizeException var12) {
            logger.error(var12.getMessage(), var12);
        } catch (NoSuchAlgorithmException var13) {
            logger.error("RSA算法不存在", var13);
        } catch (NoSuchPaddingException var14) {
            logger.error("错误的签名秘钥", var14);
        } catch (InvalidKeyException var15) {
            logger.error("错误的签名秘钥", var15);
        } catch (GeneralSecurityException var16) {
            logger.error(var16.getMessage(), var16);
        }

        return content;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        KeyFactory keyFactory = null;

        try {
            keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(StringUtils.getBytesUtf8(content));
            return signature.verify(Base64.decodeBase64(sign));
        } catch (NoSuchAlgorithmException var7) {
            logger.error("RSA算法不存在", var7);
        } catch (SignatureException var8) {
            logger.error("签名异常", var8);
        } catch (InvalidKeyException var9) {
            logger.error("错误的签名秘钥", var9);
        } catch (InvalidKeySpecException var10) {
            logger.error("错误的签名秘钥", var10);
        }

        return false;
    }

    public static String sign(String content, String privateKey) {
        PrivateKey priKey = getPrivateKey(privateKey);

        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(StringUtils.getBytesUtf8(content));
            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (NoSuchAlgorithmException var5) {
            logger.error("RSA算法不存在", var5);
        } catch (SignatureException var6) {
            logger.error("签名异常", var6);
        } catch (InvalidKeyException var7) {
            logger.error("错误的签名秘钥", var7);
        }

        return null;
    }

    private static PrivateKey getPrivateKey(String key) {
        Future<PrivateKey> f = (Future) privateKeyMap.get(key);
        if (f == null) {
            Callable<PrivateKey> callable = () -> {
                logger.debug("calculate private key");
                byte[] keyBytes = Base64.decodeBase64(key);
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
                KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
                return privateKey;
            };
            FutureTask<PrivateKey> ft = new FutureTask(callable);
            f = (Future) privateKeyMap.putIfAbsent(key, ft);
            if (f == null) {
                f = ft;
                ft.run();
            }
        }

        try {
            return (PrivateKey) ((Future) f).get();
        } catch (InterruptedException var4) {
            logger.error(var4.getMessage(), var4);
        } catch (ExecutionException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    private static byte[] read(Cipher cipher, InputStream ins, int size) throws IOException, BadPaddingException, IllegalBlockSizeException {
        byte[] buf = new byte[size];

        int bufl;
        ByteArrayOutputStream writer;
        byte[] block;
        for (writer = new ByteArrayOutputStream(); (bufl = ins.read(buf)) != -1; writer.write(cipher.doFinal(block))) {
            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];

                for (int i = 0; i < bufl; ++i) {
                    block[i] = buf[i];
                }
            }
        }

        return writer.toByteArray();
    }

    public static void main(String[] args) throws Exception {
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOmBJblWRnr/G+H7Axd7Os6iQ12K6m6+4yPhFUnLjhvJYO6mXgffvP3TsDbpFV0XMx7ItRAHiuwMhpd3EI4OWWNZb78r3q4XkywO04VQHDtWU5yUiNxhO/9gNAS6Xwmheg67LVh/dvmxtOlO5VNyX3u0C4snw+gZ7Yrs3cHV5RCpAgMBAAECgYBsrxgH9AhKJ4Oq41LFEy6EDzKbz5TehyriAFoQRxaWCF19tyH9OD6XRni2ljbDZZD9ux3LoDchN5NN3LVv6W3g1JaOqKIIfdrOhTEtHhcO5wGHHxl61D/bJ3ojxFieMtsRb1BaDRbfrQkLsZtiw0qghrfYxUte9xtU/YiBBQD8oQJBAPh8B3WXRuPOWWMfl94pIrnOoEC1s39PEvjKIFeSk0q+DxQLI2oXcOmkZaYRarrZv/qdhUWYb6rdkOJTWSuSYqsCQQDwkSF2W2aqgnQBTqm1UmQoomh7kOCpCVQBLx5W4NngW6+JTxyZbWRcCEAHTLjbqjmH8gWjj86egp2ESMJiMvn7AkA4QiyWLRf5v+BxFtgVjo60LtoNjJYFPuv2tBy6dw8uHvXe/d6YyJHh9Dynas4VW/OhVES2SxsNPnjtJR3NRaldAkEAlbPK3gts7Si3JeUcyOBXwc2nNVXn6GkMpJv8xlWwX+TObKUViAjCDl938NL2qoPYv1eF2M3x50Qi36IjjNZqJwJBAJsxYNIYWwk+clCy8rMEkcwYv9XbhDAF3paRwMEgWAOXKjlmCwa/gXFgIVLxoYqxuSTcqVoMQsUV/NBF6wQoSL4=";
        PrivateKey key = getPrivateKey(privateKey);
        System.out.println("key : " + key);
        key = getPrivateKey(privateKey);
        System.out.println("key : " + key);
    }
}