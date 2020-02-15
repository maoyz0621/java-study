/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.io.nio.channel.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 以Channel视角
 * FileChannel
 * allocate() wrap() 创建缓冲区
 *
 * @author maoyz on 18-10-21
 * @version: v1.0
 */
public class FileChannelTest {


    //获取项目的根路径
    private final static String classPath;

    static {
        //获取的是classpath路径，适用于读取resources下资源
        classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getParentFile().getParent();
        System.out.println("classpath路径 = " + classPath);
    }

    /**
     * 写入文件
     *
     * @throws IOException
     */
    @Test
    public void testWrite() throws IOException {
        RandomAccessFile file = null;
        FileChannel channel = null;

        String pathName = "/src/main/resources/files/demo/2.txt";
        String path = (classPath + pathName).replace("/", File.separator);

        try {
            // 方法1
            file = new RandomAccessFile(path, "rw");

            // 获取文件Channel
            channel = file.getChannel();

            // 方法2
            // FileOutputStream fileOutputStream = new FileOutputStream(pathName);
            // FileChannel channel0 = fileOutputStream.getChannel();

            // 初始化Buffer，设定Buffer每次可以存储数据量
            // 创建的Buffer是1024byte的，如果实际数据本身就小于1024，那么limit就是实际数据大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 1 缓存区存放数据
            buffer.put("abcdef".getBytes());
            // 2 此处必须要调用buffer的flip方法,将Buffer写模式切换到读模式并且将position置为0
            buffer.flip();
            // 3 从Buffer读取数据到Channel
            int count = channel.write(buffer);
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (file != null) {
                file.close();
            }
        }
    }

    /**
     * 读取文件
     *
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {
        String pathName = "/src/main/resources/files/demo/1.txt";
        String path = (classPath + pathName).replace("/", File.separator);
        RandomAccessFile file = null;
        FileChannel channel = null;
        // 设置Buffer缓存区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            file = new RandomAccessFile(path, "rw");
            // 获取Channel
            channel = file.getChannel();
            // 1 Channel从Buffer read into buffer.
            int read = channel.read(buffer);
            while (-1 != read) {
                System.out.println(read);
                // 2 此处必须要调用buffer的flip方法,将Buffer写模式切换到读模式，将position设回0，并将limit设置成之前position的值。
                buffer.flip();

                // 3 hasRemaining()是否读取完
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }

                // 只会清除已经读过的数据，任何未读的数据都被转移到缓冲区起始处，新写入的数据将放到缓冲区未读数据的后面。
                buffer.compact();
                // 清除整个缓冲区
                buffer.clear();
                // 3 Channel从Buffer中读取数据
                read = channel.read(buffer);
                System.out.println(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (file != null) {
                file.close();
            }
        }
    }
}
