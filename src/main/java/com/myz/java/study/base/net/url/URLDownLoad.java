package com.myz.java.study.base.net.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 模拟爬虫原理
 *
 * @author maoyz on 18-3-18.
 */
public class URLDownLoad {

    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://www.zhihu.com/question/24853633");

            try (
                    // 获取流
                    InputStream inputStream = url.openStream();
                    // 输入流
                    BufferedInputStream in = new BufferedInputStream(inputStream);
                    // 输出流
                    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("../b.html"));

            ) {
                byte[] flush = new byte[1024];
                int len = 0;

                while (-1 != (len = in.read(flush))) {
                    out.write(flush, 0, len);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
