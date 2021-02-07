package com.myz.design.decorator.demo;

/**
 * 装饰物A
 * @author maoyz on 18-3-17.
 */
class ManagerA extends Manager {

    public ManagerA(Person person) {
        super(person);
    }

    @Override
    public void doCoding() {
        doEarlyWork();
        person.doCoding();
    }

    /**
     * 项目经理开始前期准备工作
     */
    public void doEarlyWork() {
        System.out.println("项目经理A做需求分析");
        System.out.println("项目经理A做架构设计");
        System.out.println("项目经理A做详细设计");
    }
}
