package com.myz.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myz.spring.scope.Hello;

/**
 * Bean作用域
 * singleton(默认)
 * prototype
 * request
 * session
 * global session
 */
public class TestScopeSpring {

    /**
     * singleton单例,同一个实例
     */
    @Test
    public void testSingleton() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-scope.xml");
        // 首先执行构造器
        Hello hello = (Hello) context.getBean("helloworld");
        System.out.println("... begin ...");

        // true
        System.out.println(context.getBean("helloworld") == context.getBean("helloworld"));
        hello.setId(12);
        System.out.println(hello);

        Hello hello1 = (Hello) context.getBean("helloworld");
        System.out.println(hello1);
    }

    /**
     * prototype原型
     * context.getBean("helloworld1")　每次执行都new一个实例
     */
    @Test
    public void testPrototype() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-scope.xml");

        Hello hello = (Hello) context.getBean("helloworld1");
        Hello hello1 = (Hello) context.getBean("helloworld1");

        System.out.println("... begin ...");

        // false
        System.out.println(hello == hello1);

        hello.setId(12);
        System.out.println(hello);

        System.out.println(hello1);
    }


}
