package com.myz.java.study.juc.thread.threadlocal;

/**
 * @author maoyz
 */
public class InheritableThreadLocalContext {

    private static ThreadLocal<Context> threadLocalContext = new ThreadLocal<>();

    static class Context {

        String username;

        Integer age;
    }

    public static String get() {
        return threadLocalContext.get().username;
    }

}
