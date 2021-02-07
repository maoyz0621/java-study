package com.myz.java.study.io.bio;


import java.io.*;

/**
 * System
 *    InputStream in = System.in;
 *    PrintStream err = System.err;
 *    PrintStream out = System.out;
 *
 * 重定向
 *    System.setOut
 *    System.setErr
 *    System.setIn
 *
 * @author maoyz on 18-3-17.
 */
public class SystemTest {

  public static void main(String[] args) throws FileNotFoundException {

    String pathname = "../first/resources/files/demo/10.txt";

    // 重定向 　输出信息从控制台-->文件
    System.setOut(new PrintStream(
            new BufferedOutputStream(
                    new FileOutputStream(pathname, true)), true));

    System.setErr(new PrintStream(
            new BufferedOutputStream(
                    new FileOutputStream(pathname, true)), true));

    System.err.println("abc");
    System.out.println("cde");

    // 重回控制台
    System.setOut(new PrintStream(
            new BufferedOutputStream(
                    new FileOutputStream(FileDescriptor.out)), true));

    System.setErr(new PrintStream(
            new BufferedOutputStream(
                    new FileOutputStream(FileDescriptor.err)), true));

    System.out.println("系统输出:cde1111");
    System.err.println("系统输出:abc11111");
  }

}
