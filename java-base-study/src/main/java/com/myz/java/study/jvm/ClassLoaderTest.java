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

    /**
     * Class.forName
     * 实际上也是调用的ClassLoader来实现的, 将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。得到的class已经初始化完成
     * 为什么说Class.forName也是调用的CLassLoader来实现的？原因：return forName0(className, true, ClassLoader.getClassLoader(caller), caller);
     * ClassLoader.loadClass
     * 遵循双亲委派模型最终调用启动类加载器的类加载器, 并没有对类进行初始化，只是把类加载到了虚拟机中, 得到的class还没有link, 只有在newInstance才会去执行static块
     * <p>
     * 如果程序依赖于Class是否被初始化，就必须用Class.forName(name)
     */
    public static void main(String[] args) throws ClassNotFoundException {
        classLoader();
        System.out.println("===========================");

        classLoader();
        System.out.println("===========================");

        formName();
        System.out.println("===========================");

        formName();
    }

    private static void formName() throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.myz.java.study.jvm.LoaderClass");
        System.out.println(forName.getName());
    }

    private static void classLoader() throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> loadClass = loader.loadClass("com.myz.java.study.jvm.LoaderClass");
        System.out.println(loadClass.getName());
    }
}

class LoaderClass {

    private String name;

    private static int a = 10;

    {
        System.out.println("普通代码块..");
    }

    static {
        System.out.println("静态变量" + a);
        System.out.println("静态代码块..");
    }

    public LoaderClass() {
        System.out.println("构造方法");
    }

    public LoaderClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}