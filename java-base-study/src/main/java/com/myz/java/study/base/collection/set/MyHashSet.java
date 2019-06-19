package com.myz.java.study.base.collection.set;

import java.util.HashMap;

/**
 * 自定义Set
 * 底层通过HashMap实现，利用Map的key不可重复性
 *
 * @author maoyz on 18-2-22.
 */
public class MyHashSet {

    private HashMap<Object, Object> map;

    private static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new HashMap<>();
    }

    public boolean add(Object o) {
        // set不可重复是利用的HashMap的key不可重复
        return map.put(o, PRESENT) == null;
    }

    public boolean remove(Object o) {
        return map.remove(o) == null;
    }

    public int size() {
        // 返回map的size()
        return map.size();
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add("demo");
        set.add("demo");
        set.add("b");
        System.out.println(set.size());

        set.remove("demo");
        System.out.println(set.size());
    }
}
