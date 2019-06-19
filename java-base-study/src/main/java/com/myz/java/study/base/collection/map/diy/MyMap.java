/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.base.collection.map.diy;

/**
 * @author maoyz on 2018/7/9
 * @version: v1.0
 */
public interface MyMap<K, V> {

    V put(K k, V v);

    V get(Object v);

    int size();

    /**
     * 功能类似node
     */
    interface MyEntry<K, V> {

        K getKey();

        V getValue();

        V setValue(V v);
    }
}
