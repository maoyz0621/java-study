/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service.impl.required;

import com.myz.transaction.propotation.dao.UserRepositoryA;

import com.myz.transaction.propotation.pojo.UserA;
import com.myz.transaction.propotation.service.UserServiceRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Service("userServiceRequiredA")
public class UserServiceRequiredAImpl implements UserServiceRequired<UserA> {

    @Autowired
    private UserRepositoryA userRepositoryA;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(Object o) {
        if (o instanceof UserA){
            userRepositoryA.save((UserA) o);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveWithExcept(Object o) {
        userRepositoryA.save((UserA) o);
        throw new RuntimeException();
    }
}
