/**
 * 如果只使用原生的ThreadLocal，那么在异步的场景下ThreadLocal#get()方法则获取不到，所以采用InheritableThreadLocal代替ThreadLocal
 * InheritableThreadLocal，则可以解决兼容 new Thread()场景
 * InheritableThreadLocal不能解决线程场景，因为线程池存在线程复用问题，不会每次new，所以线程取InheritableThreadLocal 的值还是残留第一次传递进来值，导致结果不正确
 *
 * @author maoyz0621 on 2022/5/25
 * @version v1.0
 */
package com.myz.java.study.juc.thread.threadlocal;