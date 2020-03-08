/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class MessageProtocolEncode extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        encode0(msg, out);

        // encode1(msg, out);
    }

    /**
     * 此方法在处理多条msg时,  java.io.StreamCorruptedException: invalid stream header: 896DF935
     */
    private void encode1(MessageProtocol msg, ByteBuf out) {
        // 方法2　使用对象序列化
        byte[] bytes = ByteObjectConverter.objectToByte(msg);
        out.writeBytes(bytes, 0, bytes.length);
    }


    private void encode0(MessageProtocol msg, ByteBuf out) {
        out.writeBytes(msg.getHeader().getBytes());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }

}