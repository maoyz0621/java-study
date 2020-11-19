package com.myz.java.study.base.collection.map.diy;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 自定义HashMap
 * 1 key不能重复
 *
 * @author maoyz on 18-2-21.
 */
public class MyHashMap {

    /**
     * 链表数组
     */
    private LinkedList<MyEntry>[] tables = new LinkedList[998];

    private int size;

    /**
     * 存放key的hash码
     * 链表中存放MyEntry
     */
    public void put(Object key, Object value) {
        int hash = hash(key);
        MyEntry e = new MyEntry(key, value);

        // 1 获取下标元素是否有LinkedList
        LinkedList<MyEntry> list = tables[hash];

        // 如果不存在相同hash码
        if (list == null) {
            // 新建一个LinkedList
            list = new LinkedList<>();
            list.add(e);
            // 放入数组中
            tables[hash] = list;
            size++;
        } else {
            // 存在相同hash码，hash冲突
            for (MyEntry entry : list) {
                // 判断链表中key是否重复
                // 如果key相同
                if (Objects.equals(entry.key, key)) {
                    // 替换value值
                    entry.value = value;
                } else {
                    // hashcode相同，对象不相同
                    list.add(e);
                    size++;
                }
            }
        }
    }

    public Object get(Object key) {
        int hash = hash(key);
        if (tables[hash] != null) {
            LinkedList l = tables[hash];
            for (int i = 0; i < l.size(); i++) {
                MyEntry myEntry = tables[hash].get(i);
                if (Objects.equals(myEntry.key, key)) {
                    return myEntry.value;
                }
            }
        }
        return null;
    }

    public Boolean containsKey(Object key) {
        int hash = hash(key);
        // 判断list[hash]是否存在
        if (tables[hash] != null) {
            for (int i = 0; i < tables.length; i++) {
                MyEntry entry = (MyEntry) tables[hash].get(i);
                return Objects.equals(entry.key, key);
            }
        }

        return false;
    }

    /**
     * key的hash值
     */
    private final int hash(Object key) {
        int hash = key.hashCode();
        // 当hash为负数时
        hash = hash < 0 ? -hash : hash;
        // hash算法：hashCode取模
        return (null == key) ? 0 : hash % tables.length;
    }

    public int size() {
        return size;
    }

    /**
     *
     */
    private class MyEntry {
        private Object key;
        private Object value;

        public MyEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put("demo", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("c", "4");
        System.out.println(map.size);
        System.out.println(map.get("demo"));
        System.out.println(map.get("c"));
        System.out.println(map.get("d"));
        System.out.println(map.containsKey("demo"));
        System.out.println(map.containsKey("d"));

    }
}
