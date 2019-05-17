package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile
 *   此类的实例支持对随机访问文件的读取和写入
 * @author maoyz on 18-3-16.
 */
public class RandomAccessFileTest {

    String pathname = "../first/resources"+
            File.separator + "files/demo/11.txt";

    @Test
    public void testRandomAccessFile(){
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(pathname, "rw");
            // getFilePointer()指针位置
            System.out.println("偏移量:"+raf.getFilePointer());
            String str = "aaaaabbbbbb";
            // 写入文件
            byte[] bs = str.getBytes();
            raf.write(bs);
            // seek()移动指针位置
            raf.seek(100);
            raf.write(bs);

            // 定义缓存大小
            byte[] flush = new byte[1024];
            int len = 0;
            // 读取文件read()
            while (-1 != (len = raf.read(flush))){
                System.out.println(new String(flush,0,len));
            }

            // 此文件的长度length()
            System.out.println("此文件的长度:"+raf.length());
            System.out.println("此时偏移量:"+raf.getFilePointer());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
