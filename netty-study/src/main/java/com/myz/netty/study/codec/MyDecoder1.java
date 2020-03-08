/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 自定义解码器 方式2
 * ReplayingDecoder, 实际是ChannelInboundHandler
 *
 * @author maoyz0621 on 20-2-29
 * @version v1.0
 */
public class MyDecoder1 extends ReplayingDecoder<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyDecoder1.class);

    /**
     * @param ctx
     * @param in  　入站的ByteBuf
     * @param out 将解码后的数据放入list 传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LOGGER.debug("******************* MyDecoder decode() *********************");

        out.add(in.readBytes(in.readableBytes()));
    }
}
