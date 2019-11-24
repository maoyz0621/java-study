package com.myz.java.study.base.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 引用分类：
 * 　　强　弱　软　虚
 *
 * @author maoyz on 18-3-1.
 */
public class ReferenceTest {

    /**
     * 强引用
     */
    @Test
    public void testReference() {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();

        System.out.println(o1);
        System.out.println(o2);

    }

    /**
     * 弱引用
     */
    @Test
    public void testSoftReference() {
        Object o1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1 = null;
        System.gc();
        System.out.println("\nSystem.gc() After\n");

        System.out.println(o1);
        System.out.println(reference.get());

    }

    /**
     * 弱引用, 内存空间不足
     * 运行参数: -Xms10M -Xmx10m -XX:+PrintGCDetails
     */
    @Test
    public void testSoftReferenceNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1 = null;
        System.gc();
        System.out.println("\nSystem.gc() After\n");

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(o1);
            System.out.println(reference.get());
        }

    }

    /**
     * 常量池共享，不能被回收 WeakReference
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

    /**
     * 使用ReferenceQueue
     * 使用对象, 存在heap中
     */
    @Test
    public void testWeakReferenceQueeu() {
        Integer i = new Integer(1);
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        WeakReference<Integer> weakReference = new WeakReference<>(i, queue);
        System.out.println(weakReference.get());
        System.out.println(queue.poll());

        System.out.println("======================================");

        i = null;
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(weakReference.get());
        System.out.println(queue.poll());
    }

    /**
     * 使用基础类型
     */
    @Test
    public void testWeakReferenceQueeuBase() {
        Integer i = 1;
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        WeakReference<Integer> reference = new WeakReference<>(i, queue);
        System.out.println(reference.get());
        // 此时null
        System.out.println(queue.poll());

        System.out.println("======================================");

        i = null;
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(reference.get());
        // 取得值
        System.out.println(queue.poll());
    }

    /**
     * 虚引用PhantomReference不能单独使用, 必须配合ReferenceQueue使用
     * get()总是null
     */
    @Test
    public void testPhantomReference() {
        Integer i = new Integer(1);
        ReferenceQueue<Integer> queue = new ReferenceQueue<>();
        PhantomReference<Integer> phantomReference = new PhantomReference<>(i, queue);
        // 总是null
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());

        System.out.println("======================================");

        i = null;
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(phantomReference.get());
        System.out.println(queue.poll());
    }


}
