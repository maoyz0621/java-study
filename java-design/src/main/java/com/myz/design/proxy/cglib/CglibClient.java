package com.myz.design.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 参考springProject下的项目
 *
 * @author maoyz on 18-3-9.
 */
public class CglibClient<T> {

    private final Class<T> target;

    public CglibClient(Class<T> target) {
        if (target.isInterface()) {
            throw new IllegalArgumentException("target must be Class");
        }
        this.target = target;
    }

    public T newInstance(MethodInterceptor interceptor) {
        // Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target);
        // 设置拦截类
        enhancer.setCallback(interceptor);
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        CglibClient<TargetObject> cglibClient = new CglibClient<>(TargetObject.class);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\Work\\IDEA\\java-study\\java-design\\src\\main\\java\\com\\myz\\design\\proxy\\cglib");
        TargetObject targetObject = cglibClient.newInstance(new TargetInterceptor());

        System.out.println(targetObject.method("1"));
        System.out.println(targetObject.noMethod("1"));
        System.out.println(targetObject.error());
        System.out.println(targetObject.finalMethod());
    }

}
