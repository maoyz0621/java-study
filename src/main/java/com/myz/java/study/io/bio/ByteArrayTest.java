package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 字节数组
 *
 * ByteArrayInputStream
 * ByteArrayOutputStream
 * 此类中的方法在关闭此流后仍可被调用，而不会产生任何IOException
 * 创建临时性文件的程序以及网络数据的传输、数据压缩后的传输等可以提高运行的的效率，可以不用访问磁盘
 *
 * @author maoyz on 18-3-16.
 */
public class ByteArrayTest {

  @Test
  public void testRead() throws Exception {
    byte[] data = "是否离开是附件是离开飞机上了飞机".getBytes("utf-8");

    try (
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            BufferedInputStream in = new BufferedInputStream(inputStream);
    ) {
      int len = 0;
      byte[] buf = new byte[1024];

      while (-1 != (len = in.read(buf))) {
        System.out.println(new String(buf, 0, len));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWrite() throws Exception {
    byte[] data = "是否离开是附件是离开飞机上了飞机".getBytes("UTF-8");

    try (
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
    ) {
      out.write(data, 0, data.length);
      out.flush();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
