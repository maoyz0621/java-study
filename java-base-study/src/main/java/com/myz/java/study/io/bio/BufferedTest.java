package com.myz.java.study.io.bio;

import org.junit.Test;

import java.io.*;

/**
 * 缓存流
 *   BufferedInputStream
 *   BufferedOutputStream
 *   BufferedReader  readLine()
 *   BufferedWriter  newLine()
 *
 * @author maoyz on 18-3-16.
 */
public class BufferedTest {

    String pathname = "/files/demo/2.txt".replace("/", File.separator);
    String pathname0 = "/src/main/resources/files/demo/3.txt".replace("/", File.separator);

    /* 如果以 / 开头，则从根路径开始搜索资源 */
    private String basePath = getClass().getResource("/").getPath();

    /* 如果不以 / 开头，则从当前类所在的路径开始搜索资源 */
    private String basePath0 = getClass().getResource("").getPath();

    /* 不能以 / 开头，统一从根路径开始搜索资源 */
    private String basePath2 = Thread.currentThread().getContextClassLoader().getResource("").getPath();


    /**
     * BufferedInputStream
     * BufferedOutputStream
     */
    @Test
    public void testStream() {
        File file = new File(basePath + pathname);
        //　如果是目录
        if (file.isDirectory()) {
            return;
        }
        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(new File(basePath).getParentFile().getParent() + pathname0, true);
                //　包装
                BufferedInputStream r = new BufferedInputStream(in);
                BufferedOutputStream w = new BufferedOutputStream(out)
        ) {
            int len = 0;
            byte[] buf = new byte[1024];
            // 将文件1内容读取出
            while (-1 != (len = r.read(buf))) {
                //将读取内容写入文件2
                w.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * BufferedInputStream
     * BufferedOutputStream
     */
    @Test
    public void testReader() {
        File file = new File(basePath + pathname);
        //　如果是目录
        if (file.isDirectory()) {
            return;
        }
        try (
                Reader in = new FileReader(file);
                Writer out = new FileWriter((new File(".").getAbsolutePath().replace(".", "") + "src/main/resources/files/demo/4.txt").replace("/", File.separator));
                //　包装
                BufferedReader r = new BufferedReader(in);
                BufferedWriter w = new BufferedWriter(out)
        ) {
            int len = 0;
            String info = null;
            // r.readLine()读取一行
            while (null != (info = r.readLine())) {
                w.write(info);
                // 写入一个行分隔符
                w.newLine();
                w.append("激发剂");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
