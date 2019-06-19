package com.myz.java.study.jvm;

import org.junit.Test;

/**
 * 代码块执行顺序：
 * 成员变量 -->
 * 父类静态构造块(1次) -->
 * 子类静态构造块(1次)　-->
 * 父类普通构造块(按顺序) -->
 * 父类构造方法 -->
 * 子类普通构造块(按顺序) -->
 * 　　　　子类构造方法 -->
 * 　　　　　　普通方法
 *
 * @author myz
 */
public class StaticTest {

    static {
        System.out.println("我是主类静态构造块,只执行一次");
    }

    public static void main(String[] args) {
        /*new Article();
        new Article();
        new Article();*/


        new Article1();
        System.out.println("--------");
        new Article1();
    }

    @Test
    public void test() {
        new BookA();
        new BookA();
        new BookA();
    }
}

class Article {

    private String title;

    public Article(String title) {
        this.title = title;
        System.out.println(this.title);
    }

    public Article() {
        System.out.println("我是构造方法");
    }

    {
        System.out.println("我是构造块B");
    }

    static {
        System.out.println("我是静态构造块，只执行一次");
    }

    {
        System.out.println("我是构造块A");
    }

    public void fun() {
        System.out.println("父类方法");
    }
}

class Article1 extends Article {
    private int age;

    public Article1() {
        System.out.println("我是构造方法 Article1");
    }

    public Article1(String title) {
        super(title);
    }

    public Article1(String title, int age) {
        super(title);
        this.age = age;
    }


    {
        System.out.println("我是Article1构造块B");
    }

    static {
        System.out.println("我是Article1静态构造块，只执行一次");
    }

    @Override
    public void fun() {
        super.fun();
        System.out.println("子类方法");
    }
}

class Book {

    public static String pub = "北京";
    private static int count;
    public static int size;

    public Book() {
        count++;
        size++;
        System.out.println("你是" + count + ":" + size + "号");
        System.out.println(this);
    }

    public void fun() {
    }

    public String getInfo() {
        return pub;
    }
}

class BookA extends Book {

    final String NAME = "Hello";
    private int age;

    public BookA() {
        System.out.println("BookA");
    }

    @Override
    public void fun() {
        super.fun();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName() {
    }

}




