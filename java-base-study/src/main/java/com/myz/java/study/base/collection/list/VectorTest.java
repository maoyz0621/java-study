package com.myz.java.study.base.collection.list;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/**
 * Vector线程安全
 * 可以使用Enumeration迭代数据
 *
 * @author maoyz on 18-3-1.
 */
public class VectorTest {

    @Test
    public void test() {
        Vector<String> vector = new Vector<String>();
        vector.add("1");
        vector.add("2");
        vector.add("3");

        Enumeration<String> elements = vector.elements();

        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
        System.out.println("------------");

        Iterator<String> iterator = vector.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
