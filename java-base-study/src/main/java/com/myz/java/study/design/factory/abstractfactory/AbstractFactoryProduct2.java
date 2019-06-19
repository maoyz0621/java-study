package com.myz.java.study.design.factory.abstractfactory;

/**
 * @author maoyz on 18-3-17.
 */
class AbstractFactoryProduct2 implements AbstractFactory {

    @Override
    public ProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB2();
    }

    @Override
    public ProductC createProductC() {
        return new ProductC2();
    }
}
