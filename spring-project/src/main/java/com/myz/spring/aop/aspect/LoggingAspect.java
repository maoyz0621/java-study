package com.myz.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 1 将此类声明为切面
 * 2 同时这个类必须是IOC容器
 * 3 执行优先级@Order
 * 4 ProceedJoinPoint  proceed()环绕通知
 * <p>
 * Advice 通知，某个连接点所采用的处理逻辑，也就是向连接点注入代码
 * JoinPoint 连接点，程序执行过程中某个特定的点
 * Pointcut　切入点
 * Introduction 引入,允许我们向现有的类添加新方法属性。把切面（也就是新方法属性：通知定义的）用到目标类中
 * Target 目标,引入中所提到的目标类，也就是要被通知的对象，也就是真正的业务逻辑，可以在毫不知情的情况下，被咱们织入切面。而自己专注于业务本身的逻辑。
 * Weaving 织入,把切面应用到目标对象来创建新的代理对象的过程
 * Proxy 代理
 *
 * @author myz
 */

@Component
@Aspect
@Order(2)
public class LoggingAspect {

    // 定义切入点,方法必须是void，可以通过&& || !组合切入点,共享切入点
    @Pointcut(value = "execution(* com.myz.spring.aop.annotation.*.*(..))")
    public void joinPointMethod() {
    }

    /**
     * 紧跟Around,之后执行Method
     *
     * @param joinPoint
     */
    @Before("joinPointMethod()")
    public void beforeService(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("begin .." + methodName + ":" + args);
    }

    /**
     * 带参数&& args()
     *
     * @param arg1,arg2
     */
    @Before("joinPointMethod() && args(arg1,arg2)")
    public void beforeServiceWithArgs(int arg1, int arg2) {
        System.out.println("begin args .." + ":" + arg1 + "/" + arg2);
    }

    /**
     * 一般用于释放资源
     */
    @After("joinPointMethod()")
    public void afterService() {
        System.out.println("after ..");
    }

    /**
     * 最后执行
     *
     * @param jp
     * @param retVal
     */
    @AfterReturning(value = "joinPointMethod()", returning = "retVal")
    public void afterReturnService(JoinPoint jp, Object retVal) {
        System.out.println("after returning .." + jp.getSignature().getName() + "==>" + retVal);
    }

    /**
     * 最后执行
     *
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "joinPointMethod()", throwing = "e")
    public void afterThrowing(JoinPoint jp, Exception e) {
        System.out.println("after throwing .." + jp.getSignature().getName() + e);
    }

    /**
     * Around优先执行,第一个参数必须是ProceedingJoinPoint
     * 执行方法,proceed()
     */
    @Around(value = "joinPointMethod()")
    public Object around(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Object result = null;

        // 获取该类 int com.myz.spring.aop.annotation.CalculatorImp.add(int,int)
        Signature signature = pjp.getSignature();
        // class com.myz.spring.aop.annotation.CalculatorImp
        Class type = signature.getDeclaringType();

        // 获取该方法 int com.myz.spring.aop.annotation.CalculatorImp.add(int,int)
        MethodSignature methodSignature = (MethodSignature) signature;
        // public int com.myz.spring.aop.annotation.CalculatorImp.add(int,int)
        Method method = methodSignature.getMethod();

        // 获取目标对象 class com.myz.spring.aop.annotation.CalculatorImp
        Object target = pjp.getTarget().getClass();

        // 获取该方法的参数
        Object[] objects = pjp.getArgs();
        Class<?>[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < objects.length; i++) {
            argTypes[i] = objects[i].getClass();
        }

        // 方法名称
        String methodName = pjp.getSignature().getName();

        //method = pjp.getTarget().getClass().getMethod(methodName, argTypes);

        try {
            System.out.println("前置通知...");
            // 让目标方法执行
            result = pjp.proceed();
            System.out.println("执行结果为：" + result);
            // 对返回结果进行处理
            if (Objects.nonNull(result)) {
                result = 1;
            } else {
                result = "set around val";
            }
            System.out.println("返回通知...");
        } catch (Throwable e) {
            System.out.println("异常通知...");
            throw new RuntimeException(e);
        }
        System.out.println("后置通知...");
        return result;
    }
}
