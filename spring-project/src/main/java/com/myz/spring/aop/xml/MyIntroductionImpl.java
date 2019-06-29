package com.myz.spring.aop.xml;

/**
 * @author maoyz on 18-3-7.
 */
public class MyIntroductionImpl implements MyIntroduction {

    @Override
    public void introduction() {
        System.out.println("Introduction ...");
    }
}
