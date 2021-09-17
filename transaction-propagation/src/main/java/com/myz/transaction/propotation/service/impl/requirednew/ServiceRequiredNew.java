/**
 * Copyright 2019 Inc.
 **/
package com.myz.transaction.propotation.service.impl.requirednew;

import com.myz.transaction.propotation.pojo.UserA;
import com.myz.transaction.propotation.pojo.UserB;
import com.myz.transaction.propotation.service.UserServiceRequiredNew;
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
public class ServiceRequiredNew {

    @Autowired
    @Qualifier("userServiceRequiredNewA")
    private UserServiceRequiredNew userServiceRequiredNewA;

    @Autowired
    @Qualifier("userServiceRequiredNewB")
    private UserServiceRequiredNew userServiceRequiredNewB;

    /**
     * 外围没有开启事务, 自身抛出异常, 插入“张三”、“李四”方法都在自己的事务中独立运行, 外围方法抛出异常回滚不会影响内部方法
     * <p>
     * 结果:
     * t_user_a 插入值
     * t_user_b 插入值
     */
    public void notransaction_exception_requiresNew_requiresNew() {
        selfExcept();
    }

    /**
     * 外围没有开启事务, service抛出异常, 都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响
     * <p>
     * 结果:
     * t_user_a 插入值
     * t_user_b 未插入值
     */
    public void notransaction_requiresNew_requiresNew_exception() {
        serviceExcept();
    }

    /**
     * 外围开启事务, 自身抛出异常, 插入“张三1111”方法和外围方法一个事务，插入“李四”方法、插入“张三”方法分别在独立的新建事务中，
     * 外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三1111”的方法回滚。
     * <p>
     * 结果:
     * t_user_a 未插入张三1111, 插入李四
     * t_user_b 插入值
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_requiresNew_requiresNew() {
        UserA user1 = new UserA();
        user1.setFirstName("张三1111");
        userServiceRequiredNewA.save(user1);

        selfExcept();
    }

    /**
     * 外围开启事务, service抛出异常, 内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚
     * 外围方法开启事务，插入“张三1111”方法和外围方法一个事务，插入“张三”方法、插入“李四”方法分别在独立的新建事务中。
     * 插入“李四”方法抛出异常，首先插入 “李四”方法的事务被回滚，异常继续抛出被外围方法感知，外围方法事务亦被回滚，故插入“张三1111”方法也被回滚。
     * 结果:
     * t_user_a 未插入值
     * t_user_b 未插入值
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_required_requiresNew_requiresNew_exception() {
        UserA user0 = new UserA();
        user0.setFirstName("张三1111");
        userServiceRequiredNewA.save(user0);

        UserA user01 = new UserA();
        user01.setFirstName("张三00000");
        userServiceRequiredNewA.saveNew(user01);


        UserB user1 = new UserB();
        user1.setFirstName("张三");
        userServiceRequiredNewB.save(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        userServiceRequiredNewB.saveWithExcept(user2);
    }

    /**
     * 外围开启事务, service抛出异常并catch捕获, 插入“张三1111”方法和外围方法一个事务，
     * 插入“李四”方法、插入“张三”方法分别在独立的新建事务中。插入“李四”方法抛出异常，首先插入“李四”方法的事务被回滚，异常被catch不会被外围方法感知，外围方法事务不回滚，
     * 故插入“张三1111”方法插入成功。
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transaction_required_requiresNew_requiresNew_exception_try() {
        UserA user0 = new UserA();
        user0.setFirstName("张三1111");
        userServiceRequiredNewA.save(user0);

        UserA user01 = new UserA();
        user01.setFirstName("张三00000");
        userServiceRequiredNewA.saveNew(user01);


        UserB user1 = new UserB();
        user1.setFirstName("张三");
        userServiceRequiredNewB.save(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        try {
            userServiceRequiredNewB.saveWithExcept(user2);
        } catch (Exception e) {
            System.err.println("方法回滚");
        }
    }

    private void selfExcept() {
        UserA user1 = new UserA();
        user1.setFirstName("张三");
        userServiceRequiredNewA.saveNew(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        userServiceRequiredNewB.save(user2);

        throw new RuntimeException();
    }

    private void serviceExcept() {
        UserA user1 = new UserA();
        user1.setFirstName("张三");
        userServiceRequiredNewA.saveNew(user1);

        UserB user2 = new UserB();
        user2.setFirstName("李四");
        userServiceRequiredNewB.saveWithExcept(user2);
    }

}
