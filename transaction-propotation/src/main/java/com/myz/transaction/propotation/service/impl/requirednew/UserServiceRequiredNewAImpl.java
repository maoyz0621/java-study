/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service.impl.requirednew;

import com.myz.transaction.propotation.dao.UserRepositoryA;
import com.myz.transaction.propotation.pojo.UserA;
import com.myz.transaction.propotation.service.UserServiceRequiredNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Service("userServiceRequiredNewA")
public class UserServiceRequiredNewAImpl implements UserServiceRequiredNew<UserA> {

    @Autowired
    private UserRepositoryA userRepositoryA;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(Object o) {
        if (o instanceof UserA) {
            userRepositoryA.save((UserA) o);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveNew(UserA userA) {
        userRepositoryA.save((UserA) userA);
    }
}
