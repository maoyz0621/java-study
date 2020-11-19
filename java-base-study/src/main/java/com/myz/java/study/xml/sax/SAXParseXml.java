package com.myz.java.study.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * SAX:Simple API for XML
 *
 * @author maoyz on 18-1-2.
 */
public class SAXParseXml {

    private void a() {
        // 创建一个解析XML的工厂对象
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            // 创建一个解析XML的对象
            SAXParser saxParser = saxParserFactory.newSAXParser();
            // 创建一个解析助手类
            MyHandler handler = new MyHandler();
            saxParser.parse("", handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
