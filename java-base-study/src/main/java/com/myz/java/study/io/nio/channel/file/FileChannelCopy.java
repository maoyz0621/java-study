/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.nio.channel.file;

import org.apache.commons.compress.utils.IOUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * FileChannel.transferXX
 * 减少数据从内核到用户空间的复制，数据直接在内核空间中移动，在Linux中使用sendfile系统调用。
 *
 * @author maoyz0621 on 19-12-29
 * @version: v1.0
 */
public class FileChannelCopy {

    static String basePath = FileChannelCopy.class.getClassLoader().getResource("").getPath();

    public static void main(String[] args) throws IOException {
        main0();
        main1();
    }

    /**
     * transferFrom()
     */
    public static void main0() {
        String pathName0 = "/files/demo/1.png";
        String pathName1 = "/files/demo/2.png";
        RandomAccessFile fileIn = null;
        RandomAccessFile fileOut = null;
        FileChannel channelIn = null;
        FileChannel channelOut = null;
        try {
            fileIn = new RandomAccessFile(basePath + pathName0, "rw");
            fileOut = new RandomAccessFile(basePath + pathName1, "rw");
            // 获取Channel
            channelIn = fileIn.getChannel();
            channelOut = fileOut.getChannel();
            //
            channelOut.transferFrom(channelIn, 0, channelIn.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileIn);
            IOUtils.closeQuietly(fileOut);
            IOUtils.closeQuietly(channelIn);
            IOUtils.closeQuietly(channelOut);
        }
    }

    /**
     * transferTo()
     */
    public static void main1() {
        String pathName0 = "/files/demo/1.png";
        String pathName1 = "/files/demo/3.png";
        RandomAccessFile fileIn = null;
        RandomAccessFile fileOut = null;
        FileChannel channelIn = null;
        FileChannel channelOut = null;
        try {
            fileIn = new RandomAccessFile(basePath + pathName0, "rw");
            fileOut = new RandomAccessFile(basePath + pathName1, "rw");
            // 获取Channel
            channelIn = fileIn.getChannel();
            channelOut = fileOut.getChannel();
            channelIn.transferTo(0, channelIn.size(), channelOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileIn);
            IOUtils.closeQuietly(fileOut);
            IOUtils.closeQuietly(channelIn);
            IOUtils.closeQuietly(channelOut);
        }
    }
}
