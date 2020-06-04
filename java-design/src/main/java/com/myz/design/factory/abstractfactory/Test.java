package com.myz.design.factory.abstractfactory;

/**
 * @author maoyz on 18-3-17.
 */
class Test {

    public static void main(String[] args) {
        AbstractFactory factory = new AbstractFactoryProduct2();
        ProductA productA = factory.createProductA();

        productA.productName();
    }
}
