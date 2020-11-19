package com.myz.java.study.jvm;

import java.io.*;

/**
 * 自定义classloader
 *  getParent()     返回委托的父类加载器。
    loadClass(String name, boolean resolve)     使用指定的二进制名称来加载类。
    findClass(String name)      使用指定的二进制名称查找类。
    findLoadedClass(String name)    如果Java虚拟机已将此加载器记录为具有给定二进制名称的某个类的启动加载器，则返回该二进制名称的类。
    defineClass(String name, byte[] b, int off, int len)    将一个 byte 数组转换为 Class 类的实例。
    resolveClass(Class<?> c)    链接指定的类。
 * 要求：
 *      根据提供的类路径加载相关类
 * @author maoyz on 18-2-25.
 */
public class MyFileSystemClassLoader extends ClassLoader {

    /**
     * 文件路径
     */
    private String rootDir;

    public MyFileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     *　重写findClass()方法
     * @param name 包名
     * @return 类名
     * @throws ClassNotFoundException if Class not found
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //　查找本地已加载的类，判断是否存在
        Class<?> clazz = findLoadedClass(name);
        // 如果该类已经加载，直接返回
        if (clazz != null) {
            return clazz;
        } else {
            // 获取父类加载器
            ClassLoader parent = this.getParent();
            // 委派给父类加载器
            try {
                clazz = parent.loadClass(name);
            } catch (Exception e) {
            }
            // 返回父类加载器
            if (clazz != null) {
                return clazz;
            } else {
                // 获取字节流数组
                byte[] classData = getClassData(name);
                if (classData == null) {
                    throw new ClassNotFoundException();
                } else {
                    // defineClass()可以把二进制流字节组成的文件转换为一个java.lang.Class
                    clazz = defineClass(name,classData,0,classData.length);
                    return clazz;
                }
            }
        }

    }

    /**
     * 获取二进制流字节
     * @param className
     */
    private byte[] getClassData(String className) {
        String path = rootDir + "/" + className.replace(".","/") + ".class";
        InputStream in = null;
        // 字节数组流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {

            in = new FileInputStream(new File(path));

            byte[] data = new byte[1024];
            int len  =0;

            while ((len = in.read(data)) != -1){
                byteArrayOutputStream.write(data, 0 , len);
            }
            // 转换为byte类型
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) throws ClassNotFoundException {

        MyFileSystemClassLoader classLoader  = new MyFileSystemClassLoader("/home/maoyz/文档");
        MyFileSystemClassLoader classLoader1 = new MyFileSystemClassLoader("/home/maoyz/文档");

        // 同一个类被同一个类加载器加载，jvm认为同一个，不同类加载器加载，不相同
        Class<?> clazz  = classLoader.findClass("com.Hello");
        Class<?> clazz1 = classLoader.findClass("com.Hello");
        Class<?> clazz2 = classLoader1.findClass("com.Hello");
        Class<?> clazz3 = classLoader.findClass("java.lang.String");
        Class<?> clazz4 = classLoader.findClass("com.Hello");
        Class<?> clazz5 = classLoader.findClass("Demo1");

        // 同一加载器加载
        System.out.println(clazz.hashCode());
        System.out.println(clazz1.hashCode());
        // 不同加载器加载
        System.out.println(clazz2.hashCode());
        //启动类加载器　null
        System.out.println(clazz3.getClassLoader());
        //自定义类加载器　com.myz.java.study.jvm.MyFileSystemClassLoader
        System.out.println(clazz4.getClassLoader());
        // 应用程序加载器　sun.misc.Launcher$AppClassLoader
        System.out.println(clazz5.getClassLoader());

    }
}
