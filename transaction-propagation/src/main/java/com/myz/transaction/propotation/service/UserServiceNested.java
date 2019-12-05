/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
public interface UserServiceNested<T> extends UserService {

    default void saveNested(T t) {
    }
}
