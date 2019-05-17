/**
 * Copyright 2019 asiainfo Inc.
 **/
package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 压缩文件测试
 *
 * @author maoyz0621 on 2019/3/25
 * @version: v1.0
 */
public class CompressTest {

    private static String path = "F:\\IDEA\\first\\src\\main\\resources\\logback.xml";
    private static String zip = "F:\\IDEA\\first\\src\\main\\resources\\logback.zip";

    public static void main(String[] args) {
        compress();
    }

    private static void compress() {
        // 创建压缩对象
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry("CompressTest");
        // 要压缩的文件
        File file = new File(path);
        try (
                ZipArchiveOutputStream zipArchiveOutput = new ZipArchiveOutputStream(new File(zip));
                FileInputStream inputStream = new FileInputStream(file)
        ) {
            zipArchiveOutput.putArchiveEntry(zipArchiveEntry);

            int i = 0, j;
            while ((j = inputStream.read()) != -1) {
                zipArchiveOutput.write(j);
                i++;
            }
            System.out.println("压缩成功!遍历了:" + i + "次");
            // 需要关闭 java.io.IOException: This archive contains unclosed entries.
            zipArchiveOutput.closeArchiveEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
