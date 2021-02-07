/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.observer;


/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class ConcreteObserverB extends AbstractObserver {

    public ConcreteObserverB(String name) {
        super(name);
    }

    @Override
    protected void read() {
        System.out.println("ConcreteObserverB " + getName() + " -->" + getMessage());
    }
}
