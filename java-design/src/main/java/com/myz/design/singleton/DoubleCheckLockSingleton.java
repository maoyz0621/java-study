package com.myz.design.singleton;

/**
 * 懒汉式单例设计模式，常规用法
 * 1  构造方法私有化
 * 2  内部实例化对象为null
 * 3  提供对外接口，必须是static
 * 4  线程非安全的
 *
 * @author maoyz
 */
public class DoubleCheckLockSingleton {

    /**
     * 因为这个变量要在静态方法中使用，所以需要加上static修饰
     * static对象,定义成null
     * volatile 可见性
     */
    private static volatile DoubleCheckLockSingleton instance = null;

    /**
     * 构造方法私有化
     */
    private DoubleCheckLockSingleton() {
    }

    /**
     * 提供获取实例的方法
     * 定义成static
     * 双重检查加锁
     *
     * @return SingletonLazy
     */
    public static DoubleCheckLockSingleton getInstance() {
        //　判断存储实例的变量是否有值
        if (instance == null) {
            //　同步块，线程安全的创建实例
            synchronized (DoubleCheckLockSingleton.class) {
                //　再次检查实例是否存在，如果不存在才真的创建实例
                if (instance == null) {
                    //　如果没有，就创建一个类实例，并把值赋值给存储类实例的变量
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        //　如果有值，那就直接使用
        return instance;
    }
}
