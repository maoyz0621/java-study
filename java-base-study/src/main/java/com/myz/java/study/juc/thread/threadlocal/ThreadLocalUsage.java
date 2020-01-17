package com.myz.java.study.juc.thread.threadlocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现不同方法间的数据共享，此时不需要传递引用参数，直接在ThreadLocal中获取
 *
 * @author maoyz
 */
public class ThreadLocalUsage {

    static ExecutorService fixedThreadPool = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10),
            new ThreadFactoryBuilder().setNameFormat("thread_pool_%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            fixedThreadPool.execute(() -> new Service1().process());
        }

    }
}

/* process() 中不需要传递参数 User user */
class Service1 {

    public void process() {
        User user = new User("鲁毅-" + UUID.randomUUID().toString());
        //将User对象存储到 contextHolder 中
        UserContextHolder.contextHolder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.contextHolder.get();
        System.out.println("Service2拿到用户名: " + user.name);
        new Service3().process();
    }
}

/* remove() 释放内存， 避免 OOM */
class Service3 {

    public void process() {
        User user = UserContextHolder.contextHolder.get();
        System.out.println("Service3拿到用户名: " + user.name);
        //手动释放内存，从而避免内存泄漏
        UserContextHolder.contextHolder.remove();
    }
}


class UserContextHolder {

    public static ThreadLocal<User> contextHolder = new InheritableThreadLocal<>();

}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}

