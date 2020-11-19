package com.myz.design.decorator.demo;

/**
 * 被装饰者
 * @author maoyz on 18-3-17.
 */
class Employee implements Person {

    @Override
    public void doCoding() {
        System.out.println("程序员加班写程序啊，写程序，终于写完了。。。");
    }
}
