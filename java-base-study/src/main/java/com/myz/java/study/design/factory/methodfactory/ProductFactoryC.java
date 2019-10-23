package com.myz.java.study.design.factory.methodfactory;

/**
 * 工厂实现类C
 * @author maoyz on 18-3-17.
 */
public class ProductFactoryC implements ProductFactory {

    @Override
    public Product create() {
        return new ProductC();
    }
}
