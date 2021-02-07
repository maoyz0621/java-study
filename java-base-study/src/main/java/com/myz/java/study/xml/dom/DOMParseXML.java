package com.myz.java.study.xml.dom;


import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author maoyz on 17-8-30.
 */
public class DOMParseXML {
    @Override
    public String toString() {
        return "DOMParseXML{" +
                "document=" + document +
                '}';
    }

    private Document document = null;

    private Document prepareDocument() {
        // 1 创建DocumentBuilderFactory对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            // 2 创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 3 parse()解析xml文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("user.xml");
            document = documentBuilder.parse(is);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 解析方法
     */
    public void parseXML(String tagName) {
        // 获取所有节点
        NodeList nodeList = prepareDocument().getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            // 获取person节点
            Node person = nodeList.item(i);

            // 遍历 person属性
            NamedNodeMap attrs = person.getAttributes();
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                //　获取属性名称
                String name = attr.getNodeName();
                // 获取属性值
                String value = attr.getNodeValue();
                System.out.println("获取节点属性" + name + ":" + value);
            }

            // 获取子节点,其中空白看成一个子节点
            NodeList childrenNode = person.getChildNodes();

            for (int j = 0; j < childrenNode.getLength(); j++) {
                Node child = childrenNode.item(j);
                // 判断字节点的属性
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    // 子节点属性名
                    System.out.print("获取子节点" + child.getNodeName() + ":");
                    //　子节点属性值
//                    System.out.println(child.getFirstChild().getNodeValue());
                    System.out.println(child.getTextContent());
                }
            }
            System.out.println("=============");
        }
    }

}
