/**
 * Copyright 2022 Inc.
 **/
package com.myz.spring.aop.code;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * 可用的切入点实现
 * AnnotationMatchingPointcut	此实现在类或方法上查找特定的Java注解
 * AspectJExpressionPointcut	此实现使用AspectJ织入器以AspectJ语法评估切入点表达式
 * ComposablePointcut	ComposablePointcut使用诸如union()和intersection()等操作组合两个或更多切入点
 * ControlFlowPointcut	匹配另一个方法的控制流中的所有方法，即任何作为另一个方法的结果而直接或间接调用的方法
 * DynamicMethodMatcherPointcut	旨在作为构建动态切入点的基类
 * JdkRegexpMethodPointcut	允许使用JDK1.4正则表达式支持定义切入点
 * NameMatchMethodPointcut	对方法名称列表执行简单匹配
 * StaticMethodMatcherPointcut	用作构建静态切入点的基础
 * @author maoyz0621 on 2022/10/20
 * @version v1.0
 */
public class MyStaticMethodMatcherPointcut extends StaticMethodMatcherPointcut {

    public static void main(String[] args) throws NoSuchMethodException {
        MyStaticMethodMatcherPointcut pointcut = new MyStaticMethodMatcherPointcut();
        // true
        System.out.println(pointcut.matches(A.class.getDeclaredMethod("a"), A.class));
        // false
        System.out.println(pointcut.matches(A.class.getDeclaredMethod("b"), A.class));
        // true
        System.out.println(pointcut.matches(B.class.getDeclaredMethod("a"), B.class));
        // true
        System.out.println(pointcut.matches(C.class.getDeclaredMethod("a"), C.class));
        // true
        System.out.println(pointcut.matches(D.class.getDeclaredMethod("a"), D.class));
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        MergedAnnotations annotations = MergedAnnotations.from(method);
        if (annotations.isPresent(Transactional.class)) {
            return true;
        }
        annotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
        if (annotations.isPresent(Transactional.class)) {
            return true;
        }
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return super.getClassFilter();
    }

    class A {

        @Transactional
        void a() {

        }

        void b() {

        }
    }

    @Transactional
    class B {

        void a() {
        }
    }

    @Transactional
    interface C {
        void a();
    }

    class D implements C {
        @Override
        public void a() {

        }
    }
}