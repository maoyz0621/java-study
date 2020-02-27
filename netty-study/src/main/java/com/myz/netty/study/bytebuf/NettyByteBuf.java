/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * 通过readerIndex writerIndex  capacity 将ByteBuf分成3个区域
 * 0 -- readerIndex　已经读取的区域
 * readerIndex -- writerIndex  可读的区域
 * writerIndex -- capacity  可写的区域
 *
 * @author maoyz0621 on 20-2-22
 * @version v1.0
 */
public class NettyByteBuf {

    public static void main(String[] args) {
        // main0();
        main1();
    }

    /**
     * readByte() -->
     * int i = readerIndex;
     * byte b = _getByte(i);
     * readerIndex = i + 1;
     */
    private static void main0() {
        ByteBuf buffer = Unpooled.buffer(10);

        // writeByte()改变index
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }

        // UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 10, cap: 10)
        System.out.println(buffer);

        // readByte()改变index
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }

        // UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 10, widx: 10, cap: 10)
        System.out.println(buffer);
    }

    private static void main1() {
        ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

        // UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 11, cap: 33)
        System.out.println(content);

        if (content.hasArray()) {
            byte[] array = content.array();
            System.out.println(new String(array, CharsetUtil.UTF_8));
            // capacity=长度*3, 11 * 3
            System.out.println("capacity = " + content.capacity());
            // 0
            System.out.println("offset = " + content.arrayOffset());
            // 0
            System.out.println("readerIndex = " + content.readerIndex());
            // 11
            System.out.println("writerIndex = " + content.writerIndex());

            // 可读取的字节数量 return writerIndex - readerIndex;
            int len = content.readableBytes();
            System.out.println("readableBytes = " + len);
            for (int i = 0; i < len; i++) {
                System.out.println(content.getByte(i) + " -> " + (char) content.getByte(i));
            }

            // index开始，取指定长度
            System.out.println(content.getCharSequence(0, 7, CharsetUtil.UTF_8));
            System.out.println(content.getCharSequence(5, 3, CharsetUtil.UTF_8));
        }


    }
}
