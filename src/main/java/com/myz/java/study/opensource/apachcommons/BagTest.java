package com.myz.java.study.opensource.apachcommons;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

/**
 * Bag 包允许重复
 * uniqueSet()  key
 * getCount(Object object)  次数
 * 1.HashBag 无序
 * 2.TreeBag 有序
 * 案例：统计单词的出现次数
 *
 * @author maoyz on 18-3-5.
 */
public class BagTest {

    /**
     * HashBag 无序
     */
    @Test
    public void testHash() {
        Bag<String> bag = new HashBag<>();
        bag.add("b");
        // boolean add(E object, int nCopies)添加几次
        bag.add("demo", 5);
        // boolean remove(Object object, int nCopies)移除几个
        bag.remove("demo", 2);
        bag.add("z");
        bag.add("g");

        Iterator<String> iterator = bag.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "-->");
        }
    }

    /**
     * TreeBag 有序
     */
    @Test
    public void testTree() {
        Bag<String> bag = new TreeBag<>();
        bag.add("b");
        // boolean add(E object, int nCopies)添加几次
        bag.add("demo", 5);
        // boolean remove(Object object, int nCopies)移除几个
        bag.remove("demo", 2);
        bag.add("d");
        bag.add("c");

        Iterator<String> iterator = bag.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "-->");
        }
    }

    /**
     * 案例
     * uniqueSet()  key
     * getCount(Object object)  次数
     */
    @Test
    public void wordCount() {
        String[] arrs = "this is demo cat and that is demo micewhere is the food".split(" ");
        Bag<String> bag = new TreeBag<>();
        ArrayListIterator<String> iterator = new ArrayListIterator<>(arrs);

        while (iterator.hasNext()) {
            bag.add(iterator.next());
        }

        // 获取key
        Set<String> set = bag.uniqueSet();
        for (String s : set) {
            System.out.println(s + "->" + bag.getCount(s) + "次");
        }

    }
}
