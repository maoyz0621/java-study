/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service.impl.required;

import com.myz.transaction.propotation.pojo.UserA;
import com.myz.transaction.propotation.pojo.UserB;
import com.myz.transaction.propotation.service.UserServiceRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 在外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰
 *
 * @author maoyz0621 on 19-6-29
 * @version: v1.0
 */
@Service
public class ServiceRequired {

    @Autowired
    @Qualifier("userServiceRequiredA")
    private UserServiceRequired userServiceRequiredA;

    @Autowired
    @Qualifier("userServiceRequiredB")
    private UserServiceRequired userServiceRequiredB;

    /**
     * 外围没有开启事务, 自身抛出异常, 在自己的事务中独立运行，外围方法异常不影响内部方法独立的事务
     * <p>
     * 结果:
     * t_user_a 插入值
     * t_user_b 插入值
     */
    public void notransaction_exception_required_required() {
        selfExecept();
    }

    /**
     * 外围没有开启事务, service抛出异常, 都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响
     * <p>
     * 结果:
     * t_user_a 插入值
     * t_user_b 未插入值
     */
    public void notransaction_required_required_exception() {
        serviceExecept();
    }

    /**
     * 外围开启事务, 自身抛出异常, 内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚
     * <p>
     * 结果:
     * t_user_a 未插入值
     * t_user_b 未插入值
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        selfExecept();
    }

    /**
     * 外围开启事务, service抛出异常, 内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚
     * 结果:
     * t_user_a 未插入值
     * t_user_b 未插入值
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_required_exception() {
        serviceExecept();
    }

    /**
     * 外围开启事务, service抛出异常并catch捕获, 内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transaction_required_required_exception_try() {
        UserA user1 = new UserA();
        user1.setFirstName("张三");
        userServiceRequiredA.save(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        try {
            userServiceRequiredB.saveWithExcept(user2);
        } catch (Exception e) {
            System.err.println("方法回滚");
        }
    }

    private void selfExecept() {
        UserA user1 = new UserA();
        user1.setFirstName("张三");
        userServiceRequiredA.save(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        userServiceRequiredB.save(user2);

        throw new RuntimeException();
    }

    private void serviceExecept() {
        UserA user1 = new UserA();
        user1.setFirstName("张三");
        userServiceRequiredA.save(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        userServiceRequiredB.saveWithExcept(user2);
    }

}
