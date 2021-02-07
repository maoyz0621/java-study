/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public abstract class AbstractObserver implements Observer {

    private String name;
    private String message;

    protected AbstractObserver(String name) {
        this.name = name;
    }

    /**
     * @param o 被观察值对象
     * @param arg Observable.notifyObservers(arg)
     */
    @Override
    public void update(Observable o, Object arg) {
        //
        if (arg instanceof String){
            setMessage((String) arg);
        } else {
            throw new RuntimeException("case in error");
        }
        read();
    }

    protected abstract void read();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
