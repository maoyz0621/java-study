package com.myz.java.study.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author maoyz on 18-1-15.
 */
public class Dom4jParseXml {

    private String source;

    public Dom4jParseXml(String source) {
        this.source = source;
    }

    /**
     * 获取 Document
     */
    private Document getDocument() throws DocumentException {
        // 将src下面的xml转换为输入流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(source);
        // 1 创建SAXReader读取器，专门用于读取xml
        SAXReader reader = new SAXReader();
        // 根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取
        Document document = reader.read(is);
        return document;
    }

    /**
     * 解析方法
     */
    public void paser() throws DocumentException {
        // 1 获取根节点对象
        Element root = this.getDocument().getRootElement();
        System.out.println("根节点名称：" + root.getName());
        System.out.println("==================");
        // 2 获取根节点下直接子节点
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            System.out.println("子节点名称：" + element.getName());
            System.out.println("子节点属性数目：" + element.attributeCount());
            System.out.println("子节点id属性的值：" + element.attributeValue("id"));
            // 3 获取节点属性内容
            for (Iterator<Attribute> attrs = element.attributeIterator(); attrs.hasNext(); ) {
                Attribute attr = attrs.next();
                System.out.println(attr.getName() + "-->" + attr.getText());
            }
            // 4 获取子节点内容
            Iterator<Element> ele = element.elementIterator();
            while (ele.hasNext()) {
                Element next = ele.next();
                System.out.println(next.getName() + ":" + next.getText());
            }
            System.out.println("-----------------");
        }

    }

}
