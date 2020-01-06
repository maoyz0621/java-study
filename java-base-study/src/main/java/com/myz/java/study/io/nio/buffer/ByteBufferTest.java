/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.nio.buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * position 当前可读写的指针，如果是向ByteBuffer对象中写入一个字节，那么就会向position所指向的地址写入这个字节，如果是从ByteBuffer读出一个字节，那么就会读出position所指向的地址读出这个字节，读写完成后，position加1
 * limit 可以读写的边界，当position到达limit时，就表示将ByteBuffer中的内容读完，或者将ByteBuffer写满了
 * mark 对当前position的标记
 * capacity　ByteBuffer的容量
 * flip()
 * clear()
 * hasRemaining()
 *
 * @author maoyz0621 on 19-12-28
 * @version: v1.0
 */
public class ByteBufferTest {

    /**
     * allocate() 使用的内存开销是JVM上的
     */
    @Test
    public void test() {
        byte[] data = "Hello World".getBytes();
        final int capacity = data.length * 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        byteBuffer.put(data);
        // 写数据,pos当前位置, lim写的数据限制, java.nio.HeapByteBuffer[pos=11 lim=22 cap=22]
        System.out.println(byteBuffer);

        byteBuffer.flip();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=11 cap=22]
        System.out.println(byteBuffer);

        System.out.println(byteBuffer.get(2));
        System.out.println(byteBuffer);
    }

    /**
     * allocateDirect() 内存开销是在系统内存上的
     * java 程序接收数据首先是系统内存获取，系统内存再复制到JVM 上供程序使用。allocateDirect 就不用复制，效率会高一点。
     * <p>
     * 缺点是系统内存分配耗时比较多。
     */
    @Test
    public void test1() {
        byte[] data = "Hello World".getBytes();
        final int capacity = data.length;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(capacity);

        byteBuffer.put(data);
        System.out.println(byteBuffer);
        byteBuffer.flip();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=11 cap=22]
        System.out.println(byteBuffer);
    }

    /**
     * flip() 将limit值置为position值，position置0
     * limit = position;
     * position = 0;
     * mark = -1;
     */
    @Test
    public void testFlip() {
        byte[] data = "Hello World".getBytes();
        final int capacity = data.length * 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        byteBuffer.put(data);
        // 写数据,pos当前位置, lim写的数据限制, java.nio.HeapByteBuffer[pos=11 lim=22 cap=22]
        System.out.println(byteBuffer);

        byteBuffer.flip();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=11 cap=22]
        System.out.println(byteBuffer);
    }

    /**
     * clear() 并不将缓冲区的数据清空，而是设置position，mark，limit这三个变量的值
     * position = 0;
     * limit = capacity;
     * mark = -1;
     */
    @Test
    public void testClear() {
        byte[] data = "Hello World".getBytes();
        final int capacity = data.length * 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        byteBuffer.put(data);
        // 写数据,pos当前位置, lim写的数据限制, java.nio.HeapByteBuffer[pos=11 lim=22 cap=22]
        System.out.println(byteBuffer);

        byteBuffer.flip();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=11 cap=22]
        System.out.println(byteBuffer);

        byteBuffer.clear();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=22 cap=22]
        System.out.println(byteBuffer);
    }

    @Test
    public void testHasRemaining() {
        byte[] data = "Hello World".getBytes();
        final int capacity = data.length * 2;
        ByteBuffer byteBuffer = ByteBuffer.allocate(capacity);

        byteBuffer.put(data);
        // 写数据,pos当前位置, lim写的数据限制, java.nio.HeapByteBuffer[pos=11 lim=22 cap=22]
        System.out.println(byteBuffer);

        byteBuffer.flip();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=11 cap=22]
        System.out.println(byteBuffer);
        while (byteBuffer.hasRemaining()) {
            System.out.println("byteBuffer = " + byteBuffer.get());
        }
        // java.nio.HeapByteBuffer[pos=11 lim=11 cap=22]
        System.out.println("hasRemaining()之后:" + byteBuffer);

        byteBuffer.clear();
        // pos当前位置重置为0, lim读的数据限制, java.nio.HeapByteBuffer[pos=0 lim=22 cap=22]
        System.out.println(byteBuffer);
        System.out.println("clear()之后，再次hasRemaining()\r\n");
        while (byteBuffer.hasRemaining()) {
            System.out.println("byteBuffer = " + byteBuffer.get());
        }
    }
}
