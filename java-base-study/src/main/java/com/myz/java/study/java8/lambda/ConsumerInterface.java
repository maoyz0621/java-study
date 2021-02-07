package com.myz.java.study.java8.lambda;

/**
 * 编写一个函数接口
 *
 * @author maoyz
 */
@FunctionalInterface
public interface ConsumerInterface<T> {

    void accept(T t);

    /**
     * default方法也可以
     */
    default void test() {
    }

}
