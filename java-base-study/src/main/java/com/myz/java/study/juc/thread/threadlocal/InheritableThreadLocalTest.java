package com.myz.java.study.juc.thread.threadlocal;

/**
 * @author maoyz
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) {
        InheritableThreadLocalContext.Context context = new InheritableThreadLocalContext.Context();
        context.setUsername("maoyz");
        context.setAge(12);

        InheritableThreadLocalContext.getThreadLocalContext().set(context);
        InheritableThreadLocalContext.getInheritableThreadLocalContext().set(context);

        // NPE
        // new Thread(() -> {
        //     System.out.println("ThreadLocal: " + InheritableThreadLocalContext.get());
        // }).start();


        new Thread(() -> {
            System.out.println("InheritableThreadLocal: " + InheritableThreadLocalContext.get0());
        }).start();

    }
}
