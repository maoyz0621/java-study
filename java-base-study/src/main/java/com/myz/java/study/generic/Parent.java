/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.generic;

/**
 * 编译之后
 *
 * @author maoyz0621 on 20-3-13
 * @version v1.0
 */
class Parent<T> {
    private T val;

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}

/*
class Parent {
    private Object val;

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }
}
 */
