package com.myz.java.study.design.singleton;

/**
 * 类级内部类？简单点说，类级内部类指的是：有static修饰的成员式内部类。如果没有static修饰的成员式内部类被称为对象级内部类。
 * 类级内部类相当于其外部类的static成分，它的对象与外部类对象间不存在依赖关系，因此可直接创建。而对象级内部类的实例，是绑定在外部对象实例中的。
 * 类级内部类中，可以定义静态的方法，在静态方法中只能够引用外部类中的静态成员方法或者成员变量。
 * <p>
 * 类级内部类相当于其外部类的成员，只有在第一次被使用的时候才会被装载。
 * <p>
 * 再来看看多线程缺省同步锁的知识。
 * 在多线程开发中，为了解决并发问题，主要是通过使用synchronized来加互斥锁进行同步控制。但是在某些情况中，JVM已经隐含地为您执行了同步，
 * 这些情况下就不用自己再来进行同步控制
 *
 * @author maoyz on 18-1-6.
 */
class Singleton {

    /**
     * 静态内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     * 由于是静态的域，因此只会被虚拟机在装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
     */
    private static class SingletonHolder {
        /**
         * 保证了内存中只有一个实例存在,保证了线程安全
         */
        private static final Singleton INSTANCE = new Singleton();
    }

    /**
     * 构造方法私有化
     */
    private Singleton() {
    }

    /**
     * 调用此方法时才加载静态内部类
     *
     * @return
     */
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
