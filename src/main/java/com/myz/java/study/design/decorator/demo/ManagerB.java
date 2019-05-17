package com.myz.java.study.design.decorator.demo;

/**
 * 装饰物B
 * @author maoyz on 18-3-17.
 */
class ManagerB extends Manager {

    public ManagerB(Person person) {
        super(person);
    }

    @Override
    public void doCoding() {
        person.doCoding();
        doEarlyWork();
    }

    /**
     * 项目经理开始前期准备工作
     */
    public void doEarlyWork() {
        System.out.println("项目经理B 在做收尾工作");
    }
}
