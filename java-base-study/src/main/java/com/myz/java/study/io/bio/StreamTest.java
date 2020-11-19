package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 字节流
 * InputStream
 * OutputStream
 * <p>
 * 字符流
 * Reader
 * Writer
 *
 * @author maoyz on 18-3-16.
 */
public class StreamTest {
    /**
     * 文件路径表示方法
     */
    String pathname = "/home/maoyz/文档/JAVAWeb/first/resources" + File.separator + "files/demo/2.txt";

    @Test
    public void testRead() {
        InputStream in = null;

        try {
            in = new FileInputStream(new File(pathname));
            int len = 0;
            byte[] buf = new byte[1024];
            // 将文件1内容读取出
            while (-1 != (len = in.read(buf))) {
                String info = new String(buf, 0, len);
                System.out.println(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     */
    @Test
    public void testWrite() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(pathname), true);
            String info = "ad ad ad asd";
            byte[] buf = info.getBytes();
            out.write(buf, 0, buf.length);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writer
     */
    @Test
    public void testWriter() {

        try (
                Writer out = new FileWriter(new File(pathname));
        ) {
            String info = "搞得跟大哥大哥的非官方\r\n";
            // 直接写入字符串
            out.write(info);
            // 末尾追加内容
            out.append("追加内容了...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件拷贝
     */
    @Test
    public void testCopyFile() {
        File file = new File(pathname);
        //　如果是目录
        if (file.isDirectory()) {
            return;
        }
        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream("myz" + File.separator + "b2.properties", true);

                Reader r = new InputStreamReader(in, "UTF-8");
                Writer w = new OutputStreamWriter(out, "UTF-8");
        ) {
            int len = 0;
            byte[] buf = new byte[1024];
            // 将文件1内容读取出
            while (-1 != (len = in.read(buf))) {
                //将读取内容写入文件2
                out.write(buf, 0, len);
            }

            w.write("你好");
            char[] chs = new char[1024];
            while ((len = r.read(chs)) != -1) {
                w.write(chs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
