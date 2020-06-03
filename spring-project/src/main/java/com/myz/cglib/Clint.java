package com.myz.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author maoyz on 18-3-9.
 */
public class Clint {

    public static void main(String[] args) {
        // Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        // 1 设置父类
        enhancer.setSuperclass(TargetObject.class);
        // 2 设置回调
        enhancer.setCallback(new TargetInterceptor());
        // 3 对象创建
        TargetObject targetObject = (TargetObject) enhancer.create();
        // 4 执行目标对象方法
        targetObject.method("1");
    }
}
