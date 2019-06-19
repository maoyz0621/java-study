package com.myz.java.study.base.collection.map;

import com.myz.java.study.base.collection.Person;
import com.myz.java.study.base.collection.User;
import org.junit.Test;

import java.util.*;

/**
 * TreeMap
 * 有序的key-value集合，它是通过红黑树实现的。
 *
 * @author maoyz on 18-3-1.
 */
public class TreeMapTest {

    /**
     * String类型实现了Comparable<>
     */
    @Test
    public void test() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("dffd", "1");
        map.put("asdsf", "2");
        map.put("rthh", "3");
        map.put("ghgg", "3");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            /*asdsf=2
              dffd=1
              ghgg=3
              rthh=4*/
            System.out.println(iterator.next());
        }
    }

    /**
     * Person类实现了Comparable
     * 自然顺序(Comparable)
     */
    @Test
    public void testBeanWithComparable() {
        Map<Person, String> map = new TreeMap<Person, String>();

        map.put(new Person("zhangsan", 12), "aa");
        map.put(new Person("lisi", 14), "bb");
        map.put(new Person("wanger", 14), "cc");
        map.put(new Person("zhaoqian", 10), "dd");

        Set<Map.Entry<Person, String>> entries = map.entrySet();
        Iterator<Map.Entry<Person, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            /*
            Person{name='wanger', age=14}=cc
            Person{name='lisi', age=14}=bb
            Person{name='zhangsan', age=12}=aa
            Person{name='zhaoqian', age=10}=dd
            * */
            System.out.println(iterator.next());
        }

        System.out.println(map.get(new Person("zhaoqian", 10)));
    }

    /**
     * TreeSet  java.util.Comparator
     * 比较器顺序(Comparator)
     */
    @Test
    public void testBeanWithComparator() {
        User u1 = new User("zhangsan", 12);
        User u2 = new User("lisi", 14);
        User u3 = new User("wanger", 14);
        User u4 = new User("zhaoqian", 10);

        Map<User, String> map = new TreeMap<User, String>(new Comparator<User>() {

            @Override
            public int compare(User p1, User p2) {
                if (p1.getAge() < p2.getAge()) {
                    return -1;
                } else if (p1.getAge() > p2.getAge()) {
                    return 1;
                } else {
                    return -p1.getName().compareTo(p2.getName());
                }
            }
        });

        map.put(u1, "a1");
        map.put(u2, "a2");
        map.put(u3, "a3");
        map.put(u4, "a4");

        Set<Map.Entry<User, String>> entries = map.entrySet();
        Iterator<Map.Entry<User, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            /*
            User{name='zhaoqian', age=10}=a4
            User{name='zhangsan', age=12}=a1
            User{name='wanger', age=14}=a3
            User{name='lisi', age=14}=a2
            * */
            System.out.println(iterator.next());
        }

    }
}
