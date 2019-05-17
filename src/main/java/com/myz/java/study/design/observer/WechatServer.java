/**
 * Copyright 2018 asiainfo Inc.
 **/
package com.myz.java.study.design.observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 被观察者，也就是微信公众号服务
 * 实现了Observerable接口，对Observerable接口的三个方法进行了具体实现
 *
 * @author maoyz on 18-10-20
 * @version: v1.0
 */
public class WechatServer extends Observable {

    private List<Observer> observers;
    private String message;

    public WechatServer() {
        this.observers = new ArrayList<Observer>();
    }

    public void setInfomation(String msg) {
        this.message = msg;
        System.out.println("微信服务更新消息： " + msg);
        // 一定要更改状态
        setChanged();
        // 消息更新，通知所有观察者
        notifyObservers(message);
    }


}
