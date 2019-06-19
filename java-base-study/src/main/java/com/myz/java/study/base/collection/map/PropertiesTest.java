package com.myz.java.study.base.collection.map;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * Properties是Hashtable的子类
 *
 * @author maoyz on 18-2-28.
 */
public class PropertiesTest {

    @Test
    public void test() throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        System.out.println(properties.getProperty("username"));
    }

    @Test
    public void testProperties() throws IOException {
        InputStream inStream = ClassLoader.getSystemResourceAsStream("db.properties");
        Properties p = new Properties();
        p.load(inStream);
        System.out.println(p.getProperty("username"));
    }

    /**
     * @throws IOException
     */
    @Test
    public void testStore() throws IOException {
        Properties p = new Properties();

        p.setProperty("name", "123");
        p.setProperty("age", "12");

        System.out.println(p.getProperty("name", "值不存在"));

        try {
            // 保存文件，使用相对路径
            p.store(new FileOutputStream(new File("src/com/collection/map/demo.properties")), "使用相对路径保存.properties文件");
            p.storeToXML(new FileOutputStream(new File("src/com/collection/map/demo.xml")), "使用相对路径保存为.xml文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //读取文件
            p.load(new FileInputStream(new File("demo.properties")));
            System.out.println(p.getProperty("age"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
