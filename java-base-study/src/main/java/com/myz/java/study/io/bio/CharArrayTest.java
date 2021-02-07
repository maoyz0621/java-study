package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 字节数组
 *
 *   ByteArrayInputStream
 *   ByteArrayOutputStream
 *   通过用合适的 BufferedReader替代每个DataInputStream，
 * 可以对将DataInputStream用于文字输入的程序进行本地化
 * @author maoyz on 18-3-16.
 */
public class CharArrayTest {

    @Test
    public void testRead() {

        char[] data = "是否离开是附件是离开飞机上了飞机".toCharArray();

        try (
                CharArrayReader inputStream = new CharArrayReader(data);
                BufferedReader in = new BufferedReader(inputStream);
        ) {
            String len = null;

            while (null != (len = in.readLine())) {
                System.out.println(new String(len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWrite() {
        char[] data = "是否离开是附件是离开飞机上了飞机".toCharArray();

        try (
                CharArrayWriter outputStream = new CharArrayWriter();
                BufferedWriter out = new BufferedWriter(outputStream);
        ) {
            out.write(data, 0, data.length);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
