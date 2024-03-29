package com.myz.java.study.base.collection.list;

import com.myz.java.study.base.collection.domain.Book;
import com.myz.java.study.utils.UserBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * ListTest
 * 1  Collection
 *
 * @author maoyz
 * 2017年6月10日 下午1:09:03
 */
public class ListIterTest {

    @Test
    public void testPut1() {
        UserBean user = new UserBean();
        user.setAge(1);
        user.setAddr("1");
        List<UserBean> list = new ArrayList<>();
        list.add(user);
        // [UserBean(age=1, height=0, sex=null, salary=null, birth=null, addr=1)]
        System.out.println(list);
        System.out.println("=====================================");
        for (UserBean user1 : list) {
            user1.setAge(2);
        }
        // [UserBean(age=2, height=0, sex=null, salary=null, birth=null, addr=1)]
        System.out.println(list);

    }

    @Test
    public void testPut() {
        List<String> excludeIds = new ArrayList<>(Arrays.asList("0", "11", "2"));
        excludeIds.add(0, "a");
        // [a, 0, 11, 2]
        System.out.println(excludeIds);
    }

    @Test
    public void testCollection1() {
        List<String> excludeIds = new ArrayList<>(Arrays.asList("0", "11", "2"));
        List<String> orIncludeIds = new ArrayList<>(Arrays.asList("00", "11", "22"));
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(excludeIds)
                && org.apache.commons.collections.CollectionUtils.isNotEmpty(orIncludeIds)) {
            excludeIds.removeIf(orIncludeIds::contains);
        }
        System.out.println(excludeIds);
        System.out.println(orIncludeIds);
    }

    /**
     * testCollection
     * 是一个接口
     * 方法:isEmpty()  size() clear()
     * add(e)
     * 使用foreach或迭代输出
     *
     * @return void
     */
    @Test
    public void testCollection() {
        /*
         * ArrayList　底层实现数组，线程不安全，效率高，查找快，插入　修改慢
         * LinkedList　底层实现链表，插入　修改快，查询慢
         * Vector　线程安全，效率低
         */

        List<Book> c = new ArrayList<Book>();
        // isEmpty()  size()
        System.out.println(c.isEmpty() + " " + c.size());
        c.add(new Book("JAVA", 40.5));
        c.add(new Book("JSP", 30.6));
        c.add(new Book("Struct2", 20.4));
        c.add(new Book("JAVA", 40.5));

        //Book类重写equals()
        System.out.println("是否包含:" + c.contains(new Book("JAVA", 40.5)));

        System.out.println("使用foreach增强循环 ********* \r\n");
        // 使用foreach增强循环
        for (Book book : c) {
            // java.util.ConcurrentModificationException
            // c.remove(book);
            System.out.println(book);
        }

        System.out.println("使用iterator迭代器 ********* \r\n");
        // 使用迭代器进行List的增、删
        Iterator<Book> iterator = c.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("remove() 前: " + book.toString() + " ==> " + c.size());
            if (40 < book.getPrice()) {
                iterator.remove();
            }
            System.out.println("remove() 后: " + c.size());
        }

        c.clear();    //清空
        System.out.println("清空后:" + c.isEmpty() + " " + c.size());
        System.out.println(c == null);
    }

    /**
     * List
     * add(e)  add(index, e)
     * set(index, e)
     * get(index)
     */
    @Test
    public void testList() {
        List<String> list = new ArrayList<String>();
        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");
        list.add("3");
        System.out.println(list.get(0));
        System.out.println("使用iterator输出");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
        }

        list.set(0, "demo");
        list.set(1, "demo");
        list.set(2, "b");
        System.out.println("重新设置值:" + list.get(0));
        // 使用foreach增强循环
        System.out.println("使用foreach增强循环输出");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("-----------------");

        Iterator<String> iterator1 = list.iterator();
        /*使用迭代之后.引用指向末尾,第二次迭代无法完成
         * 若想再次使用迭代,重新创建iterator
         * */
        while (iterator1.hasNext()) {
            String str2 = iterator1.next();
            System.out.println(str2);
        }

        // 截取一段list
        List<String> subl = list.subList(1, 2);
        //list转array
        String[] arrList = subl.toArray(new String[]{});
        //以字符串形式打印出来
        System.out.println(Arrays.toString(arrList));
    }


    /**
     * Collections.sort(list)
     * 原list发生改变
     */
    @Test
    public void testCollections() {
        List<Integer> list = new ArrayList<>();
        //随机数
        Random r = new Random(5);
        for (int i = 0; i < 10; i++) {
            list.add(r.nextInt(100));
        }
        System.out.println("原位置: " + list);
        // 执行排序
        Collections.sort(list);
        System.out.println("执行排序之后位置发生改变: " + list);
    }


    private static void test1() {
        Collection<String> c1 = new ArrayList<>();
        c1.add("terry");
        c1.add("Java");
        System.out.println(c1);
        Collection<String> c2 = new HashSet<>();
        c2.addAll(c1);
        System.out.println(c2);
        Collection<String> c3 = new ArrayList<>();
        c3.add("terry");
        System.out.println(c1.containsAll(c3));
    }


    public static void main(String[] args) {
        test1();
    }

    /**
     * add()和addAll()的区别
     * add()将元素作为一个整体; addAll()将元素中遍历出来之后在添加
     */
    @Test
    public void testAddAnAddAll() {
        List list1 = new ArrayList<>(Arrays.asList("0", "1", "2"));
        List list2 = new ArrayList<>(Arrays.asList("00", "11", "22"));
        List list3 = new ArrayList<>(Arrays.asList("00", "11", "22"));

        list3.add(list1);
        // [00, 11, 22, [0, 1, 2]]
        System.out.println(list3);
        // 4
        System.out.println(list3.size());

        list2.addAll(list1);
        // [00, 11, 22, 0, 1, 2]
        System.out.println(list2);
        // 6
        System.out.println(list2.size());
    }

    /**
     * add() 实则数组扩容
     * addAll() 实则数组拷贝
     */
    @Test
    public void testListChange() {
        List<String> list1 = new ArrayList<>(Arrays.asList("0", "1", "2"));
        List<String> list2 = new ArrayList<>(Arrays.asList("00", "11", "22"));
        List<Object> list3 = new ArrayList<>(Arrays.asList("00", "11", "22"));

        list3.add(list1);
        // [00, 11, 22, [0, 1, 2]]
        System.out.println(list3);
        list2.addAll(list1);
        // [00, 11, 22, 0, 1, 2]
        System.out.println(list2);

        list1.set(0, "aaaaa");
        System.out.println("******************* list1发生变化 *********************");

        // [00, 11, 22, [aaaaa, 1, 2]]
        System.out.println(list3);
        // [00, 11, 22, 0, 1, 2]
        System.out.println(list2);

    }

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

}
