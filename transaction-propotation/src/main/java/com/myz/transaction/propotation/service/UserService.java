/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service;


/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
public interface UserService<T> {

    void save(T t);

    default void saveWithExcept(T t) {
    }

    default void execute() {
    }

    default void executeWithAopContext() {
    }
}
