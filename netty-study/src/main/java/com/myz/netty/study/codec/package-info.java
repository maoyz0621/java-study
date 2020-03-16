/**
 * 编解码器
 * <p>
 * ObjectEncoder  ObjectDecoder
 * LengthFieldPrepender: 带有长度字段的数据包编解码。
 * LineBasedFrameDecoder: 以行字符串为一个数据包解码。
 * StringEncoder, StringDecoder: 字符集转换器。
 * Base64Encoder, Base64Decoder: Base64编码转换器。
 * ProtobufEncoder, ProtobufDecoder: protoBuf序列化格式数据包的编解码。
 * ZlibEncoder,ZlibDecoder: zlib压缩格式数据包的编解码。
 * SnappyFramedEncoder,SnappyFramedDecoder: Snappy压缩格式数据包的编解码。
 * <p>
 * DelimiterBasedFrameDecoder是基于消息边界方式进行粘包拆包处理的。
 * <p>
 * FixedLengthFrameDecoder是基于固定长度消息进行粘包拆包处理的。
 * <p>
 * LengthFieldBasedFrameDecoder是基于消息头指定消息长度进行粘包拆包处理的。
 * <p>
 * LineBasedFrameDecoder是基于行来进行消息粘包拆包处理的。
 * <p>
 * 解码: ByteToMessageDecoder -> ChannelInboundHandlerAdapter
 * 编码: MessageToByteEncoder -> ChannelOutboundHandlerAdapter
 * <p>
 * ChannelHandler调用链机制
 *
 * @author maoyz0621 on 20-2-29
 * @version v1.0
 */
package com.myz.netty.study.codec;