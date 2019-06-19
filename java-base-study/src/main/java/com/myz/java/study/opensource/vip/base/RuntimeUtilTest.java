/**
 * Copyright 2019 Inc.
 **/
package com.myz.java.study.opensource.vip.base;

import com.vip.vjtools.vjkit.base.RuntimeUtil;
import org.junit.Test;

/**
 * @author maoyz0621 on 19-4-28
 * @version: v1.0
 */
public class RuntimeUtilTest {

    @Test
    public void test() {
        System.out.println("获得当前进程的PID = " + RuntimeUtil.getPid());
        System.out.println("获取CPU核数 = " + RuntimeUtil.getCores());
        System.out.println("通过StackTrace，获得调用者的类名 = " + RuntimeUtil.getCallerClass());
        System.out.println("通过StackTrace，获得调用者的类名.方法名()" + RuntimeUtil.getCallerMethod());
        System.out.println("通过StackTrace，获得当前方法的类名" + RuntimeUtil.getCurrentClass());
        System.out.println("通过StackTrace，获得当前方法的类名.方法名()" + RuntimeUtil.getCurrentMethod());

        // 注册JVM关闭时的钩子程序
        RuntimeUtil.addShutdownHook(() -> {
            System.out.println("****************************");
        });

        System.out.println("输入的JVM参数列表:" + RuntimeUtil.getVmArguments());
        System.out.println("应用启动到现在的毫秒数 " + RuntimeUtil.getUpTime());
    }
}
