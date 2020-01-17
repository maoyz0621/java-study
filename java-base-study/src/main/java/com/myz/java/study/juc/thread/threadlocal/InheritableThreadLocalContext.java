package com.myz.java.study.juc.thread.threadlocal;

/**
 * @author maoyz
 */
public class InheritableThreadLocalContext {

    private static ThreadLocal<Context> threadLocalContext = new ThreadLocal<>();

    /**
     * 可继承父线程属性的ThreadLocal
     */
    private static ThreadLocal<Context> inheritableThreadLocalContext = new InheritableThreadLocal<>();

    static class Context {

        String username;

        Integer age;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public static ThreadLocal<Context> getThreadLocalContext() {
        return threadLocalContext;
    }


    public static ThreadLocal<Context> getInheritableThreadLocalContext() {
        return inheritableThreadLocalContext;
    }


    public static String get() {
        return threadLocalContext.get().username + " => " + threadLocalContext.get().age;
    }

    public static String get0() {
        return inheritableThreadLocalContext.get().username + " => " + inheritableThreadLocalContext.get().age;
    }

}
