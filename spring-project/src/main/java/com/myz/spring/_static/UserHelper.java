package com.myz.spring._static;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 对于工具类，提供static方法
 * 1. @Component
 * 2. 去掉@Component，参考AutowiredStaticSmartInitializingSingleton
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/15 19:22
 */
// @Component
public class UserHelper {

    static UserClient userClient;

    @Autowired
    public void setUcClient(UserClient userClient) {
        UserHelper.userClient = userClient;
    }

    public static void get() {
        userClient.test();
    }

}
