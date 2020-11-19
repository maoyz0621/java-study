package com.myz.java.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


/**
 * base64编解码操作
 */
public class Base64Utils {

    private Logger log = LoggerFactory.getLogger(getClass());


    /**
     * base64字符串编码
     */
    public String encode(String str) {
        return Base64.encodeToString(str.getBytes(), false);
    }


    /**
     * base64  byte[]编码
     */
    public String encode(byte[] bytes) {
        byte[] outBytes = Base64.encodeToByte(bytes, false);
        try {
            return new String(outBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Base64 encode bytes error", e);
            return "";
        }
    }


    /**
     * Base64字符串解码
     */
    public String decode(String str) {
        byte[] bytes = Base64.decode(str);
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Base64 decode string error", e);
            return "";
        }
    }

    /**
     * Base64 inputstream解码
     */
    public void decode(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] inputBytes = new byte[inputStream.available()];
            inputStream.read(inputBytes);
            outputStream.write(Base64.encodeToByte(inputBytes, false));
        } catch (IOException e) {
            log.error("Base64 decode inputstream error", e);
        }

    }

    /**
     * Base64 bytes解码
     */
    public String decode(byte[] bytes) {
        byte[] outBytes = Base64.decode(bytes);
        try {
            return new String(outBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Base64 decode bytes error", e);
            return "";
        }
    }

    /**
     * Base64二进制解码
     */
    public static byte[] decode2Byte(String base64) {
        return Base64.decode(base64);
    }
}
