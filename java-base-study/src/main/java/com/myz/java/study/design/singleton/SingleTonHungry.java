package com.myz.java.study.design.singleton;

/**
 * 饿汉式单例设计模式
 * 1  构造方法私有化
 * 2  内部实例化对象
 * 3  提供对外接口，必须是static
 * 4  线程安全的，因为虚拟机保证了只会装载一次，在装载类的时候是不会发生并发的。
 *
 * @author maoyz
 */
class SingleTonHungry {

    /**
     * 因为这个变量要在静态方法中使用，所以需要加上static修饰
     * 类初始化时，立即加载类,天然的线程安全
     */
    private static SingleTonHungry instance = new SingleTonHungry();

    /**
     * 构造方法私有化
     */
    private SingleTonHungry() {
    }

    public static SingleTonHungry getInstance() {
        // 直接使用已经创建好的实例
        return instance;
    }
}
