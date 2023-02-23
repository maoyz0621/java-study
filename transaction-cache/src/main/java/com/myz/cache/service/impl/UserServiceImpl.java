/**
 * Copyright 2019 Inc.
 **/
package com.myz.cache.service.impl;

import com.myz.cache.dao.UserRepository;
import com.myz.cache.pojo.UserPO;
import com.myz.cache.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

    @Autowired
    DataSourceTransactionManager transactionManager;

    // 声明式事务
    private void catchTransaction() {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        // 设置事务名称
        transactionDefinition.setName("tx_name");
        // 设置事务策略
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            // todo ... 执行业务代码

            // 提交事务
            transactionManager.commit(status);
        } catch (Exception e) {
            // 回滚事务
            transactionManager.rollback(status);
            throw e;
        }
    }

    // 手动设置回滚
    @Transactional(rollbackFor = Exception.class)
    public void rollback() {

        try {
            // todo ... 执行业务代码


        } catch (Exception e) {
            // 手动回滚，这样上层就无需去处理异常
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    /**
     * 使用Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
     * 使用TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);回滚到savePoint。
     */
    @Transactional(rollbackFor = Exception.class)
    public void rollback1() {

        // 设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {

            // todo ... 执行业务代码


        } catch (Exception e) {
            // 回滚到savePoint
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            throw e;
        }
    }
}
