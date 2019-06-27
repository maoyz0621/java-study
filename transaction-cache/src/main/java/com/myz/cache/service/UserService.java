/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.service;

import com.myz.cache.pojo.UserPO;

import java.util.List;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
public interface UserService {

    List<UserPO> findAll();

    void save();

    void  execute();

    void executeWithAopContext();
}
