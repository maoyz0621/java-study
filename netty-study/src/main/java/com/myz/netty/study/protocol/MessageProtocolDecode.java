/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class MessageProtocolDecode extends ReplayingDecoder<Void> {

    /**
     * 将字节数组解析成　MessageProtocol
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        MessageProtocol protocol = new MessageProtocol();
        protocol.setHeader((String) in.readCharSequence(3, CharsetUtil.UTF_8));

        //
        int len = in.readInt();
        protocol.setLength(len);
        byte[] content = new byte[len];
        protocol.setContent(content);

        // 需要将content写入buf
        in.readBytes(content);

        out.add(protocol);
    }

}
