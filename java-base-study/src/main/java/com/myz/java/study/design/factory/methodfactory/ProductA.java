package com.myz.java.study.design.factory.methodfactory;

/**
 * 产品实现类A
 * @author maoyz on 18-3-17.
 */
public class ProductA implements Product {

    @Override
    public void productName() {
        System.out.println("product A");
    }
}
