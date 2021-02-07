/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.base.collection.map.diy;

import java.util.Objects;

/**
 * 数组+链表+红黑树
 * 当链表长度大于8时，链表转红黑树(转之前，如果当前数组长度小于64，选择先数组扩容　treeifyBin())
 *
 * @author maoyz on 2018/7/8
 * @version v1.0
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
     * 默认的填充因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * table默认初始化容量
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    // 临界值
    int threshold;
    // 加载因子
    final float loadFactor;

    public MyDeepHashMap() {
        // 默认加载因子0.75
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    // 指定容量大小
    public MyDeepHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    //　指定容量大小和加载因子
    public MyDeepHashMap(int initialCapacity, float loadFactor) {
        // 省略参数判断
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    @Override
    public V put(K k, V v) {
        return putVal(hash(k), k, v, false, true);
    }

    /**
     * @param hash
     * @param k
     * @param v
     * @param onlyIfAbsent
     * @param evict
     * @return
     */
    private V putVal(int hash, K k, V v, boolean onlyIfAbsent, boolean evict) {
        // 1 table是否为空
        // 2 扩容
        // resize();
        // // 3 计算hash值，指定插入数据位置
        // return null;
        return null;
    }


    @Override
    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K, V> getNode(int hash, Object key) {
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

    // 计算hash
    public final int hash(Object key) {
        return 0;
    }

    private int tableSizeFor(int initialCapacity) {
        return 0;
    }

    /**
     * 静态内部类
     * 单向链表
     */
    static class Node<K, V> implements MyMap.MyEntry<K, V> {
        final K key;
        V value;
        // 存储指向下一个Node的引用，单链表结构
        Node<K, V> next;
        // 对key的hashcode值进行hash运算后得到的值，存储在Entry，避免重复计算
        final int hash;

        Node(K key, V value, Node<K, V> next, int hash) {
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

        //返回老的值
        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        // ^ 异或运算符 只要相同都是false(0);只有不同才是true(1)
        // & 与运算符   全true(1),即为true(1);全false(0),即为false(0);一false(0)一true(1),还是false(0)
        // | 或运算符   遇true(1)就是true(1), 无true(1)就是false(0)
        // ~ 取反运算符
        // 左移运算(<<)
        // 右移运算符(>>)
        // 无符号右移(>>>)
        @Override
        public int hashCode() {
            // 异或运算
            return Objects.hash(key) ^ Objects.hashCode(value);
        }
    }

    static final class TreeNode<K, V> extends Node<K, V> {
        TreeNode<K, V> parent; // 父节点
        TreeNode<K, V> left; // 左节点
        TreeNode<K, V> right; // 右节点
        TreeNode<K, V> prev;
        boolean red; // 颜色属性

        public TreeNode(K key, V value, Node<K, V> next, int hash) {
            super(key, value, next, hash);
        }

        // 返回当前节点的根节点
        final TreeNode<K, V> root() {
            for (TreeNode<K, V> r = this, p; ; ) {
                // 如果parent=null,则是根节点
                if ((p = r.parent) == null) {
                    return r;
                }
                r = p;
            }
        }
    }
}