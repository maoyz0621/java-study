/**
 * Copyright 2020 Inc.
 **/
package com.myz.java.study.juc.lock;

/**
 * @author maoyz0621 on 20-5-11
 * @version v1.0
 */
public class ReentrantLockTest0 {

    public static void main(String[] args) {
        Resource resource = new Resource();
    }

    static class Resource {

        private int count;

        public void add() {
            count++;
        }
    }
}