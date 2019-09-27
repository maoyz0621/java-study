/*
 * Copyright (C) 2018, All rights Reserved, Designed By www.xiniaoyun.com
 * @author: maoyz
 * @Copyright: 2019-09-27 14:10 www.xiniaoyun.com Inc. All rights reserved.
 * 注意：本内容仅限于南京微欧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.myz.java.study.base.thread.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maoyz
 */
@Slf4j
public class ReentrantLockTest {

    private Lock lock = new ReentrantLock(true);

    public void reentrant() {
        lock.lock();
        try {
            log.debug("Lock1 = {}", Thread.currentThread().getName());
            lock.lock();
            log.debug("Lock2 = {}", Thread.currentThread().getName());
            lock.lock();
            log.debug("Lock3 = {}", Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }


}
