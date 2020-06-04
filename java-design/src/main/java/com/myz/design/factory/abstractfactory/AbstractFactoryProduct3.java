package com.myz.design.factory.abstractfactory;

/**
 * @author maoyz on 18-3-17.
 */
class AbstractFactoryProduct3 implements AbstractFactory {

    @Override
    public ProductA createProductA() {
        return new ProductA3();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB3();
    }

    @Override
    public ProductC createProductC() {
        return new ProductC3();
    }
}
