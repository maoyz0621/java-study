/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-02-20 16:12  Inc. All rights reserved.
 */
package com.myz.java.study.secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

/**
 * @author maoyz
 */
public class AES256Utils {

    private static final Logger logger = LoggerFactory.getLogger(AES256Utils.class);
    private static int pwdIterations = 65536;
    private static int keySize = 256;
    private static byte[] ivBytes;
    private static String keyAlgorithm = "AES";
    private static String encryptAlgorithm = "AES/CBC/PKCS5Padding";
    private static String secretKeyFactoryAlgorithm = "PBKDF2WithHmacSHA1";

    static {
        String errorString = "Failed manually overriding key-length permissions.";

        int newMaxKeyLength;
        try {
            if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength(keyAlgorithm)) < keySize) {
                Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
                Constructor con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissionCollection = con.newInstance();
                Field f = c.getDeclaredField("all_allowed");
                f.setAccessible(true);
                f.setBoolean(allPermissionCollection, true);
                c = Class.forName("javax.crypto.CryptoPermissions");
                con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object allPermissions = con.newInstance();
                f = c.getDeclaredField("perms");
                f.setAccessible(true);
                ((Map) f.get(allPermissions)).put("*", allPermissionCollection);
                c = Class.forName("javax.crypto.JceSecurityManager");
                f = c.getDeclaredField("defaultPolicy");
                f.setAccessible(true);
                Field mf = Field.class.getDeclaredField("modifiers");
                mf.setAccessible(true);
                mf.setInt(f, f.getModifiers() & -17);
                f.set((Object) null, allPermissions);
                newMaxKeyLength = Cipher.getMaxAllowedKeyLength(keyAlgorithm);
            }
        } catch (Exception var8) {
            throw new RuntimeException(errorString, var8);
        }

        if (newMaxKeyLength < 256) {
            throw new RuntimeException(errorString);
        }
    }


    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String text = new String(bytes);
        return text;
    }

    public static String encyrpt(String plainText, String tokenKey, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
        PBEKeySpec spec = new PBEKeySpec(tokenKey.toCharArray(), saltBytes, pwdIterations, keySize);
        SecretKey secretKey = skf.generateSecret(spec);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);
        Cipher cipher = Cipher.getInstance(encryptAlgorithm);
        cipher.init(1, key);
        ivBytes = ((IvParameterSpec) cipher.getParameters().getParameterSpec(IvParameterSpec.class)).getIV();
        byte[] encryptedText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedText);
    }

    public static String decrypt(String encryptText, String tokenKey, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
        byte[] encryptTextBytes = Base64.getDecoder().decode(encryptText);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(secretKeyFactoryAlgorithm);
        PBEKeySpec spec = new PBEKeySpec(tokenKey.toCharArray(), saltBytes, pwdIterations, keySize);
        SecretKey secretKey = skf.generateSecret(spec);
        SecretKeySpec key = new SecretKeySpec(secretKey.getEncoded(), keyAlgorithm);
        Cipher cipher = Cipher.getInstance(encryptAlgorithm);
        cipher.init(2, key, new IvParameterSpec(ivBytes));
        byte[] decyrptTextBytes = cipher.doFinal(encryptTextBytes);
        String text = new String(decyrptTextBytes);
        return text;
    }

}