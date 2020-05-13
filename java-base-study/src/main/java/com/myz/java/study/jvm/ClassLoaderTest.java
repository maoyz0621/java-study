package com.myz.java.study.jvm;

import org.junit.Test;

/**
 * jvm加载顺序：
 * 加载->　验证　-> 准备-> 解析　-> 初始化
 * 类加载器ClassLoader类型：
 * 启动加载器　>　扩展类加载器　>　应用程序类加载器　>　自定义类加载器
 * getSystemClassLoader()
 * getParent()
 *
 * @author maoyz on 18-2-25.
 */
public class ClassLoaderTest {

    /**
     * 类加载器
     */
    @Test
    public void test1() {
        // sun.misc.Launcher$AppClassLoader
        System.out.println("应用程序类加载器:" + ClassLoader.getSystemClassLoader());
        // sun.misc.Launcher$ExtClassLoader
        System.out.println("扩展类加载器:" + ClassLoader.getSystemClassLoader().getParent());
        // null ，实则是BootstrapClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }

    @Test
    public void test2() {

        System.out.println(System.getProperty("java.class.path"));

    }
}
