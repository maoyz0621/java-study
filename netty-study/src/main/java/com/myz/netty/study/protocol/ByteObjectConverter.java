/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.protocol;

import java.io.*;

/**
 * Object　和　byte[] 转换
 *
 * @author maoyz0621 on 20-3-8
 * @version v1.0
 */
public class ByteObjectConverter {

    /**
     * 序列化对象　反序列化
     *
     * @param bytes 已序列化二进制对象
     * @param <T>   　转化对象
     * @return T
     */
    public static <T> T byteToObject(byte[] bytes) {
        T t = null;
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(byteArrayInputStream))
        ) {
            t = (T) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 对象序列化
     *
     * @param obj 待转化对象
     * @param <T> 　需转化成的对象
     * @return　对象
     */
    public static <T> byte[] objectToByte(T obj) {
        byte[] bytes = null;
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream))
        ) {
            outputStream.writeObject(obj);
            outputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static void main(String[] args) {

    }

}
