package com.myz.java.study.base.collection.set;

import com.myz.java.study.base.collection.domain.Book;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * HashSet实现原理 通过HashMap实现
 * add(e) 将e作为HashMap的key, value = new Object(), 返回boolean (==null);可以有一个Null元素，存入的元素是无序的
 *
 * @author maoyz on 18-2-19.
 */
public class SetTest {

    /**
     * HashSet
     */
    @Test
    public void testHashSet() {
        Set<String> set = new HashSet<>();
        // 返回boolean
        boolean a = set.add("a");
        System.out.println(a);
        // false
        boolean b = set.remove("b");
        System.out.println(b);
        set.add(null);
        // 2
        System.out.println(set.size());
        set.add(null);
        //2
        System.out.println(set.size());
    }

    /**
     * TreeSet
     */
    @Test
    public void testSet() {
        Set<Book> set = new TreeSet<Book>();
        set.add(new Book("JAVA", 30.5));
        set.add(new Book("JSP", 40.6));
        set.add(new Book("Struct", 30.5));
        set.add(new Book("JAVA", 30.5));
        System.out.println("使用iterator输出");
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            Book str = iterator.next();
            System.out.println(str.toString());
        }
    }
}
