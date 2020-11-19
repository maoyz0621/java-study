/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service.impl.required;

import com.myz.transaction.propotation.dao.UserRepositoryB;
import com.myz.transaction.propotation.pojo.UserB;
import com.myz.transaction.propotation.service.UserServiceRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author maoyz0621 on 19-5-29
 * @version: v1.0
 */
@Service("userServiceRequiredB")
public class UserServiceRequiredBImpl implements UserServiceRequired<UserB> {

    @Autowired
    private UserRepositoryB userRepositoryB;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void save(Object o) {
        userRepositoryB.save((UserB) o);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void saveWithExcept(Object o) {
        userRepositoryB.save((UserB) o);
        throw new RuntimeException();
    }

}
