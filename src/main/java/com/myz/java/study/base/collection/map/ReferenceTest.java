package com.myz.java.study.base.collection.map;

import org.junit.Test;

import java.lang.ref.WeakReference;

/**
 * 引用分类：
 * 　　强　弱　软　虚
 *
 * @author maoyz on 18-3-1.
 */
public class ReferenceTest {

    /**
     * 常量池共享，不能被回收
     */
    @Test
    public void testWeakReference() {
        // 常量池共享，不能被回收
        String store = "abc";
        //　堆中,被回收
        String temp = new String("abcd");
        //　弱引用
        WeakReference<String> reference1 = new WeakReference<>(store);
        WeakReference<String> reference2 = new WeakReference<>(temp);

        System.out.println("常量池中gc()前:" + reference1.get());
        System.out.println("堆中gc()前:" + reference2.get());

        store = null;
        temp = null;
        // 告诉垃圾收集器打算进行垃圾收集，而垃圾收集器进不进行收集是不确定的
        System.gc();
        // 强制调用已经失去引用的对象的finalize方法
        System.runFinalization();
        System.out.println("常量池中gc()后:" + reference1.get());
        System.out.println("堆中gc()后:" + reference2.get());
    }
}
