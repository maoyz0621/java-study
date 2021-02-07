package com.myz.spring.test;

import com.myz.spring.lifecycle.BeanLifeWithPostProcessor;
import com.myz.spring.lifecycle.HelloWorld;
import com.myz.spring.lifecycle.HelloWorldLifeCycle;
import com.myz.spring.lifecycle.LifeCycleHelloWorld;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean生命周期
 * 定义
 * 初始化
 * InitializingBean接口　　    优先级:1
 * init-method                优先级:2
 * default-init-method(全局)   被覆盖
 * /@PostConstruct
 * 使用
 * 销毁
 * DisposableBean接口           优先级:1
 * destroy-method              优先级:2
 * default-destroy-method(全局) 被覆盖
 * /@PreDestory
 * <p>
 * start()  启动容器并将启动信号扩散至该容器中的所有组件
 * close()　销毁
 *
 * @author myz
 */
public class TestLifeCycleSpring {

    /**
     * 方法内
     */
    @Test
    public void test() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        context.start();
        HelloWorld cycle = (HelloWorld) context.getBean("helloworld1");
        cycle.say();
        context.close();
        context.registerShutdownHook();
    }

    /**
     * 实现接口InitializingBean, DisposableBean
     */
    @Test
    public void test1() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        context.start();
        HelloWorldLifeCycle cycle = (HelloWorldLifeCycle) context.getBean("helloworld2");
        cycle.say();
        context.close();
        context.registerShutdownHook();
    }

    /**
     * 全局配置
     */
    @Test
    public void testDefault() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        context.start();
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloworld3");
        helloWorld.say();
        context.close();
        context.registerShutdownHook();
    }


    /**
     * 实现接口
     * 方法
     * 全局配置(被覆盖)
     * 执行顺序:
     * 构造方法
     * --> BeanPostProcessor postProcessBeforeInitialization()
     * --> InitializingBean afterPropertiesSet()
     * --> init()
     * --> BeanPostProcessor postProcessAfterInitialization()
     * --> 执行方法()
     * --> DisposableBean destroy()
     * --> destroy()
     */
    @Test
    public void test2() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        context.start();
        LifeCycleHelloWorld helloWorld = (LifeCycleHelloWorld) context.getBean("helloworld4");
        /**
         执行实例化...
         BeanPostProcessor postProcessBeforeInitialization() ...
         执行InitializingBean afterPropertiesSet() ...
         执行init()
         BeanPostProcessor postProcessAfterInitialization() ...
         执行say()
         执行DisposableBean destroy() ...
         执行destory()
         */
        helloWorld.say();
        context.close();
        context.registerShutdownHook();
    }

    /**
     * BeanFactoryPostProcessor postProcessBeanFactory()
     执行实例化...
     BeanPostProcessor postProcessBeforeInitialization() ...
     执行InitializingBean afterPropertiesSet() ...
     执行init()
     BeanPostProcessor postProcessAfterInitialization() ...
     执行say()
     执行DisposableBean destroy() ...
     执行destory()
     */
    @Test
    public void test3() {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("beans-lifecycle.xml");
        context.start();
        LifeCycleHelloWorld helloWorld = (LifeCycleHelloWorld) context.getBean("helloworld4");
        helloWorld.say();
        // old value (hello), new Value (被我修改了, 哈哈哈)
        System.out.println(helloWorld.getDesc());
        context.close();
        context.registerShutdownHook();
    }

}
