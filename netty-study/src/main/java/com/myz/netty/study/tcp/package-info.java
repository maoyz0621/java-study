/**
 * 一　TCP 粘包和拆包
 * <p>
 * 为什么会发生TCP粘包、拆包？
 * 1、应用程序写入的数据大于套接字缓冲区大小，这将会发生拆包。
 * 2、应用程序写入数据小于套接字缓冲区大小，网卡将应用多次写入的数据发送到网络上，这将会发生粘包。
 * 3、进行MSS（最大报文长度）大小的TCP分段，当TCP报文长度-TCP头部长度>MSS的时候将发生拆包。
 * 4、接收方法不及时读取套接字缓冲区数据，这将发生粘包。
 * <p>
 * 二　粘包、拆包解决办法
 * <p>
 * 1、客户端在发送数据包的时候，每个包都固定长度，比如1024个字节大小，如果客户端发送的数据长度不足1024个字节，则通过补充空格的方式补全到指定长度；
 * 2、客户端在每个包的末尾使用固定的分隔符，例如\r\n，如果一个包被拆分了，则等待下一个包发送过来之后找到其中的\r\n，然后对其拆分后的头部部分与前一个包的剩余部分进行合并，这样就得到了一个完整的包；
 * 3、将消息分为头部和消息体，在头部中保存有当前整个消息的长度，只有在读取到足够长度的消息之后才算是读到了一个完整的消息；
 * 4、通过自定义协议进行粘包和拆包的处理。
 * <p>
 * 三　Netty提供的粘包拆包解决方案
 * <p>
 * 1、FixedLengthFrameDecoder
 * 对于使用固定长度的粘包和拆包场景，可以使用FixedLengthFrameDecoder，该解码一器会每次读取固定长度的消息，如果当前读取到的消息不足指定长度，那么就会等待下一个消息到达后进行补足。
 * <p>
 * 2、LineBasedFrameDecoder与DelimiterBasedFrameDecoder
 * 对于通过分隔符进行粘包和拆包问题的处理，Netty提供了两个编解码的类，LineBasedFrameDecoder和DelimiterBasedFrameDecoder。
 * 这里LineBasedFrameDecoder的作用主要是通过换行符，即\n或者\r\n对数据进行处理；
 * 而DelimiterBasedFrameDecoder的作用则是通过用户指定的分隔符对数据进行粘包和拆包处理。
 * <p>
 * 3、LengthFieldBasedFrameDecoder与LengthFieldPrepender
 * LengthFieldBasedFrameDecoder与LengthFieldPrepender需要配合起来使用
 * 主要思想是在生成的数据包中添加一个长度字段，用于记录当前数据包的长度。LengthFieldBasedFrameDecoder会按照参数指定的包长度偏移量数据对接收到的数据进行解码，从而得到目标消息体数据；
 * 而LengthFieldPrepender则会在响应的数据前面添加指定的字节数据，这个字节数据中保存了当前消息体的整体字节数据长度。LengthFieldBasedFrameDecoder的解码过程如下图所示：
 * <p>
 * 关于LengthFieldBasedFrameDecoder，这里需要对其构造函数参数进行介绍：
 * <p>
 * - maxFrameLength：指定了每个包所能传递的最大数据包大小；
 * - lengthFieldOffset：指定了长度字段在字节码中的偏移量；
 * - lengthFieldLength：指定了长度字段所占用的字节长度；
 * - lengthAdjustment：对一些不仅包含有消息头和消息体的数据进行消息头的长度的调整，这样就可以只得到消息体的数据，这里的lengthAdjustment指定的就是消息头的长度；
 * - initialBytesToStrip：对于长度字段在消息头中间的情况，可以通过initialBytesToStrip忽略掉消息头以及长度字段占用的字节。
 * <p>
 * //添加支持粘包、拆包解码器，意义：从头两个字节解析出数据的长度，并且长度不超过1024个字节
 * new LengthFieldBasedFrameDecoder(1024, 0, 2, 0, 2))
 * //添加支持粘包、拆包编码器，发送的每个数据都在头部增加两个字节表消息长度
 * new LengthFieldPrepender(2))
 *
 * @author maoyz0621 on 20-1-14
 * @version v1.0
 */
package com.myz.netty.study.tcp;