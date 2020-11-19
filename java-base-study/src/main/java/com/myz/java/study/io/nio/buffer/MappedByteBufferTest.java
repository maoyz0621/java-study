/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.buffer;

import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel.map()
 * MappedByteBuffer 内存映射文件, 子类 -> DirectByteBuffer
 * 将文件按照一定大小块映射为内存区域，当程序访问这个内存区域时将直接操作这个文件数据，不需要在拷贝一次, 直接在堆外内存中操作, 省去了数据从内核空间向用户空间复制的损耗
 * 这种方式适合对大文件的只读性操作，如大文件的MD5校验。
 * 这种方式和操作系统底层I/O实现相关。
 *
 * @author maoyz0621 on 20-1-1
 * @version v1.0
 */
public class MappedByteBufferTest {

    static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    public static void main(String[] args) {
        String pathName0 = "/src/main/resources/files/demo/1.txt";

        RandomAccessFile fileIn = null;

        FileChannel channelIn = null;

        try {
            fileIn = new RandomAccessFile((new File(basePath).getParentFile().getParent() + pathName0).replace("/", File.separator), "rw");

            // 获取Channel
            channelIn = fileIn.getChannel();

            // 实际类型是　DirectByteBuffer
            MappedByteBuffer mappedByteBuffer = channelIn.map(FileChannel.MapMode.READ_WRITE, 0, channelIn.size());
            // java.nio.DirectByteBuffer[pos=0 lim=60 cap=60]
            System.out.println(mappedByteBuffer);

            mappedByteBuffer.put(0, (byte) 'A');
            mappedByteBuffer.put(2, (byte) 'C');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileIn);

            IOUtils.closeQuietly(channelIn);

        }
    }
}
