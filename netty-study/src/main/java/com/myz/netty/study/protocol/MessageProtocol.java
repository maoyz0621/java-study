/**
 * Copyright 2020 Inc.
 **/
package com.myz.netty.study.protocol;

import java.io.Serializable;

/**
 * 自定义协议
 *
 * @author maoyz0621 on 20-3-1
 * @version v1.0
 */
public class MessageProtocol implements Serializable {

    private static final long serialVersionUID = -8543898911608054864L;

    /**
     * 头信息
     */
    private String header;

    /**
     * 消息体长度
     */
    private int length;

    /**
     * 消息体内容　字节
     */
    private byte[] content;

    public MessageProtocol() {
    }

    public MessageProtocol(int length, byte[] content) {
        this.length = length;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("MessageProtocol{");
            sb.append("header='").append(header).append('\'');
            sb.append(", length=").append(length);
            sb.append(", content=").append(new String(content, "utf-8"));
            sb.append('}');
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
