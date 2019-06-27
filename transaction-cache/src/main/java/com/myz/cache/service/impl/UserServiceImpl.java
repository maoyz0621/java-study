/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.service.impl;

import com.myz.cache.dao.UserRepository;
import com.myz.cache.pojo.UserPO;
import com.myz.cache.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserPO> findAll() {
        return userRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save() {
        userRepository.deleteById(1L);
        int i = 1 / 0;
        UserPO userPO = new UserPO("aaaa", "1");
    }

    @Override
    public void execute() {
        save();
    }

    @Override
    public void executeWithAopContext() {
        ((UserService) AopContext.currentProxy()).save();
    }
}
