/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.io.nio.buffer;

import org.junit.Test;

import java.nio.IntBuffer;

/**
 * @author maoyz0621 on 19-12-28
 * @version v1.0
 */
public class BufferTest {

    @Test
    public void testWrap() {
        IntBuffer intBuffer = IntBuffer.wrap(new int[]{1, 2, 3, 4, 5});
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

    @Test
    public void testAllocate() {
        final int size = 5;
        IntBuffer intBuffer = IntBuffer.allocate(size);
        for (int i = 0; i < size; i++) {
            intBuffer.put(i);
        }

        // Important
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

    @Test
    public void testWAllocate0() {
        final int size = 10;
        IntBuffer intBuffer = IntBuffer.allocate(size);
        for (int i = 0; i < size; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        // 设置容量
        intBuffer.limit(5);

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

    @Test
    public void testWAllocate1() {
        final int size = 10;
        IntBuffer intBuffer = IntBuffer.allocate(size);
        for (int i = 0; i < size; i++) {
            intBuffer.put(i);
        }
        intBuffer.flip();
        //　设置初始位置
        intBuffer.position(5);

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
