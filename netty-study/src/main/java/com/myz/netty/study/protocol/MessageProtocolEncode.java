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
        out.writeBytes(msg.getHeader().getBytes());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }

}
