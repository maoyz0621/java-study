package com.myz.design.strategy.paystrategyextend;

/**
 * @author maoyz
 * @version V1.0
 * @date 2021/9/15 20:11
 */
public interface BaseEnum<T> {

    T getCode();

    String getText();

    boolean is(T t);
}
