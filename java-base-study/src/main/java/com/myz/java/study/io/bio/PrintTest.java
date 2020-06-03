package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 打印流
 *
 *   PrintStream
 *      PrintStream out = System.out;
 *   PrintWriter
 * @author maoyz on 18-3-17.
 */
public class PrintTest {

    String pathname = "/home/maoyz/文档/JAVAWeb/first/resources"+ File.separator + "files/demo/2.txt";

    @Test
    public void testStream() {
        // PrintStream out = System.out;

        try (
                PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(pathname)));
        ) {
            out.println("aaa");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriter(){
        try (
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(pathname)));
        ) {
            out.println("bbb");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
