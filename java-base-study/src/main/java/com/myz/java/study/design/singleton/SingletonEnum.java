package com.myz.java.study.design.singleton;

/**
 * 使用枚举来实现单例模式
 * <p>
 * 使用枚举来实现单实例控制，会更加简洁，而且无偿的提供了序列化的机制，并由JVM从根本上提供保障，
 * 绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 * 在枚举类型的序列化和反序列化上，Java做了特殊的规定：在序列化时Java仅仅是将枚举对象的name属性输出到结果中，反序列化的时候则是通过java.lang.Enum的valueOf方法来根据名字查找枚举对象。
 * <p>
 * 一个枚举可以拥有成员变量，成员方法，构造方法
 * 自由序列化，线程安全，保证单例
 * enum有且仅有private的构造器,private修饰符对于构造器是可以省略的，但这不代表构造器的权限是默认权限
 *
 * @author maoyz on 18-1-6.
 */
enum SingletonEnum {

    /**
     * 枚举的元素,它就代表了Singleton的一个实例,无延迟加载
     */
    INSTANCE;

    /**
     * 示意方法，单例可以有自己的操作
     */
    public void singletonOperation() {
        //功能处理
    }


}
