/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义编码器
 * MessageToByteEncoder,实际是　ChannelOutboundHandler
 * 需要注意: 传入的数据类型和处理的数据类型一致, 才会起编码作用
 *
 * @author maoyz0621 on 20-2-29
 * @version v1.0
 */
public class MyEncoder extends MessageToByteEncoder<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        LOGGER.debug("******************* MyEncoder encode() *********************");
        System.out.println("=============== " + msg);

        out.writeLong(msg);
    }
}
