package com.myz.java.study.base.collection.set;

import com.myz.java.study.base.collection.Person;
import com.myz.java.study.base.collection.User;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * TreeSet
 * 对象元素进行排序,同样也可以保证元素的唯一。
 * 在添加数据时进行排序,数据更改不会影响原来的顺序
 * 不要修改数据，可能造成数据重复
 *
 * @author maoyz on 18-3-1.
 */
public class TreeSetTest {

    /**
     * String类型实现了Comparable<>
     */
    @Test
    public void test() {
        Set<String> set = new TreeSet<String>();
        set.add("dffd");
        set.add("asdsf");
        set.add("ghgg");
        set.add("rthh");

        for (String s : set) {
            // asdsf-dffd-ghgg-rthh
            System.out.print(s + "-");
        }
    }

    /**
     * Person类Comparable
     * 自然顺序(Comparable)
     */
    @Test
    public void testBeanWithComparable() {
        Set<Person> set = new TreeSet<Person>();
        set.add(new Person("zhangsan", 12));
        set.add(new Person("lisi", 14));
        set.add(new Person("wanger", 14));
        set.add(new Person("zhaoqian", 10));

        for (Person s : set) {
            System.out.print(s + "-");
        }
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

        Set<User> set = new TreeSet<User>((p1, p2) -> {
            // 优先年龄排序
            if (p1.getAge() > p2.getAge()) {
                return -1;
            } else if (p1.getAge() < p2.getAge()) {
                return 1;
            } else {
                // 按姓氏排序
                return p1.getName().compareTo(p2.getName());
            }
        });
        set.add(u1);
        set.add(u2);
        set.add(u3);
        set.add(u4);
        /*[User{name='lisi', age=14}, User{name='wanger', age=14},
        User{name='zhangsan', age=12}, User{name='zhaoqian', age=10}]*/
        System.out.println(set);

        // 在添加数据时进行排序,数据更改不会影响原来的顺序
        // u4.setAge(15);

        /*[User{name='lisi', age=14}, User{name='wanger', age=14},
        User{name='zhangsan', age=12}, User{name='zhaoqian', age=15}]*/
        System.out.println("更改后数据：" + set);

    }
}
