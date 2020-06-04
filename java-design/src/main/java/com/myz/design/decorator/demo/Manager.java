package com.myz.design.decorator.demo;

/**
 * 定义抽象装饰物
 * @author maoyz on 18-3-17.
 */
abstract class Manager implements Person {

    protected Person person;//给雇员升职

    public Manager(Person person) {
        this.person = person;
    }

    //装饰器增加功能
    @Override
    public abstract void doCoding();
}
