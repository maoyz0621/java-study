/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.observer;


/**
 * 具体观察者
 *
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class ConcreteObserverA extends AbstractObserver {

    public ConcreteObserverA(String name) {
        super(name);
    }

    @Override
    protected void read() {
        System.out.println("ConcreteObserverA " + getName() + " -->" + getMessage());
    }
}
