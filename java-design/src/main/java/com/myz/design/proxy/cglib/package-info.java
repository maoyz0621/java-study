/**
 * cglib :Code Generation Library
 * 使用ASM在内存中动态的生成被代理的子类，代理类无需实现接口
 * <p>
 * ASM:字节码操作框架，以二进制的形式修改已有类或动态生成类
 * <p>
 * 前提：不能对final类以及final方法进行代理
 *
 * @author maoyz
 */
package com.myz.design.proxy.cglib;