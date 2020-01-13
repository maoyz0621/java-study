/**
 * 零拷贝: 没有CPU拷贝
 * <p>
 * mmap:　内存映射，适合小数据量读写
 * 4次上下文切换，3次数据拷贝
 * <p>
 * sendFile:　适合大文件传输
 * 3次上下文切换，最少2次数据拷贝
 * linux2.1提供, 数据不经过用户态，直接从内核缓冲区进入到Socket Buffer
 * linux2.4提供, 数据不经过用户态，直接从内核缓冲区进入到Socket Buffer
 *
 * @author maoyz0621 on 20-1-8
 * @version: v1.0
 */
package com.myz.java.study.io.zerocopy;