package com.myz.java.study.base.collection.set;

import com.myz.java.study.base.collection.Book;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 通过HashMap实现
 *
 * @author maoyz on 18-2-19.
 */
public class SetTest {

    /**
     *
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
