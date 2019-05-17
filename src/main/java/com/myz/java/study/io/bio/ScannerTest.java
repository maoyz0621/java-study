package com.myz.java.study.io.bio;

import java.io.*;
import java.util.Scanner;

/**
 * 扫描流
 *   new Scanner(System.in)
 *   scanner.nextLine()
 *
 * @author maoyz on 18-3-17.
 */
public class ScannerTest {

    public static void main(String[] args) throws FileNotFoundException {
        String pathname = "/home/maoyz/文档/JAVAWeb/first/resources"+ File.separator + "files/demo/1.txt";

        System.out.println(":");
        // 键盘输入
        InputStream in = System.in;

        // in = new BufferedInputStream(new FileInputStream(new File((pathname))));
        Scanner scanner = new Scanner(in);

        String info = null;

        while (null != (info = scanner.nextLine())){
            System.out.println("-->"+info);
        }

    }

}
