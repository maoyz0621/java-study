package com.myz.java.study.base.clone.base;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maoyz
 */
public class BaseCloneTest {

    @Test
    public void test20() {
        Map<String, Object> map = new ConcurrentHashMap<>(12);
        // = 指向同一个堆内存地址
        Map<String, Object> map1 = map;
        add(map);
        System.out.println(map);
        add1(map);
        System.out.println("map原始值: " + map);
        System.out.println("map发生变化: " + map1);

        map1.put("c", 3);
        System.out.println("map1发生变化, map: " + map);
        System.out.println("map1发生变化, map1: " + map1);


    }

    private void add(Map map) {
        map.put("a", 1);
    }

    private Integer add1(Map map) {
        map.put("b", 2);
        return null;
    }

    @Test
    public void test() {
        StudentClone student1 = new StudentClone();
        student1.setNumber(1);
        student1.setAge(10);
        student1.setAddr("anhui");
        // 堆地址引用
        StudentClone student2 = student1;
        // true
        System.out.println(student1 == student2);
        System.out.println(student1);
        System.out.println(student2);

        // student1 变化
        student1.setNumber(11);
        student1.setAge(101);
        student1.setAddr("anhui1");
        // StudentClone{number=11, age=101, addr='anhui1'}
        System.out.println("student1发生变化: " + student1);
        // StudentClone{number=11, age=101, addr='anhui1'}
        System.out.println("student1发生变化: " + student2);

        // student2变化
        student2.setNumber(12);
        student2.setAge(102);
        student2.setAddr("anhui2");
        // StudentClone{number=12, age=102, addr='anhui2'}
        System.out.println("student2发生变化: " + student1);
        // StudentClone{number=12, age=102, addr='anhui2'}
        System.out.println("student2发生变化: " + student2);
    }

    /**
     * 对象 Cloneable
     */
    @Test
    public void testShallowClone() {
        StudentClone studentClone1 = new StudentClone();
        studentClone1.setNumber(1);
        studentClone1.setAge(10);
        studentClone1.setAddr("anhui");
        StudentClone studentClone2 = (StudentClone) studentClone1.clone();
        // false
        System.out.println(studentClone1 == studentClone2);
        System.out.println(studentClone1);
        System.out.println(studentClone2);

        // studentClone1 变化
        studentClone1.setNumber(11);
        studentClone1.setAge(101);
        studentClone1.setAddr("anhui1");
        // StudentClone{number=11, age=101, addr='anhui1'}
        System.out.println("student1发生变化: " + studentClone1);
        // StudentClone{number=1, age=10, addr='anhui'}
        System.out.println("student1发生变化: " + studentClone2);

        // student变化
        studentClone2.setNumber(12);
        studentClone2.setAge(102);
        studentClone2.setAddr("anhui2");
        // StudentClone{number=11, age=101, addr='anhui1'}
        System.out.println("student2发生变化: " + studentClone1);
        // StudentClone{number=12, age=102, addr='anhui2'}
        System.out.println("student2发生变化: " + studentClone2);


    }

}
