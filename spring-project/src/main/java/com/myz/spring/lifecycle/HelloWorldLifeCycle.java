package com.myz.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * InitializingBean
 * DisposableBean 用完即可丢弃的
 *
 * @author maoyz
 */
public class HelloWorldLifeCycle implements InitializingBean, DisposableBean {

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init ...");
    }

    public void say() {
        System.out.println("执行say()");
    }

}
