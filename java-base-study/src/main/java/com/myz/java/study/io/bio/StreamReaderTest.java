package com.myz.java.study.io.bio;


import org.junit.Test;

import java.io.*;


/**
 * @author maoyz
 */
public class StreamReaderTest {
    String pathname = "../first/resources" + File.separator + "files/demo/2.txt";

    @Test
    public void test() {
        File file = new File(pathname);
        if (file.isDirectory()) {
            return;
        }
        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream("/home/maoyz/文档/JAVAWeb/first/resources" +
                        File.separator + "files/demo/5.txt");

                // 字节流转字符流,指定编码
                Reader r = new InputStreamReader(in, "UTF-8");
                Writer w = new OutputStreamWriter(out, "UTF-8");
        ) {
            int len = 0;

            w.write("你好");
            char[] chs = new char[1024];

            while ((len = r.read(chs)) != -1) {
                w.write(chs, 0, len);
            }
            w.write("aaaa");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
