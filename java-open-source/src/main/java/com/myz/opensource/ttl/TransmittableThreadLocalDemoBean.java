/**
 * Copyright 2021 Inc.
 **/
package com.myz.opensource.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 保存对象的引用地址
 *
 * @author maoyz0621 on 2021/7/22
 * @version v1.0
 */
public class TransmittableThreadLocalDemoBean {

    public static ThreadLocal<Stud> context = new TransmittableThreadLocal<>();
    public static ExecutorService executorService =
            TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) throws InterruptedException {
        context.set(new Stud("a", 1));
        // User{name='a', age=1}
        System.out.println("context = " + context.get());

        executorService.submit(() -> {
            System.out.println("第一次子线程读取：" + context.get());
        });
        Thread.sleep(1000);

        System.out.println("\r\n====================================\r\n");

        context.get().setAge(2);
        // User{name='a', age=2}
        System.out.println("context-1 = " + context.get());

        // 父线程修改了本地变量后，新提交到线程池的子线程成功读取到新值。
        executorService.submit(() -> {
            // User{name='a', age=2}
            System.out.println("父线程修改了本地变量后子线程读取：" + context.get());

            // 子线程修改变量值
            context.get().setName("b");
            // User{name='b', age=2}
            System.out.println("子线程修改变量值后子线程读取：" + context.get());
        });

        Thread.sleep(1000);
        System.out.println("\r\n====================================\r\n");

        // 子线程修改不影响父线程的变量值
        // User{name='b', age=2}
        System.out.println("子线程修改变量值后主线程读取context = " + context.get());

        executorService.shutdown();
    }

    static class Stud {
        private String name;
        private int age;

        public Stud(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
    }
}