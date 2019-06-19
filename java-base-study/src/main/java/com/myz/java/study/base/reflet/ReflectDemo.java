package com.myz.java.study.base.reflet;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Book {
    private String title;
    private double price;

    public Book() {
        super();
        System.out.println("Book");
    }

    private Book(String title) {
        this.title = title;
        System.out.println(this.title);
    }

    public Book(String title, double price) {
        super();
        this.title = title;
        this.price = price;
        System.out.println(this.title + ":" + this.price);
    }


    private void desc(int a, int b) {
        System.out.println("描述" + a + b);
    }

    @Override
    public String toString() {
        // TODO Auto-generated methodfactory stub
        return this.title + this.price;
    }
}

public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        //获取方式1：类名.class
        Class<Book> class1 = Book.class;
        System.out.println(class1);

        //获取方式2：static Class<?> forName(String className)
        Class<?> class2 = Class.forName("com.myz.java.study.base.reflet.Book");
        System.out.println(class2);

        //获取方式3:对象.getClass()
        Object object = new Book();
        Class<?> class3 = object.getClass();
        System.out.println(class3);

        System.out.println(Integer.TYPE != Integer.class);

        System.out.println("========================");

        System.out.println("获取所有的公共构造方法:");
        //获取所有的公共构造方法
        Constructor<?>[] constructors = class1.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println(class1.getConstructor());


        System.out.println("获取所有的构造方法:");
        // 获取所有的公共构造方法
        Constructor<?>[] constructors_all = class1.getDeclaredConstructors();
        for (Constructor<?> constructor_all : constructors_all) {
            System.out.println(constructor_all);
        }
        System.out.println(class1.getDeclaredConstructor(String.class));


        System.out.println("========================");


        // Class中创建对象
        class1.newInstance();


        // Contructor中创建对象
        class1.getConstructor().newInstance();
        class1.getConstructor(String.class, double.class).newInstance("12", 12.3);


        // setAccessible(true) 将私有构造
        Constructor<?> constructor = class1.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        constructor.newInstance("aa");   // 私有构造不能直接调用


        // 返回所有公用（public）方法,包括其继承类的公用方法，当然也包括它所实现接口的方法
        Method[] methods = class1.getMethods();
        for (Method method : methods) {
            System.out.println("所有公共方法：" + method);
        }

        System.out.println("===================");

        // 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法
        Method[] methods_all = class1.getDeclaredMethods();
        for (Method method : methods_all) {
            System.out.println("所有方法但不包括继承方法：" + method);
        }


        // getDeclaredMethod("方法名称",Class clz)　获得指定方法
        Method method = class1.getDeclaredMethod("desc", new Class[]{int.class, Integer.TYPE});  //int.Class和Integer.class不是一回事情
        System.out.println(method);
        //　设置private为public
        method.setAccessible(true);
        // invoke(当前对象,参数) 执行该方法
        method.invoke(class1.newInstance(), 1, 2);


        // 获取指定封装属性
        Field field_title = class1.getDeclaredField("title");
        Field field_price = class1.getDeclaredField("price");
        //　设置访问权限
        field_title.setAccessible(true);
        field_price.setAccessible(true);
        //　获取对象
        Object obj = class1.newInstance();
        //设置属性值
        field_title.set(obj, "你好");
        field_price.setDouble(obj, 123.5);

        // 获取属性值
        String string = (String) field_title.get(obj);
        double d = field_price.getDouble(obj);
        System.out.println(string);
        System.out.println(d);
        System.out.println(obj);

    }

    @Test
    public void testClassName() throws Exception {
        Class<?> forName = Class.forName("java.lang.Class");
        String simpleName = forName.getSimpleName();
        System.out.println("simpleName = " + simpleName);
        String name = forName.getName();
        System.out.println("name = " + name);
        String canonicalName = forName.getCanonicalName();
        System.out.println("canonicalName = " + canonicalName);

        System.out.println("\r\n");


        System.out.println("Arrays类型");
        String[] str = new String[]{};
        // 返回的是虚拟机里面的class的表示     [Ljava.lang.String;
        // 首先 ‘[’ 表示数组，一个代表一维数组，比如 '[[' 代表二维数组。之后 'L' 代表类描述符，最后分号表示结束。
        // 这种编码叫做JNI字段描述符（JavaNative Interface FieldDescriptors)。有兴趣的话可以自己学习下。
        System.out.println("getName            " + str.getClass().getName());
        // java.lang.String[]
        System.out.println("getCanonicalName   " + str.getClass().getCanonicalName());
        // String[]
        System.out.println("getSimpleName      " + str.getClass().getSimpleName());
    }

}
