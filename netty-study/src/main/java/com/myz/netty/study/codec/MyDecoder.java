/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 自定义解码器
 * ByteToMessageDecoder, 实际是ChannelInboundHandler
 * <p>
 * 可以使用ReplayingDecoder
 *
 * @author maoyz0621 on 20-2-29
 * @version v1.0
 */
public class MyDecoder extends ByteToMessageDecoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDecoder.class);

    /**
     * decode　会根据接收的数据，被调用多次，直到确定没有新的元素添加到list中，或者是ByteBuf中没有更多的可读字节为止，
     * 如果list不为空，就会将list中的内容传递到下一个ChannelInboundHandler处理
     *
     * @param ctx
     * @param in  　入站的ByteBuf
     * @param out 将解码后的数据放入list 传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LOGGER.debug("******************* MyDecoder decode() *********************");

        if (in.readableBytes() >= 8) {
            out.add(in.readBytes(in.readableBytes()));
        }
    }
}
