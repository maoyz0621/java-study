package com.myz.java.study.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 1 成员必须是无参的
 * 2 成员参数必须是无异常的
 * 3 类型受限制
 *
 * @author maoyz0621
 */
@Target({ElementType.TYPE, ElementType.METHOD})   //作用域
@Retention(RetentionPolicy.RUNTIME)   //生命周期
@Documented
@Inherited      //允许子类继承
public @interface MyAnnotation {

    /**
     * String类型
     */
    String[] value() default "";

    /**
     * 基本数据类型，不能使用包装类
     */
    int age() default 18;

    /**
     * 成员必须是无参的
     */
    // double price(double d);

    /**
     * 成员参数必须是无异常的
     */
    // double price() throws Exception;

    /**
     * 类型受限制
     */
    // List list();
}
