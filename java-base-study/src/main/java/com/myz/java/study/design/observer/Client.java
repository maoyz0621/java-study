/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class Client {

    public static void main(String[] args) {
        Observable observable = new WechatServer();
        Observer observerA = new ConcreteObserverA("observerA");
        Observer observerB = new ConcreteObserverB("observerB");
        observable.addObserver(observerA);
        observable.addObserver(observerB);
        ((WechatServer) observable).setInfomation("Server send message ...");

        // B下线
        observable.deleteObserver(observerB);
        ((WechatServer) observable).setInfomation("Server another message ...");

    }
}
