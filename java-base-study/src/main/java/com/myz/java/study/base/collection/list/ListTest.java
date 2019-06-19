package com.myz.java.study.base.collection.list;

import com.myz.java.study.base.collection.Book;
import org.junit.Test;

import java.util.*;

/**
 * ListTest
 * 1  Collection
 *
 * @author maoyz
 * 2017年6月10日 下午1:09:03
 */
public class ListTest {
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
        System.out.println(list);
        Collections.sort(list);        //排序
        System.out.println(list);
    }


    private static void test1() {
        Collection<String> c1 = new ArrayList<>();
        c1.add("terry");
        c1.add("Jva");
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
     */
    @Test
    public void testAddAnAddAll() {
        List list1 = new ArrayList<>(Arrays.asList("0","1","2"));
        List list2 = new ArrayList<>(Arrays.asList("00","11","22"));
        List list3 = new ArrayList<>(Arrays.asList("00","11","22"));

        list3.add(list1);
        // [00, 11, 22, [0, 1, 2]]
        System.out.println(list3);

        list2.addAll(list1);
        // [00, 11, 22, 0, 1, 2]
        System.out.println(list2);
    }
}
