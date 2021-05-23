// /*
//  * Copyright (C) 2021, All rights Reserved, Designed By
//  * @author: maoyz
//  * @Copyright: 2021-02-20 16:19  Inc. All rights reserved.
//  */
// package com.myz.java.study.secret;
//
// import org.apache.commons.codec.binary.Base64;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import sun.misc.BASE64Decoder;
//
// import javax.crypto.BadPaddingException;
// import javax.crypto.Cipher;
// import javax.crypto.IllegalBlockSizeException;
// import javax.crypto.NoSuchPaddingException;
// import java.io.*;
// import java.security.InvalidKeyException;
// import java.security.KeyFactory;
// import java.security.NoSuchAlgorithmException;
// import java.security.interfaces.RSAPrivateKey;
// import java.security.interfaces.RSAPublicKey;
// import java.security.spec.InvalidKeySpecException;
// import java.security.spec.PKCS8EncodedKeySpec;
// import java.security.spec.X509EncodedKeySpec;
// import java.util.concurrent.ConcurrentHashMap;
//
// /**
//  * @author maoyz
//  */
// public class RsaOpenSSLUtils {
//     private static final Logger logger = LoggerFactory.getLogger(RsaOpenSSLUtils.class);
//     private static final ConcurrentHashMap<String, RSAPrivateKey> privateKeyMap = new ConcurrentHashMap();
//     private static final ConcurrentHashMap<String, RSAPublicKey> publicKeyMap = new ConcurrentHashMap();
//     private static final char[] HEX_CHAR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//
//     public RsaOpenSSLUtils() {
//     }
//
//     public static RSAPrivateKey getPrivateKey(String alias, String path) {
//         RSAPrivateKey privateKey = (RSAPrivateKey)privateKeyMap.get(alias);
//         if (privateKey == null) {
//             logger.info("calculate private key");
//
//             try {
//                 InputStream in = RsaOpenSSLUtils.class.getClassLoader().getResourceAsStream(path);
//                 ByteArrayOutputStream bout = new ByteArrayOutputStream();
//                 byte[] tmpbuf = new byte[1024];
//
//                 int count;
//                 for(boolean var6 = false; (count = in.read(tmpbuf)) != -1; tmpbuf = new byte[1024]) {
//                     bout.write(tmpbuf, 0, count);
//                 }
//
//                 PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bout.toByteArray());
//                 KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//                 privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
//                 in.close();
//                 bout.close();
//             } catch (IOException var9) {
//                 logger.error(var9.getMessage(), var9);
//             } catch (NoSuchAlgorithmException var10) {
//                 logger.error("无此解密算法", var10);
//             } catch (InvalidKeySpecException var11) {
//                 logger.error("解密私钥非法", var11);
//             }
//
//             privateKeyMap.putIfAbsent(alias, privateKey);
//         }
//
//         return privateKey;
//     }
//
//     public static RSAPublicKey getPublicKey(String alias, String path) {
//         RSAPublicKey publicKey = (RSAPublicKey)publicKeyMap.get(alias);
//         if (publicKey == null) {
//             logger.info("calculate public key");
//
//             try {
//                 InputStream in = RsaOpenSSLUtils.class.getClassLoader().getResourceAsStream(path);
//                 BufferedReader br = new BufferedReader(new InputStreamReader(in));
//                 String readLine = null;
//                 StringBuilder sb = new StringBuilder();
//
//                 while((readLine = br.readLine()) != null) {
//                     if (readLine.charAt(0) != '-') {
//                         sb.append(readLine);
//                         sb.append('\r');
//                     }
//                 }
//
//                 BASE64Decoder base64Decoder = new BASE64Decoder();
//                 byte[] buffer = base64Decoder.decodeBuffer(sb.toString());
//                 KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//                 X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
//                 publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
//                 in.close();
//                 br.close();
//             } catch (IOException var11) {
//                 logger.error(var11.getMessage(), var11);
//             } catch (NoSuchAlgorithmException var12) {
//                 logger.error("无此解密算法", var12);
//             } catch (InvalidKeySpecException var13) {
//                 logger.error("加密公钥非法", var13);
//             }
//
//             publicKeyMap.putIfAbsent(alias, publicKey);
//         }
//
//         return publicKey;
//     }
//
//     public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
//         if (publicKey == null) {
//             throw new Exception("加密公钥为空, 请设置");
//         } else {
//             Cipher cipher = null;
//
//             try {
//                 cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//                 cipher.init(1, publicKey);
//                 byte[] output = cipher.doFinal(plainTextData);
//                 return output;
//             } catch (NoSuchAlgorithmException var4) {
//                 logger.error("无此加密算法", var4);
//             } catch (NoSuchPaddingException var5) {
//                 logger.error("错误的签名秘钥", var5);
//             } catch (InvalidKeyException var6) {
//                 logger.error("加密公钥非法", var6);
//             } catch (IllegalBlockSizeException var7) {
//                 logger.error("明文长度非法", var7);
//             } catch (BadPaddingException var8) {
//                 logger.error("明文数据已损坏", var8);
//             }
//
//             return null;
//         }
//     }
//
//     public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
//         if (privateKey == null) {
//             throw new Exception("解密私钥为空, 请设置");
//         } else {
//             Cipher cipher = null;
//
//             try {
//                 cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//                 cipher.init(2, privateKey);
//                 byte[] output = cipher.doFinal(cipherData);
//                 return output;
//             } catch (NoSuchAlgorithmException var4) {
//                 logger.error("无此解密算法", var4);
//             } catch (NoSuchPaddingException var5) {
//                 logger.error("错误的签名秘钥", var5);
//             } catch (InvalidKeyException var6) {
//                 logger.error("解密私钥非法", var6);
//             } catch (IllegalBlockSizeException var7) {
//                 logger.error("密文长度非法", var7);
//             } catch (BadPaddingException var8) {
//                 logger.error("密文数据已损坏", var8);
//             }
//
//             return null;
//         }
//     }
//
//     public static String encode(String str, String alias, String path) {
//         try {
//             byte[] cipher = encrypt(getPublicKey(alias, path), str.getBytes());
//             return Base64.encodeBase64String(cipher);
//         } catch (Exception var4) {
//             var4.printStackTrace();
//             return null;
//         }
//     }
//
//     public static String decode(String str, String alias, String path) {
//         try {
//             byte[] plainText = decrypt(getPrivateKey(alias, path), Base64.decodeBase64(str));
//             return new String(plainText);
//         } catch (Exception var4) {
//             var4.printStackTrace();
//             return null;
//         }
//     }
// }