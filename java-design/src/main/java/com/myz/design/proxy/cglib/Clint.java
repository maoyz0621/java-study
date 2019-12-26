package com.myz.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * 参考springProject下的项目
 * @author maoyz on 18-3-9.
 */
public class Clint {

    public static void main(String[] args) {
        // Enhancer是CGLIB的字节码增强器，可以很方便的对类进行拓展
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(TargetObject.class);
        //
        enhancer.setCallback(new TargetInterceptor());
        TargetObject targetObject = (TargetObject) enhancer.create();
        System.out.println(targetObject.method("1"));

    }
}
