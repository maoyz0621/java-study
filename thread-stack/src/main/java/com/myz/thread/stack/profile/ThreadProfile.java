package com.myz.thread.stack.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 线程调用堆栈信息
 *
 * @author maoyz
 * @version V1.0
 * @date 2021/9/27 14:31
 */
public class ThreadProfile {

    /**
     * 超时阈值
     */
    private static int threshold = 200;
    private static Logger LOG = null;
    private static final ThreadLocal<StackTrace> stackTrace = new InheritableThreadLocal<>();
    private static Object lock = new Object();


    /**
     * 线程初始化
     *
     * @param threshold
     */
    public static void init(int threshold) {
        if (threshold > 0) {
            ThreadProfile.threshold = threshold;
        }
        initLog();
    }


    /**
     * 开始
     *
     * @param uri
     */
    public static void start(String uri) {
        stackTrace.remove();
        stackTrace.set(new StackTrace(uri));
    }

    /**
     * 进入
     *
     * @param className
     * @param methodName
     */
    public static void enter(String className, String methodName) {
        if (stackTrace.get() != null) {
            stackTrace.get().enter(ProfileEntry.builder().className(className).methodName(methodName).build());
        }
    }

    /**
     * 退出
     */
    public static void exit() {
        if (stackTrace.get() != null) {
            stackTrace.get().exit();
        }
    }

    /**
     * 停止
     */
    public static void stop() {
        StackTrace stackTrace = ThreadProfile.stackTrace.get();
        if (stackTrace == null) {
            return;
        }
        stackTrace.end();

        dump();

        stackTrace.clear();
    }

    /**
     * 初始化日志生成器
     * 双检锁
     */
    private static void initLog() {
        if (LOG == null) {
            //　同步块，线程安全的创建实例
            synchronized (lock) {
                if (LOG == null) {
                    LOG = LoggerFactory.getLogger("THREAD_PROFILE");
                    if (LOG == null) {
                        LOG = LoggerFactory.getLogger(ThreadProfile.class);
                    }
                }
            }
        }
    }

    /**
     * 打印日志
     */
    private static void dump() {
        StackTrace stackTrace = ThreadProfile.stackTrace.get();

        List<ProfileEntry> profileEntryList = stackTrace.getProfileEntryList();

        StringBuilder sb = new StringBuilder();
        for (ProfileEntry entry : profileEntryList) {

            for (int i = 0; i < entry.getLevel(); i++) {
                sb.append("");
            }
            sb.append(entry.toString()).append("\r\n");
        }

        LOG.warn("{}", sb);
    }

}
