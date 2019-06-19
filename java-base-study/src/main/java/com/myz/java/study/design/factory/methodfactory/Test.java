package com.myz.java.study.design.factory.methodfactory;

/**
 * @author maoyz on 18-3-17.
 */
class Test {

    public static void main(String[] args) {
        Product productA = new ProductFactoryA().create();
        Product productB = new ProductFactoryB().create();
        Product productC = new ProductFactoryC().create();

        productA.productName();
        productB.productName();
        productC.productName();
    }
}
