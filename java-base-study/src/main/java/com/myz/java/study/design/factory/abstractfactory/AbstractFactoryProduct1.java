package com.myz.java.study.design.factory.abstractfactory;

/**
 * @author maoyz on 18-3-17.
 */
class AbstractFactoryProduct1 implements AbstractFactory {

    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }

    @Override
    public ProductC createProductC() {
        return new ProductC1();
    }
}
