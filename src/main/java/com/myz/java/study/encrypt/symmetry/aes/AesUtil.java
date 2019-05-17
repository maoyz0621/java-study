package com.myz.java.study.encrypt.symmetry.aes;


import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES 加密工具类
 *
 * @author maoyz0621
 */
public class AesUtil {

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 秘钥 16位
     */
    private static final String PASSWORD = "e!@$%s^&*a)_m+s.";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        return encrypt(content, PASSWORD);
    }


    /**
     * AES 加密操作
     *
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));

            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            Logger.getLogger(AesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     */
    public static String decrypt(String content) {
        return decrypt(content, PASSWORD);
    }

    /**
     * AES 解密操作
     */
    public static String decrypt(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
            Logger.getLogger(AesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     * linux会出现： javax.crypto.BadPaddingException: Given final block not properly padded. Such issues can arise if a bad key is used during decryption.
     * 解决方案：https://www.cnblogs.com/zempty/p/4318902.html
     */
    private static SecretKeySpec getSecretKey(final String password) {
        if (null == password || password.length() == 0) {
            throw new NullPointerException("key not is null");
        }
        SecretKeySpec secretKeySpec = null;
        SecureRandom random = null;
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator generator = null;

        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            generator = KeyGenerator.getInstance(KEY_ALGORITHM);

            //AES 要求密钥长度为 128
            generator.init(128, random);

            //生成一个密钥
            SecretKey secretKey = generator.generateKey();
            // 转换为AES专用密钥
            secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AesUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return secretKeySpec;
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        String s1 = AesUtil.encrypt(s, "1234");
        System.out.println("s1:" + s1);

        System.out.println("s2:" + AesUtil.decrypt(s1, "1234"));


    }

}
