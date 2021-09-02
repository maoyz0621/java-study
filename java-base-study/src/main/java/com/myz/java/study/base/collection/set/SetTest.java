package com.myz.java.study.base.collection.set;

import com.myz.java.study.base.collection.domain.Book;
import com.myz.java.study.base.collection.domain.User;
import org.junit.Test;

import java.util.*;

/**
 * 1 HashSet实现原理 通过HashMap实现
 * add(e) 将e作为HashMap的key, value = new Object(), 返回boolean (==null);可以有一个Null元素，存入的元素是无序的
 * <p>
 * <p>
 * 2 LinkedHashSet如何保证有序和唯一性, 通过LinkedHashMap
 * <p>
 * 1).底层数据结构由哈希表和链表组成。
 * 2).链表保证了元素的有序即存储和取出一致，哈希表保证了元素的唯一性。
 * <p>
 * <p>
 * 3 TreeSet 通过TreeMap 不可以有Null元素，根据元素的自然顺序进行排序
 * TreeSet如何保证元素的排序和唯一性
 * <p>
 * 底层的数据结构是红黑树(一种自平衡二叉查找树)
 *
 * @author maoyz on 18-2-19.
 */
public class SetTest {

    @Test
    public void testHashSetNull() {
        Set<String> set = new HashSet<>();
        set.addAll(null);
        set.add(null);
        System.out.println(set);
        System.out.println(set.contains(null));
        set.remove(null);
        System.out.println(set);
    }

    @Test
    public void testHashSetRemove() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        Set<String> set1 = new HashSet<>();
        set1.add("a");
        set1.add("c");
        set.removeAll(set1);
        // b
        System.out.println(set);
    }

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
        // [null, a]
        System.out.println(set);
        //2
        System.out.println(set.size());
    }

    /**
     * 没有重写 hashCode()和equals()
     */
    @Test
    public void testHashSetBeanNo() {
        Set<User> set = new HashSet<>();
        set.add(new User("JAVA", 30));
        set.add(new User("JSP", 40));
        set.add(new User("Struct", 31));
        set.add(new User("JAVA", 30));
        // [User{name='JSP', age=40}, User{name='JAVA', age=30}, User{name='Struct', age=31}, User{name='JAVA', age=30}]
        System.out.println(set);
    }

    /**
     * 重写 hashCode()和equals()
     */
    @Test
    public void testHashSetBean() {
        Set<Book> set = new HashSet<>();
        set.add(new Book("JAVA", 30.5));
        set.add(new Book("JSP", 40.6));
        set.add(new Book("Struct", 30.5));
        set.add(new Book("JAVA", 30.5));
        // [书名:JAVA;价格:30.5, 书名:Struct;价格:30.5, 书名:JSP;价格:40.6]
        System.out.println(set);
    }

    /**
     * LinkedHashSet如何保证有序和唯一性
     * <p>
     * 1).底层数据结构由哈希表和链表组成。
     * 2).链表保证了元素的有序即存储和取出一致，哈希表保证了元素的唯一性。
     */
    @Test
    public void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("a");
        set.add("a1");
        set.add("b");
        // [a, a1, b]
        System.out.println(set);
    }

    /**
     * TreeSet 通过TreeMap
     * 不可以有Null元素，根据元素的自然顺序进行排序
     */
    @Test
    public void testSet() {
        Set<Book> set = new TreeSet<Book>();
        set.add(new Book("JAVA", 30.5));
        set.add(new Book("JSP", 40.6));
        set.add(new Book("Struct", 30.5));
        set.add(new Book("JAVA", 30.5));
        System.out.println("使用iterator输出\n");
        Iterator<Book> iterator = set.iterator();
        while (iterator.hasNext()) {
            Book str = iterator.next();
            System.out.println(str.toString());
        }
        // java.lang.NullPointerException
        set.add(null);
    }
}
