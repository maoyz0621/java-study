/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Q: 既然说类型变量会在编译的时候擦除掉，那为什么我们往 ArrayList 创建的对象中添加整数会报错呢？不是说泛型变量String会在编译的时候变为Object类型吗？为什么不能存别的类型呢？既然类型擦除了，如何保证我们只能使用泛型变量限定的类型呢？
 * A: Java编译器是通过先检查代码中泛型的类型，然后在进行类型擦除，再进行编译。
 * <p>
 * Q: 所有的泛型类型变量最后都会被替换为原始类型。既然都被替换为原始类型，那么为什么我们在获取的时候，不需要进行强制类型转换呢？
 * A: 当存取一个泛型域时也会自动插入强制类型转换, 自动地在结果字节码中插入强制类型转换
 *
 * @author maoyz0621 on 20-3-13
 * @version v1.0
 */
public class GenericTest {

    /**
     * 类型擦除，只剩原始类型
     */
    @Test
    public void test() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);

        List<String> list2 = new ArrayList<>();
        list2.add("aa");

        //　将list1的Integer　和　list2的String　擦除了
        System.out.println(list1.getClass() == list2.getClass());
    }

    /**
     * 反射添加其他类型元素
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        List<Integer> list1 = new ArrayList<>();
        // 此时的list只能　存储Integer
        list1.add(1);

        list1.getClass().getMethod("add", Object.class).invoke(list1, "aaa");

        // for 循环取值
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
    }

    /**
     * 使用instanceof　去掉泛型
     */
    @Test
    public void test2() {
        ArrayList<String> list = new ArrayList<>();
        // if (list instanceof ArrayList<String>) {
        if (list instanceof ArrayList) {

        }

    }

}
