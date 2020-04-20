/**
 * Copyright 2020 Inc.
 **/
package com.myz.design.singleton;

/**
 * 枚举类可以实现策略模式
 *
 * @author maoyz0621 on 20-4-17
 * @version v1.0
 */
public enum SingletonEnumStrategy {
    EXPRESS {
        @Override
        public void t() {
            // todo
            System.out.println("EXPRESS");
        }
    },
    NORMAL {
        @Override
        public void t() {
            // todo
            System.out.println("NORMAL");
        }
    };

    public abstract void t();
}