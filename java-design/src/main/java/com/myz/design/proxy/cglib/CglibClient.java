package com.myz.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

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

    public T newInstance() {
        // Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target);
        //
        enhancer.setCallback(new TargetInterceptor());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        CglibClient<TargetObject> cglibClient = new CglibClient<>(TargetObject.class);
        TargetObject targetObject = cglibClient.newInstance();
        System.out.println(targetObject.method("1"));
    }

}
