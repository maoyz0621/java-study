package com.myz.java.study.design.factory.methodfactory;

/**
 * 工厂实现类B
 * @author maoyz on 18-3-17.
 */
public class ProductFactoryB implements ProductFactory {

    @Override
    public Product create() {
        return new ProductB();
    }
}
