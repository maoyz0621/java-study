/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.base.collection.map.diy;

/**
 * @author maoyz on 2018/7/8
 * @version: v1.0
 */
public class MyDeepHashMap<K, V> implements MyMap<K, V> {

    /**
     * 没有初始化容器，懒加载，存放HashMap数组
     */
    Node<K, V>[] table = null;

    /**
     * 实际容量大小
     */
    int size;

    /**
     * 默认负载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * table默认初始化容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    @Override
    public V put(K k, V v) {
        // 1 table是否为空
        // 2 扩容
        resize();
        // 3 计算hash值，指定插入数据位置
        return null;
    }

    @Override
    public V get(Object v) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 扩容
     */
    private void resize() {
        // 之前数组大小的2倍

    }

    /**
     * 静态内部类
     */
    static class Node<K, V> implements MyMap.MyEntry<K, V> {

        K key;

        V value;
        /**
         * 存储指向下一个Node的引用，单链表结构
         */
        Node<K, V> next;
        /**
         * 对key的hashcode值进行hash运算后得到的值，存储在Entry，避免重复计算
         */
        int hash;

        public Node(K key, V value, Node<K, V> next, int hash) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        /**
         * 返回老的值
         */
        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            this.value = newValue;
            return oldValue;
        }
    }
}
