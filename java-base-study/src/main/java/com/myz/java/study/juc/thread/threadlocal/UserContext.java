package com.myz.java.study.juc.thread.threadlocal;

/**
 * @author maoyz
 */
public class UserContext implements AutoCloseable {

    static final ThreadLocal<String> USER_CONTEXT = new InheritableThreadLocal<>();

    public UserContext(String name) {
        USER_CONTEXT.set(name);
    }

    public static String currentUser() {
        return USER_CONTEXT.get();
    }

    @Override
    public void close() throws Exception {
        USER_CONTEXT.remove();
    }

    public static void main(String[] args) throws Exception {
        try (UserContext userContext = new UserContext("Mao")) {
            System.out.println(UserContext.USER_CONTEXT.get());
            System.out.println(UserContext.currentUser());
        }
        System.out.println(UserContext.USER_CONTEXT.get());
    }
}
