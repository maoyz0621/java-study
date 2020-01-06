/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.io.nio.buffer;

import com.myz.java.study.io.nio.channel.file.FileChannelCopy;
import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel.map()
 * MappedByteBuffer 内存映射文件
 * 将文件按照一定大小块映射为内存区域，当程序访问这个内存区域时将直接操作这个文件数据，这种方式省去了数据从内核空间向用户空间复制的损耗。这种方式适合对大文件的只读性操作，如大文件的MD5校验。这种方式和操作系统底层I/O实现相关。
 *
 * @author maoyz0621 on 20-1-1
 * @version: v1.0
 */
public class MappedByteBufferTest {

    static String basePath = FileChannelCopy.class.getClassLoader().getResource("").getPath();

    public static void main(String[] args) {
        String pathName0 = "/files/demo/1.txt";

        RandomAccessFile fileIn = null;

        FileChannel channelIn = null;

        try {
            fileIn = new RandomAccessFile(basePath + pathName0, "rw");

            // 获取Channel
            channelIn = fileIn.getChannel();

            MappedByteBuffer mappedByteBuffer = channelIn.map(FileChannel.MapMode.READ_WRITE, 0, channelIn.size());
            //
            System.out.println(mappedByteBuffer);

            mappedByteBuffer.put(0, Byte.parseByte("111"));
            mappedByteBuffer.put(2, Byte.parseByte("2"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileIn);

            IOUtils.closeQuietly(channelIn);

        }
    }
}
