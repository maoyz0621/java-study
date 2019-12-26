package com.myz.design.factory.methodfactory;

/**
 * 工厂实现类A
 * @author maoyz on 18-3-17.
 */
public class ProductFactoryA implements ProductFactory {

    @Override
    public Product create() {
        return new ProductA();
    }
}
