package com.myz.design.decorator.demo;

/**
 * @author maoyz on 18-3-17.
 */
class Test {

    /**
     * 赋予被装饰者Employee
     */
    public static void main(String[] args) {
        // 先装饰A功能,在装饰B功能
        Manager manager = new ManagerA(
                              new ManagerB(
                                  new Employee()));

        manager.doCoding();

    }
}
