package com.myz.java.study.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author maoyz on 18-1-2.
 */
public class MyHandler extends DefaultHandler {

    /**
     * 标志解析开始,只调用一次
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("解析开始...");
    }

    /**
     * 遍历xml文件开始的标签,调用多次
     *
     * @param uri        　元素的命名空间
     * @param localName  　元素的本地名称（不带前缀）
     * @param qName      开始标签，元素的限定名（带前缀）
     * @param attributes 元素的属性集合
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        System.out.println("执行startElement()...");
    }

    /**
     * 　获取节点值，调用多次，接收字符数据的通知。
     * 在DOM中 ch[start:end] 相当于Text节点的节点值（nodeValue）
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        System.out.println("执行characters()...");
    }

    /**
     * 遍历xml文件结束的标签，调用多次
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        System.out.println("执行endElement()...");
    }

    /**
     * 标志解析结束,只调用一次
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("解析结束...");
    }
}
