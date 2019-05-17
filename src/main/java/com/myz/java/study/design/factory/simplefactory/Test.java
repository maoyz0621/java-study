package com.myz.java.study.design.factory.simplefactory;

/**
 * @author maoyz on 18-3-17.
 */
class Test {

  public static void main(String[] args) {
    ProductA productA = BeanFactory.getBean("com.myz.java.study.design.factory.simplefactory.ProductA", ProductA.class);
    ProductB productB = BeanFactory.getBean("com.myz.java.study.design.factory.simplefactory.ProductB", ProductB.class);

    productA.productName();

    productB.productName();

  }
}
