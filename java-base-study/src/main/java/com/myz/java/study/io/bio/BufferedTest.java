package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 缓存流
 *   BufferedInputStream
 *   BufferedOutputStream
 *   BufferedReader  readLine()
 *   BufferedWriter  newLine()
 *
 * @author maoyz on 18-3-16.
 */
public class BufferedTest {

    String pathname = "/home/maoyz/文档/JAVAWeb/first/resources"+ File.separator + "files/demo/2.txt";

    /**
     * BufferedInputStream
     * BufferedOutputStream
     */
    @Test
    public void testStream() {
        File file = new File(pathname);
        //　如果是目录
        if (file.isDirectory()) {
            return;
        }
        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream("/home/maoyz/文档/JAVAWeb/first/resources" + File.separator + "files/demo/3.txt", true);
                //　包装
                BufferedInputStream r = new BufferedInputStream(in);
                BufferedOutputStream w = new BufferedOutputStream(out);
        ) {
            int len = 0;
            byte[] buf = new byte[1024];
            // 将文件1内容读取出
            while (-1 != (len = r.read(buf))) {
                //将读取内容写入文件2
                w.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedInputStream
     * BufferedOutputStream
     */
    @Test
    public void testReader() {
        File file = new File(pathname);
        //　如果是目录
        if (file.isDirectory()) {
            return;
        }
        try (
                Reader in = new FileReader(file);
                Writer out = new FileWriter("/home/maoyz/文档/JAVAWeb/first/resources" + File.separator + "files/demo/4.txt");
                //　包装
                BufferedReader r = new BufferedReader(in);
                BufferedWriter w = new BufferedWriter(out);
        ) {
            int len = 0;
            String info = null;
            // r.readLine()读取一行
            while (null != (info = r.readLine())) {
                w.write(info);
                // 写入一个行分隔符
                w.newLine();
                w.append("激发剂");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
