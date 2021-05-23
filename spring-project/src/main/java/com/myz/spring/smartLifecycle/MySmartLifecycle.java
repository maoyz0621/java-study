/**
 * Copyright 2021 Inc.
 **/
package com.myz.spring.smartLifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * SmartLifecycle
 * 当Spring容器加载所有bean并完成初始化之后，会接着回调实现该接口的类中对应的方法（start()方法）
 *
 * @author maoyz0621 on 2021/3/24
 * @version v1.0
 */
@Component
public class MySmartLifecycle implements SmartLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySmartLifecycle.class);

    private AtomicBoolean running = new AtomicBoolean(false);

    /**
     * 1
     * 根据该方法的返回值决定是否执行start方法。
     * 返回true时start方法会被自动执行，返回false则不会。
     */
    @Override
    public boolean isAutoStartup() {
        LOGGER.info("isAutoStartup()");
        return true;
    }

    /**
     * 2
     * 1. 只有该方法返回false时，start方法才会被执行。<br/>
     * 2. 只有该方法返回true时，stop(Runnable callback)或stop()方法才会被执行。
     */
    @Override
    public boolean isRunning() {
        LOGGER.info("isRunning()");
        return this.running.get();
    }

    /**
     * 4
     * 当isRunning()方法返回true时，该方法才会被调用。
     */
    @Override
    public void stop(Runnable callback) {
        LOGGER.info("stop(callback)");
        stop();
        // 如果你让isRunning返回true，需要执行stop这个方法，那么就不要忘记调用callback.run()。
        // 否则在你程序退出时，Spring的DefaultLifecycleProcessor会认为你这个TestSmartLifecycle没有stop完成，程序会一直卡着结束不了，等待一定时间（默认超时时间30秒）后才会自动结束。
        // PS：如果你想修改这个默认超时时间，可以按下面思路做，当然下面代码是springmvc配置文件形式的参考，在SpringBoot中自然不是配置xml来完成，这里只是提供一种思路。
        // <bean id="lifecycleProcessor" class="org.springframework.context.support.DefaultLifecycleProcessor">
        //      <!-- timeout value in milliseconds -->
        //      <property name="timeoutPerShutdownPhase" value="10000"/>
        // </bean>
        callback.run();
    }

    /**
     * 3
     */
    @Override
    public void start() {
        LOGGER.info("start()");

        if (!this.running.get()) {
            LOGGER.info("start() true");
            running.set(true);
        }
    }

    /**
     * 5
     * 接口Lifecycle的子类的方法，只有非SmartLifecycle的子类才会执行该方法。<br/>
     * 1. 该方法只对直接实现接口Lifecycle的类才起作用，对实现SmartLifecycle接口的类无效。<br/>
     * 2. 方法stop()和方法stop(Runnable callback)的区别只在于，后者是SmartLifecycle子类的专属。
     */
    @Override
    public void stop() {
        LOGGER.info("stop()");
        this.running.set(false);
    }

    @Override
    public int getPhase() {
        return 0;
    }

}