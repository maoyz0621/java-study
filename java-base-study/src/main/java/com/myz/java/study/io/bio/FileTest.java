package com.myz.java.study.io.bio;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * 路径分隔符 File.pathSeparator
 * 文件分隔符 File.separator
 *@author maoyz
 */
public class FileTest {
    /**
     * 文件路径表示方法
     */
	// String pathname = "myz" + File.separator + "files/demo/1.txt";


    /**
     * 相对路径
     *      new File(String parent, String child)
     * 绝对路径
     *      new File(String pathname)
     * 获取路径名称：getPath()
     * 获取文件名称：getName()
     * 获取绝对路径名称：file.getAbsolutePath()
     * 获取父目录：getParent()，若是相对路径，返回null
     */
    @Test
    public void testPath() {
        String parentPath = "files" + File.separator + "demo";
        String path = "1.txt";
        URL resource = this.getClass().getClassLoader().getResource(parentPath + File.separator + path);
        String path0 = null;
        if (resource != null){
            path0 = resource.getPath();
        }
        System.out.println(path0);
        File file = new File(parentPath, path);
        // 路径名称
        System.out.println(file.getPath());
        // 文件名称
        System.out.println("文件名称: " + file.getName());
        System.out.println("绝对路径：" + file.getAbsolutePath());
        System.out.println(file.getAbsoluteFile());
        System.out.println("父目录：" + file.getParent());
    }

    /**
     * 文件是否存在 exists()
     * 文件是否可写 canWrite()
     * 设置文件读写权限 setWritable()/setReadable()
     * 是否是文件　isFile()
     * 是否是文件夹　isDirectory()
     */
    @Test
    public void test() {
        String path = "files" + File.separator + "demo" + File.separator + "1.txt";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        String path0 = null;
        if (resource != null) {
            path0 = resource.getPath();
        }

        System.out.println(path0);
        File file = new File(path0);
        // 判断文件是否存在
        System.out.println(file.exists());
        // 判断文件的可读性
        System.out.println(file.canWrite());
        // 设置可读权限
        file.setWritable(false);
        System.out.println(file.canWrite());
        System.out.println("是否是文件：" + file.isFile());
        System.out.println("是否是文件夹：" + file.isDirectory());
    }

    /**
     * 创建文件 createNewFile()
     * 删除文件 delete()
     */
    @Test
    public void testCreateAndDel() throws IOException {
        String path = "files" + File.separator + "demo" + File.separator + "2.txt";
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        String path0 = null;
        if (resource != null) {
            path0 = resource.getPath();
        }


        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("文件不存在，创建");
        } else {
            file.delete();
            System.out.println("文件存在，删除");
        }
    }

    /**
     * 创建临时文件 File.createTempFile(String prefix, String suffix,File directory)
     * 退出删除 deleteOnExit()
     */
    @Test
    public void testTemp() throws IOException, InterruptedException {
        String path = "/home/maoyz/文档/JAVAWeb/first/resources/files/demo";
        File temp =  File.createTempFile("myz",".tmp",new File(path));
        Thread.sleep(5000);
        temp.deleteOnExit();
    }

    /**
	* new File("..")表示上一级目录,new File(".")表示当前根目录
    * FileFilter
	* listFiles()
	 */
    @Test
    public void testFile() {
        File f = new File("..");
        System.out.println(f.getPath());

        // listFiles()
        File[] fs = f.listFiles(pathname -> pathname.getName().startsWith(".")
        );
        for (File file : fs) {
            System.out.println(file);
        }
    }
}
