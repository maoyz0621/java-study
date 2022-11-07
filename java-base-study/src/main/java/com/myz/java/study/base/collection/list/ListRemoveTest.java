package com.myz.java.study.base.collection.list;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * ListTest
 * 1  Collection
 *
 * @author maoyz
 * 2017年6月10日 下午1:09:03
 */
public class ListRemoveTest {

    @Test
    public void testIterator() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
            Integer next = iterator.next();
            if (next == 2) {
                continue;
            }
            System.out.println(next);
            iterator.remove();
        }
        System.out.println("****************************");
        System.out.println(list);
    }

    @Test
    public void testRemove0() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sh-" + i);
        }
        System.out.println(list);
        // 循环中list的size是变化的
        for (int i = 0; i < list.size(); i++) {
            // 每次remove()时，list发生变化
            list.remove(i);
            // 0 => [sh-1, sh-2, sh-3, sh-4, sh-5, sh-6, sh-7, sh-8, sh-9] sh-1
            // 1 => [sh-1, sh-3, sh-4, sh-5, sh-6, sh-7, sh-8, sh-9] sh-3
            // 2 => [sh-1, sh-3, sh-5, sh-6, sh-7, sh-8, sh-9] sh-5
            // 3 => [sh-1, sh-3, sh-5, sh-7, sh-8, sh-9] sh-7
            // 4 => [sh-1, sh-3, sh-5, sh-7, sh-9] sh-9
            System.out.println(i + " => " + list + " " + list.get(i));
        }
    }

    @Test
    public void testRemove1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sh" + i);
        }

        int size = list.size();
        // java.util.ConcurrentModificationException
        for (String s : list) {
            list.remove(s);
        }
        // for (int i = 0; i < size; i++) {
        //     list.remove(i);
        //     // java.lang.IndexOutOfBoundsException
        //     System.out.println("秘密 => " + list.get(i));
        // }
    }

    @Test
    public void testRemove2() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sh" + i);
        }

        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            System.out.println(next);
            // java.util.ConcurrentModificationException
            list.remove(next);
        }
    }

    @Test
    public void testRemove3() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sh" + i);
        }

        for (String s : list) {
            System.out.println(s);
            // java.util.ConcurrentModificationException
            list.remove(s);
        }
    }

    @Test
    public void test() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("sh" + i);
        }

        // 这里会抛出角标越界异常java.lang.IndexOutOfBoundsException，为什么呢，因为 for 循环的条件 list.iterator().hasNext()，
        // 我们知道 list.iterator() 将会new 一个新的  iterator 对象，而在 new 的过程中我们将 每次 list.remove 后的 modCount 赋值给了新的 iterator 的 expectedModCount，所以不会抛出 ConcurrentModificationException 异常，
        // 而 hasNext 内部只判断了 size 是否等于 cursor != size 当我们删除了一半元素以后，size 变成了 5 而新的  list.iterator() 的 cursor 等于 0 ，0!=5 for 循环继续，那么当执行到 list.remove(5)的时候就会抛出角标越界了。
        for (int i = 0; list.iterator().hasNext(); i++) {
            list.remove(i);
            // java.lang.IndexOutOfBoundsException
            System.out.println("秘密 => " + list.get(i));
        }
    }

    /**
     * java.util.Arrays.ArrayList#ArrayList(java.lang.Object[])
     */
    @Test
    public void testRemoveIf() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // java.lang.UnsupportedOperationException: remove
        list.removeIf(param -> Objects.equals(2, param));
    }

    @Test
    public void testRemoveIf1() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4);
        list.removeIf(param -> Objects.equals(2, param));
    }

}