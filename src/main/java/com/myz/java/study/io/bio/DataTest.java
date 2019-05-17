package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * DataInputStream    readXxx()
 * 从底层输入流中读取基本Java数据类型 + String
 * DataOutputStream   writeXxx()
 * 将基本Java数据类型写入输出流中
 * <p>
 * 操作读取数据的顺序必须和写入数据顺序一直，否则出错
 *
 * @author maoyz on 18-3-16.
 */
public class DataTest {

  String pathname = "../first/resources".replace("/", File.separator) + File.separator + "files/demo/6.txt".replace("/", File.separator);

  /**
   * 读取文件内容
   */
  @Test
  public void testRead() {
    try (
        DataInputStream input = new DataInputStream(
            new BufferedInputStream(
                new FileInputStream(pathname)));
    ) {
      // 要保存顺序一直
      String s1 = input.readUTF();
      double d = input.readDouble();
      short s = input.readShort();
      String s2 = input.readUTF();
      System.out.println("读取数据:\r\n" + d + " " + s + " " + s1 + " " + s2);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * DataOutputStream 写入文件中
   */
  @Test
  public void testWrite() {
    double d = 100L;
    short s = 12;
    String s1 = "abc";

    try (
        DataOutputStream out = new DataOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(pathname)));
    ) {
      out.writeUTF("utf-8");
      out.writeDouble(d);
      out.writeShort(s);
      out.writeUTF(s1);

      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
